package io.ionic.ylnewapp.view.activity.market;

import android.graphics.drawable.Animatable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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
import io.ionic.ylnewapp.adpater.ZxAdapter;
import io.ionic.ylnewapp.bean.market.ZxDetailBean;
import io.ionic.ylnewapp.custom.CuInteractiveKLineLayout;
import io.ionic.ylnewapp.custom.MyListView;
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
    @ViewInject(R.id.text_all)
    TextView text_all;
    @ViewInject(R.id.text_line)
    TextView text_line;
    @ViewInject(R.id.lv_hq)
    MyListView lv;
    @ViewInject(R.id.te_hq)
    TextView te_hq;
    @ViewInject(R.id.te_jk)
    TextView te_jk;

    @ViewInject(R.id.myLineLayout) CuInteractiveKLineLayout kLineLayout ;
    @ViewInject(R.id.Left_Loading_Image) private ImageView Left_Loading_Image = null;
    @ViewInject(R.id.Right_Loading_Image) private ImageView Right_Loading_Image = null;

    private EntrySet entrySet;

    ZxAdapter adapter;
    List<String> mData;

    @Event(type = View.OnClickListener.class,value ={ R.id.back,R.id.jj,R.id.te_hq,R.id.te_jk})
    private void click(View v){
        switch (v.getId()){
            case R.id.back :
                finish();
                break;
            case R.id.jj:
                text_line.setVisibility(View.GONE);
                text_all.setVisibility(View.VISIBLE);
                break;
            case R.id.te_jk:
                te_jk.setTextColor(mContext.getColor(R.color.main));
                te_hq.setTextColor(mContext.getColor(R.color.black_1));
                hq.setVisibility(View.GONE);
                jk.setVisibility(View.VISIBLE);
                break;
            case R.id.te_hq:
                te_jk.setTextColor(mContext.getColor(R.color.black_1));
                te_hq.setTextColor(mContext.getColor(R.color.main));
                jk.setVisibility(View.GONE);
                hq.setVisibility(View.VISIBLE);
                break;
        }
    }

    String eventName ="Bittrex_BTC_USDT_minute"; //socket

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zx_sz_detail);
        initSockte();
        init();
    }


    /**
     * 界面数据初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),225);
        initView();
        initList();
    }

    /**
     * 初始化
     */
    private void initView() {
        getKLineData();
    }

    /**
     * listveiw设置
     */
    private void initList() {
        mData = new ArrayList<>();
        mData.add("ttt");
        mData.add("uuu");
        adapter = new ZxAdapter(mContext,mData);
        lv.setAdapter(adapter);
    }


    /**
     * 请求K线历史数据
     */
    private void getKLineData(){
        OkGo.<String>get("http://47.52.145.241:8080/marketData/ticker?exchangeName=Bittrex&currencyPair=BTC-USDT&type=minute_1&beginDate=2018-06-11%2010:10:00&endDate=2018-06-11%2015:20:05" )
                .tag(this)
                .cacheTime(1000)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ZxDetailBean javaBean = gson.fromJson(response.body().toString(), ZxDetailBean.class);
                        if (javaBean.isSuccess() == true)
                            initUI(javaBean.getData().size());
                            loadKLineData(response.body(),javaBean.getData().size());
                            int size = javaBean.getData().size() -1;
                            tv_close.setText(javaBean.getData().get(size).getClose()+"");
                            tv_high.setText(javaBean.getData().get(size).getHighest()+"");
                            tv_low.setText(javaBean.getData().get(size).getLowest()+"");
                            title.setText(javaBean.getData().get(size).getCurrencyPair()+"");
                        }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }

                });
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

    /**
     * 初始化
     */
    private void initSockte() {
        mSocket.on(eventName, minuteMessage);
        mSocket.connect();
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
                    if(data!=null){
                        Gson gson = new Gson();
                        ZxDetailBean.DataBean ma = gson.fromJson(data.toString(),ZxDetailBean.DataBean.class);
                            setView(ma);


                        Entry jj = new Entry(Float.parseFloat(ma.getClose()+""),Float.parseFloat(ma.getHighest()+""),Float.parseFloat(ma.getLowest()+"")
                                ,Float.parseFloat(ma.getOpen()+""),
                                0, DateUtil.getDTofSystem(ma.getIntervalDate()+""));

                        Log.i("-------",data.toString());
                        kLineLayout.getKLineView().getRender().getEntrySet().addEntry(jj);
                        kLineLayout.getKLineView().notifyDataSetChanged();

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
//                    Toast.makeText(mContext, "single tab [" + x + ", " + y + "]", Toast.LENGTH_SHORT).show();
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
//                Right_Loading_Image.setVisibility(View.VISIBLE);
//                ((Animatable) Right_Loading_Image.getDrawable()).start();
//                // 模拟耗时
//                kLineLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Right_Loading_Image.setVisibility(View.GONE);
//                        ((Animatable) Right_Loading_Image.getDrawable()).start();
//
//                        List<Entry> entries = addEntries();
//
//                        kLineLayout.getKLineView().getRender().getEntrySet().addEntries(entries);
//                        kLineLayout.getKLineView().notifyDataSetChanged();
//                        kLineLayout.getKLineView().refreshComplete(entries.size() > 0);
//
//                        if (entries.size() == 0) {
//                            Toast.makeText(mContext, "已经到达最右边了", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }, 1000);
            }
        });
    }


    private void loadKLineData(final String data,final int size) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                PerformenceAnalyser.getInstance().addWatcher();
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
                PerformenceAnalyser.getInstance().addWatcher();
                kLineLayout.getKLineView().notifyDataSetChanged();
                PerformenceAnalyser.getInstance().addWatcher();
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
        Entry jj = new Entry(Float.parseFloat(ma.getClose()+""),Float.parseFloat(ma.getHighest()+""),Float.parseFloat(ma.getLowest()+"")
                ,Float.parseFloat(ma.getOpen()+""),
                0, DateUtil.getDTofSystem(ma.getIntervalDate()+""));
            entries.add(jj);

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
