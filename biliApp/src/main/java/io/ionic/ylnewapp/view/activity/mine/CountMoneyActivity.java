package io.ionic.ylnewapp.view.activity.mine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.CountMoneyAdapter;
import io.ionic.ylnewapp.adpater.CountMoneyAdapterRight;
import io.ionic.ylnewapp.bean.TotalBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class CountMoneyActivity extends BaseActivity {


    private PieChartView chart;
    //数据
    private PieChartData pieChardata;
    List<SliceValue> values = new ArrayList<>();

    private boolean hasLabels = true;
    private boolean hasLabelsOutside = true;
    private boolean hasCenterCircle = false;
    private boolean isExploded = false;
    private boolean hasLabelForSelected = false;
    List<TotalBean.BodyBean.OrdersBean> mData;
    private List<Integer> countData;
    private List<Integer> colorData;


    @ViewInject(R.id.lv_count)
    ListView lv_count;
    CountMoneyAdapter adapter;
    @ViewInject(R.id.count)
    TextView count;
    @ViewInject(R.id.right_lv)
    ListView lvRight;
    CountMoneyAdapterRight adapterRight;

    @Event(type = View.OnClickListener.class,value = {R.id.tv_back})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_money);
        init();
        loadData();
    }

    private void initView( List<TotalBean.BodyBean.OrdersBean> mData) {
        adapter = new CountMoneyAdapter(mContext,mData);
        lv_count.setAdapter(adapter);
        adapterRight = new CountMoneyAdapterRight(mContext,mData);
        lvRight.setAdapter(adapterRight);
    }



    private void loadData() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "user/total")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        Gson gson = new Gson();
                        TotalBean javaBean =gson.fromJson(data.toString(),TotalBean.class);
                        if(javaBean.getStatus() ==401) {
                            ActivityUtils.toLogin(CountMoneyActivity.this,0);
                        }
                        mData = javaBean.getBody().getOrders();
                        if(mData!=null){
                            initCharts(mData);
                            initView(mData);
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

    private void initCharts(List<TotalBean.BodyBean.OrdersBean> mData) {
        chart = findViewById(R.id.chart);
        chart.setOnValueTouchListener(new ValueTouchListener());
        countData = new ArrayList<>();
        colorData = new ArrayList<>();
        for (int i =0;i<mData.size();i++){
            countData.add(mData.get(i).getCount());
            colorData.add(Color.parseColor(mData.get(i).getColor()));
        }
        generateData();
    }



    /**
     * 获取数据
     */
    private void setPieChartData() {
        for (int i = 0; i < countData.size(); i++) {
            SliceValue sliceValue = new SliceValue((float) countData.get(i), colorData.get(i));
            values.add(sliceValue);
        }
    }

    private void generateData() {
        setPieChartData();
        pieChardata = new PieChartData();
        chart.setViewportCalculationEnabled(true);//设置饼图自动适应大小
        pieChardata.setHasLabels(true);//显示表情
        pieChardata.setValues(values);//填充数据
        pieChardata.setHasLabels(hasLabels);//是否展示lable
        pieChardata.setValueLabelTextSize(8);//lable字体大小
        pieChardata.setHasLabelsOnlyForSelected(hasLabelForSelected);
        pieChardata.setHasLabelsOutside(hasLabelsOutside);
        pieChardata.setHasCenterCircle(hasCenterCircle);
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        chart.setPieChartData(pieChardata);
        chart.setCircleFillRatio(0.9f);//如果chart挤压lable显示不完全，设置这个属性
//        chart.setValueSelectionEnabled(true);//选择饼图某一块变大
        chart.setAlpha(0.9f);//设置透明度
//        chart.setCircleFillRatio(1f);//设置饼图大小

        if (isExploded) {
            pieChardata.setSlicesSpacing(24);
        }
        chart.setPieChartData(pieChardata);
    }

    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            Toast.makeText(mContext, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {

        }

    }

    /**
     * 初始化
     */
    private void init() {
        Intent intent = getIntent();
        String money = intent.getStringExtra("money");
        count.setText(money);
//        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);

    }
}
