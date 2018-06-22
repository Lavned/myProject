package io.ionic.ylnewapp.view.activity.market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiangyy.easydialog.LoadingDialog;

import org.json.JSONArray;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.net.URISyntaxException;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.MarketFragAdapter2;
import io.ionic.ylnewapp.bean.market.MarketBean;

import static io.ionic.ylnewapp.constants.Constants.MARKRT_SZ;

/**
 * Created by mogojing on 2018/6/12/0012.
 */
@ContentView(R.layout.market_frag_02)//加载布局
public class MarketFragment2 extends Fragment {

    public MarketFragment2() {
    }

    @ViewInject(R.id.lv_mk_fragment2)
    ListView lvData;
    MarketFragAdapter2 adapter;
    public LoadingDialog.Builder mBuilder ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = x.view().inject(this, inflater, container);
        mBuilder =  new LoadingDialog.Builder(getActivity());
        initSockte();
        return view;
    }
    //数据初始化
    private void initView(List<MarketBean> marketBean) {
        adapter = new MarketFragAdapter2(getContext(),marketBean);
        adapter.notifyDataSetChanged();
        lvData.setAdapter(adapter);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),ZxSzDetailActivity.class));
            }
        });
    }


    /**
     * 声明
     */
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket(MARKRT_SZ);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化
     */
    private void initSockte() {
        mBuilder.setTitle("请稍候").show();
        mSocket.on("marketValue", onNewMessage);
        mSocket.connect();
    }

    /**
     * 事件
     */
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if(getActivity()==null){
                return;
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mBuilder!=null)
                        mBuilder.dismiss();
                    JSONArray data = null;
                    if(args[0] instanceof String){
                        data = (JSONArray) args[1];
                    }else {
                        data = (JSONArray) args[0];
                    }
                    if(data!=null){
                        Gson gson = new Gson();
                        List<MarketBean> marketBean2s =  gson.fromJson(data.toString(),
                                new TypeToken<List<MarketBean>>(){}.getType());
                        Log.i("socketlog",""+data.toString());
                        if(marketBean2s!=null)
                            initView(marketBean2s);
                    }
                }
            });
        }

    };

    @Override
    public void onDestroyView() {
        Log.i("socketlog","我关啦");
        super.onDestroyView();
        if(mSocket!=null){
            mSocket.disconnect();
            mSocket.off("marketValue", onNewMessage);
        }
    }
}