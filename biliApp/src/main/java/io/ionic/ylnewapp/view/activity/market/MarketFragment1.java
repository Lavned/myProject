package io.ionic.ylnewapp.view.activity.market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiangyy.easydialog.LoadingDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.net.URISyntaxException;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.market.MarketFragAdapter1;
import io.ionic.ylnewapp.bean.market.MarketBean;
import io.ionic.ylnewapp.bean.market.ToMarketBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;

import static io.ionic.ylnewapp.constants.Constants.MARKRT_CHOOSE;

/**
 * Created by mogojing on 2018/6/12/0012.
 */
@ContentView(R.layout.market_frag_01)//加载布局
public class MarketFragment1 extends Fragment {

    public MarketFragment1() { }

    @ViewInject(R.id.lv_mk_fragment)
    ListView lvData;

    @ViewInject(R.id.lin_add)
    LinearLayout lin_add;

    private boolean mIsDeleteModel = false; // 当前listView是否处于删除模式 ,默认是正常显示模式.
    MarketFragAdapter1 adapter;
    List<ToMarketBean.BodyBean> mData;
    LoadingDialog.Builder mBuilder ;

    @Event(type = View.OnClickListener.class,value = {R.id.delTv,R.id.add_bz})
    private void click(View v){
        switch (v.getId()){
            case R.id.delTv :
                if(!mIsDeleteModel){
                    showDeleteModel();
                }else {
                    hideDeleteModel();
                }
                break;
            case R.id.add_bz:
                startActivity(new Intent(getActivity(),MarketSearchActivity.class));
                break;
        }
    }

    /**
     * socket对象声明
     */
    public static Socket mSockets;
    {
        try {
            mSockets = IO.socket(MARKRT_CHOOSE);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化socket
     */
    private void initSockte() {
        mSockets.on("choose", choose);
        mSockets.connect();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = x.view().inject(this, inflater, container);
        mBuilder = new LoadingDialog.Builder(getActivity());
        initSockte();
        return view;
    }




    /**
     * 请求数据
     */
    private void loadData() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "market/chooses" )
                .tag(this)
                .cacheTime(1000)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(getActivity(),"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ToMarketBean javaBean =gson.fromJson(response.body().toString(),ToMarketBean.class);
                        if(javaBean.getStatus() == 401)
                            ActivityUtils.toLogin(getActivity(),1);
                        else if(javaBean.getStatus() ==200){
                            mData = javaBean.getBody();
                            mSockets.emit("init", gson.toJson(javaBean.getBody()));
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(getActivity());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mBuilder.dismiss();
                    }
                });
    }


    /**
     * 获取最新数据
     */
    @Override
    public void onResume() {
        super.onResume();
        if(lvData!=null){
            loadData();
        }
    }

    /**
     * 监听socket事件
     */
    private Emitter.Listener choose = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if(getActivity()==null){
                return;
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONArray data = null;
                    if(args[0] instanceof String){
                        data = (JSONArray) args[1];
                        Log.i("-----",data.toString());
                    }else {
                        data = (JSONArray) args[0];
                        Log.i("-----222",data.toString());
                    }
                    if(data!=null){
                        Gson gson = new Gson();
                        List<MarketBean> marketBean2s =  gson.fromJson(data.toString(),
                                new TypeToken<List<MarketBean>>(){}.getType());
                        if(marketBean2s!=null)
                            initList(marketBean2s);
                    }
                }
            });
        }
    };

    //数据初始化
    private void initList(List<MarketBean> MyData) {
        if(mData.size() == 0 ){
            lin_add.setVisibility(View.VISIBLE);
        }
        adapter = new MarketFragAdapter1(getActivity(),MyData,mData);
        adapter.notifyDataSetChanged();
        lvData.setAdapter(adapter);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ZxSzDetailActivity.class);
                intent.putExtra("name",mData.get(position).getCurrencyPair()
                        +"|"+mData.get(position).getExchangeName());
                startActivity(intent);
                MobclickAgent.onEvent(getActivity(), "Pricedetail");
            }
        });
    }

    // 展示编辑模式
    private void showDeleteModel() {
        adapter.showDeleteCheck();
        mIsDeleteModel = true;
    }

    // 隐藏编辑模式，回到正常显示listView
    private void hideDeleteModel() {
        adapter.hideDeleteCheck();
//        initData();
        mIsDeleteModel = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(adapter!=null){
            hideDeleteModel();
        }
        if(mSockets!=null){
            mSockets.disconnect();
            mSockets.off("choose", choose);
        }
    }
}