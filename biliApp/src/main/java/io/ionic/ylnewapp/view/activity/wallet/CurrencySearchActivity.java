package io.ionic.ylnewapp.view.activity.wallet;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.CurrencyNameAdapater;
import io.ionic.ylnewapp.adpater.CurrencyLvAdapter;
import io.ionic.ylnewapp.bean.CoinBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;


//搜索
public class CurrencySearchActivity extends BaseActivity {

    @ViewInject(R.id.gridview)
    GridView gridView;
    @ViewInject(R.id.search_lv)
    ListView searchLv;
    @ViewInject(R.id.ed_currency)
    EditText ed_currency;

    CurrencyNameAdapater adapter;
    CurrencyLvAdapter lvAdapte;
    List<String> dataList;

    List<CoinBean.BodyBean>mData;
    CurrencyLvAdapter lvAdapter;
    String keyName ="";


    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.del})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.del:
                ed_currency.setText("");
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_search);
        init();
        initGrid();
        loadList();
    }

    /**
     * 初始化lv
     */
    private void initListView(final List<CoinBean.BodyBean> mData) {
        if(mData != null & mData.size() == 0)  {
            T.showShort("暂无数据");
        }
        lvAdapte=new CurrencyLvAdapter(mContext, mData);
        lvAdapte.notifyDataSetChanged();
        searchLv.setAdapter(lvAdapte);
        searchLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addCoin(mData.get(position).getKey());
            }
        });
    }

    /**
     * 初始化View
     */
    private void initGrid() {
        dataList = new ArrayList<>();
        dataList.add("BTC");
        dataList.add("ETH");
        dataList.add("ADA");
        dataList.add("EOS");
        dataList.add("NEO");
        dataList.add("VEN");
        dataList.add("ETC");
        dataList.add("OKB");
        dataList.add("REP");
        dataList.add("BTC");
        adapter=new CurrencyNameAdapater(mContext, dataList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addCoin(dataList.get(position));
            }
        });
    }

    /**
     * 初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        ed_currency.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 当按了搜索之后关闭软键盘
                    ActivityUtils.colseIM(ed_currency);
                    keyName = ed_currency.getText().toString().trim();
                    loadList();
                    return true;
                }
                return false;
            }
        });
    }



    private void loadList() {
        mBuilder.setTitle("请稍候...").show();
        OkGo.<String>get(Constants.URL_BASE + "coins/coin?key=" + keyName )
                .tag(this)
                .cacheTime(1000)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CoinBean javaBean =gson.fromJson(response.body().toString(),CoinBean.class);
                        if(javaBean.getStatus() == 401){
                            ActivityUtils.toLogin(CurrencySearchActivity.this,0);
                        }
                        mData = javaBean.getBody();
                        if(mData!= null){
                            initListView(mData);
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mBuilder.dismiss();
                    }
                });
    }

    /**
     * 添加货币
     * @param index
     */
    private void addCoin(String index) {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>put(Constants.URL_BASE + "coins/coin" )
                .tag(this)
                .cacheTime(1000)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("name",index)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response.body().toString());
                            if(jsonObject.getInt("status") == 401){
                                ActivityUtils.toLogin(CurrencySearchActivity.this,0);
                            }else if(jsonObject.getInt("status") == 200){
                                finish();
                            }
                            T.showShort(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mBuilder.dismiss();
                    }
                });
    }



}
