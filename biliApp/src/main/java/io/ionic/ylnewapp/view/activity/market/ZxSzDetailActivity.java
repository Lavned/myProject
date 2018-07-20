package io.ionic.ylnewapp.view.activity.market;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.analytics.MobclickAgent;
import com.wordplat.ikvstockchart.KLineHandler;
import com.wordplat.ikvstockchart.compat.PerformenceAnalyser;
import com.wordplat.ikvstockchart.entry.Entry;
import com.wordplat.ikvstockchart.entry.EntrySet;
import com.wordplat.ikvstockchart.render.KLineRender;

import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.market.ZxAdapter;
import io.ionic.ylnewapp.bean.market.MarketSummaryBean;
import io.ionic.ylnewapp.bean.market.OtherExchangeBBean;
import io.ionic.ylnewapp.bean.market.ZxDetailBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.custom.CuInteractiveKLineLayout;
import io.ionic.ylnewapp.custom.NewListView;
import io.ionic.ylnewapp.utils.DateUtil;
import io.ionic.ylnewapp.utils.StockDataUtil;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

import static io.ionic.ylnewapp.constants.Constants.MARKRT_TICKER;

public class ZxSzDetailActivity extends BaseActivity {

    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.tv_close)
    TextView tv_close;
    @ViewInject(R.id.tv_high)
    TextView tv_high;
    @ViewInject(R.id.tv_low)
    TextView tv_low;
    @ViewInject(R.id.lin_hq)
    LinearLayout hq;
    @ViewInject(R.id.lin_jk)
    LinearLayout jk;
    @ViewInject(R.id.jk_1)
    TextView jk1;
    @ViewInject(R.id.jk_2)
    TextView jk2;
    @ViewInject(R.id.jk_3)
    TextView jk3;
    @ViewInject(R.id.lv_hq)
    NewListView lv;
    @ViewInject(R.id.te_hq)
    TextView te_hq;
    @ViewInject(R.id.te_jk)
    TextView te_jk;
    @ViewInject(R.id.closess)
    TextView close;
    @ViewInject(R.id.open)
    TextView open;

    @ViewInject(R.id.myLineLayout) CuInteractiveKLineLayout kLineLayout ;
    @ViewInject(R.id.Left_Loading_Image) private ImageView Left_Loading_Image = null;
    @ViewInject(R.id.Right_Loading_Image) private ImageView Right_Loading_Image = null;

    String eventName ="data"; //socket
    String exchangeName ,currencyPairName;

    String eventValue="";//点击事件的value

    private EntrySet entrySet;
    ZxAdapter adapter;

    @Event(type = View.OnClickListener.class,value ={ R.id.back,R.id.te_hq,R.id.te_jk})
    private void click(View v){
        switch (v.getId()){
            case R.id.back :
                finish();
                break;
            case R.id.te_jk:
                te_jk.setTextColor(mContext.getColor(R.color.main));
                te_hq.setTextColor(mContext.getColor(R.color.black_1));
                hq.setVisibility(View.GONE);
                jk.setVisibility(View.VISIBLE);
                getData();
                break;
            case R.id.te_hq:
//                getOtherData();
                te_jk.setTextColor(mContext.getColor(R.color.black_1));
                te_hq.setTextColor(mContext.getColor(R.color.main));
                jk.setVisibility(View.GONE);
                hq.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 声明
     */
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket(MARKRT_TICKER);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zx_sz_detail);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),225);
        init();
        MobclickAgent.onEvent(mContext, "Pricedetail");
    }

    /**
     * 界面数据初始化
     */
    String szName;
        private void init() {
        Intent intent = getIntent();
        String name =  intent.getStringExtra("name");
        int i = name.indexOf("|");
        szName =name.substring(i+1,name.length());
        currencyPairName = name.substring(0,i).replace("/","-");
        exchangeName = szName.replace("/","-");
        if(exchangeName.equals("全网")){
            exchangeName = "global";
            szName = "global";
        }
//        eventName = exchangeName.replace("-","_")+"_"+currencyPairName.replace("-","_")+"_minute";
        eventValue = szName+"."+name.substring(0,i)+".minute";
        Log.i("------",eventValue+"-----"+exchangeName+"------"+currencyPairName);
        initSockte(eventValue);
        getKLineData(exchangeName,currencyPairName,0);
        getOtherData(exchangeName,currencyPairName);
    }


    /**
     * listveiw设置
     */
    private void initList(final List<OtherExchangeBBean.DataBean> mData) {
        adapter = new ZxAdapter(mContext,mData);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String exName = mData.get(position).getExchange().replace("/","-");
                String pairName =mData.get(position).getCurrencyPair().replace("/","-") ;
                if(exchangeName.equals("全网")){
                    exchangeName = "global";
                }
//                eventName = mData.get(position).getExchange().replace("/","-")
//                        +"_"+mData.get(position).getCurrencyPair().replace("/","_")+"_minute";
//                initSockte(eventName);
                eventValue = mData.get(position).getExchange()+"."+mData.get(position).getCurrencyPair()+".minute";
                Log.i("------1111",eventValue+"-----"+exchangeName+"------"+currencyPairName);
                mSocket.emit("subscribe", eventValue);
                getKLineData(exName,pairName,0);
                getOtherData(exName,pairName);
            }
        });
    }

    //获取简况数据
    private void getData() {
        OkGo.<String>get(Constants.MARKRT_URL_BASEHTTP + "marketData/"+currencyPairName+"/summary")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        MarketSummaryBean item = gson.fromJson(response.body(),MarketSummaryBean.class);
                        if(item!=null){
                            jk1.setText(item.getData().getCirculatingSupply()+"万");
                            jk2.setText(item.getData().getTotalSupply()+"万");
                            jk3.setText("￥"+item.getData().getMarketCap()+"万");
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }
                });
    }

    //获取行情其他交易所数据
    private void getOtherData(String exchangeName,String currencyPairName) {
        OkGo.<String>get(Constants.MARKRT_URL_BASEHTTP + "marketData/"+exchangeName+"/"+currencyPairName+"/relation")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        OtherExchangeBBean item = gson.fromJson(response.body(),OtherExchangeBBean.class);
                        if(item!=null){
                            initList(item.getData());
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }
                });
    }



    /**
     * 请求K线历史数据
     */
    private void getKLineData(String exchangeName, String currencyPair, final int type){
        String endTime = DateUtil.getYMDofSystem();
        String beginTime = DateUtil.getDateAndTimeofMouth();
        Log.i("999999999999",beginTime+"---");
        OkGo.<String>get("http://47.52.145.241:8080/marketData/ticker?exchangeName="+exchangeName+"&" +
                "currencyPair="+currencyPair +"&" +"type=minute_1&beginDate="+beginTime+"&endDate="+endTime )
                .tag(this)
                .cacheTime(1000)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("--------",response.body().trim());
                        Gson gson = new Gson();
                        ZxDetailBean javaBean = gson.fromJson(response.body().toString(), ZxDetailBean.class);
                        if (javaBean.isSuccess() == true && javaBean.getData().size() > 0){
                            initUI(javaBean.getData().size());
                            loadKLineData(response.body(),javaBean.getData().size());
                            int size  = javaBean.getData().size();
                            if(javaBean.getData().size() > 0){
                                size  = javaBean.getData().size()-1; //删了-1
                            }
                            if(type == 0){
                                tv_close.setText(javaBean.getData().get(size).getClose()+"");
                                tv_high.setText(javaBean.getData().get(size).getHighest()+"");
                                tv_low.setText(javaBean.getData().get(size).getLowest()+"");
                                title.setText(javaBean.getData().get(size).getCurrencyPair()+"");
                                open.setText(javaBean.getData().get(size).getChange()+"%");
                                close.setText(javaBean.getData().get(size).getChangeValue()+"");
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }
                });
    }

    /**
     * 初始化
     */
    private void initSockte(String eventValue) {
        Log.i("-----------socket",eventValue +"");
        mSocket.connect();
        mSocket.emit("subscribe",eventValue);
        mSocket.on(eventName, minuteMessage);

    }

    /**
     * 事件
     */
    private Emitter.Listener minuteMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if(mContext==null){
                return;
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    Log.i("---------","---"+data.toString());
                    if(data!=null){
                        Gson gson = new Gson();
                        ZxDetailBean.DataBean ma = gson.fromJson(data.toString(),ZxDetailBean.DataBean.class);
                        setView(ma);
                        getKLineData(exchangeName,currencyPairName,1);
//                        setView(ma);
                    }
                }
            });
        }
    };

    /**
     * 设置数据
     * @param marketBean2s
     */
    private void setView(ZxDetailBean.DataBean marketBean2s) {
        tv_close.setText(marketBean2s.getClose()+"");
        tv_high.setText(marketBean2s.getHighest()+"");
        tv_low.setText(marketBean2s.getLowest()+"");
        open.setText(marketBean2s.getChange()+"%");
        close.setText(marketBean2s.getChangeValue()+"");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mSocket!=null){
            mSocket.disconnect();
            mSocket.off(eventName, minuteMessage);
        }
    }


    /**
     * K线
     */
    private void initUI(final int index) {
        kLineLayout.setKLineHandler(new KLineHandler() {
            @Override
            public void onHighlight(Entry entry, int entryIndex, float x, float y) {
            }

            @Override
            public void onCancelHighlight() {
            }

            @Override
            public void onSingleTap(MotionEvent e, float x, float y) {
                final KLineRender kLineRender = (KLineRender) kLineLayout.getKLineView().getRender();
                if (kLineRender.getKLineRect().contains(x, y)) {
                }
            }

            @Override
            public void onDoubleTap(MotionEvent e, float x, float y) {
                final KLineRender kLineRender = (KLineRender) kLineLayout.getKLineView().getRender();
                if (kLineRender.getKLineRect().contains(x, y)) {
                    kLineRender.zoomIn(x, y);
                }
            }

            @Override
            public void onLeftRefresh() {
                Left_Loading_Image.setVisibility(View.VISIBLE);
                ((Animatable) Left_Loading_Image.getDrawable()).start();
                // 模拟耗时
                kLineLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Left_Loading_Image.setVisibility(View.GONE);
                        ((Animatable) Left_Loading_Image.getDrawable()).stop();

                        List<Entry> entries = insertEntries(index);

                        kLineLayout.getKLineView().getRender().getEntrySet().insertFirst(entries);
                        kLineLayout.getKLineView().notifyDataSetChanged();
                        kLineLayout.getKLineView().refreshComplete(entries.size() > 0);

                        if (entries.size() == 0) {
                            Toast.makeText(mContext, "已经到达最左边了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1000);
            }

            @Override
            public void onRightRefresh() {
            }
        });
    }


    private void loadKLineData(final String data,final int size) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
//                PerformenceAnalyser.getInstance().addWatcher();
                PerformenceAnalyser.getInstance().addWatcher();
                entrySet = StockDataUtil.parseKLineData(data);
                PerformenceAnalyser.getInstance().addWatcher();
                entrySet.computeStockIndex();
                PerformenceAnalyser.getInstance().addWatcher();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                kLineLayout.getKLineView().setEntrySet(loadFirst(size));
//                PerformenceAnalyser.getInstance().addWatcher();
                kLineLayout.getKLineView().notifyDataSetChanged();
//                PerformenceAnalyser.getInstance().addWatcher();
            }
        }.execute();
    }

    private EntrySet loadFirst(int index) {
        EntrySet set = new EntrySet();

        for (int i = 0 ; i < index ; i++) {
            set.addEntry(entrySet.getEntryList().get(i));
        }
        return set;
    }

    private List<Entry> addEntries(ZxDetailBean.DataBean ma) {
        List<Entry> entries = new ArrayList<>();
        Entry jj = new Entry(Float.parseFloat(ma.getOpen()+""),Float.parseFloat(ma.getHighest()+""),Float.parseFloat(ma.getLowest()+"")
                ,Float.parseFloat(ma.getClose()+""),
                0, DateUtil.getDTofSystem(ma.getIntervalDate()+""));
        Entry jjs= new Entry(1000.10f,8000.10f,5000.10f,9000.10f,10,"000");
            entries.add(jj);
            entries.add(jjs);

        return entries;
    }

    private List<Entry> insertEntries(int index) {
        List<Entry> entries = new ArrayList<>();
        for (int i = index ; i > index - 0 && i > -1 ; i--) {
            entries.add(entrySet.getEntryList().get(i));
        }
        return entries;
    }
}
