package io.ionic.ylnewapp.view.activity.market;

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

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.market.MarketSearchLvAdapter;
import io.ionic.ylnewapp.adpater.market.TagNameAdapater;
import io.ionic.ylnewapp.bean.market.SeachGrid;
import io.ionic.ylnewapp.bean.market.SearchBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;


//搜索
public class MarketSearchActivity extends BaseActivity {

    @ViewInject(R.id.gridview)
    GridView gridView;
    @ViewInject(R.id.search_lv)
    ListView searchLv;
    @ViewInject(R.id.ed_currency)
    EditText ed_currency;
    @ViewInject(R.id.em_tip)
    TextView em_tip;

    TagNameAdapater adapter;
    MarketSearchLvAdapter lvAdapte;
    List<SeachGrid.DataBean> dataList;

    List<SearchBean.DataBean>mData;
    String keyName ="";


    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.del})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.del:
                ed_currency.setText("");
                keyName = "test";
                loadList();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_search);
        init();
        loadTag();
    }

    /**
     * 初始化lv
     */
    private void initListView(final List<SearchBean.DataBean> mData) {
        if(mData != null & mData.size() == 0)  {
            T.showShort("暂无数据");
        }
        em_tip.setVisibility(View.GONE);
        lvAdapte=new MarketSearchLvAdapter(mContext, mData);
        lvAdapte.notifyDataSetChanged();
        searchLv.setAdapter(lvAdapte);
        searchLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addCoinMarkrt(mData.get(position).getExchangeName(),mData.get(position).getCurrencyPair());
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


    /**
     * j加载热门标签
     */
    private void loadTag() {
        OkGo.<String>get(Constants.MARKRT_URL_BASEHTTP + "marketData/tags" )
                .tag(this)
                .cacheTime(1000)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        SeachGrid javaBean =gson.fromJson(response.body().toString(),SeachGrid.class);
                        if(javaBean.isSuccess() == true){
                            dataList = javaBean.getData();
                            if(dataList!= null){
                                initGrid(dataList);
                            }
                        }else
                            T.showShort(javaBean.getMessage()+"");

                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }

                });
    }

    /**
     * 初始化View
     */
    private void initGrid(final List<SeachGrid.DataBean> dataList) {
        adapter=new TagNameAdapater(mContext, dataList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ed_currency.setFocusable(true);
                ed_currency.setFocusableInTouchMode(true);
                ed_currency.setText(dataList.get(position).getValue());
                keyName = dataList.get(position).getValue();
                loadList();
            }
        });
    }


    /**
     * list数据
     */
    private void loadList() {
        mBuilder.setTitle("请稍候...").show();
        OkGo.<String>get(Constants.MARKRT_URL_BASEHTTP + "marketData/marketValues?currencyPair=" + keyName )
                .tag(this)
                .cacheTime(1000)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        SearchBean javaBean =gson.fromJson(response.body().toString(),SearchBean.class);
                        if(javaBean.isSuccess() == true){
                            mData = javaBean.getData();
                            if(mData!= null){
                                initListView(mData);
                            }
                        }else
                             T.showShort(javaBean.getMessage()+"");

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
     * 添加自选
     * @param name
     */
    private void addCoinMarkrt(String name,String pair) {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>put(Constants.URL_BASE + "market/choose" )
                .tag(this)
                .cacheTime(1000)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("exchangeName",name)//交易所名称 全网、火币
                .params("currencyPair",pair)// 货币名称 BTC ETF
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response.body().toString());
                            if(jsonObject.getInt("status") == 401){
                                ActivityUtils.toLogin(MarketSearchActivity.this,0);
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
