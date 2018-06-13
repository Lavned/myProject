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
    private int[] data = {21, 20, 9, 2, 19,20,70};
    private int[] colorData = {Color.parseColor("#46c099"),
            Color.parseColor("#8180ff"),
            Color.parseColor("#50b2ef"),
            Color.parseColor("#aad8fb"),
            Color.parseColor("#f7d878"),
            Color.parseColor("#c8e9a0"),
            Color.parseColor("#f4a277")};

    private boolean hasLabels = true;
    private boolean hasLabelsOutside = true;
    private boolean hasCenterCircle = false;
    private boolean isExploded = false;
    private boolean hasLabelForSelected = false;


    @ViewInject(R.id.lv_count)
    ListView lv_count;
    CountMoneyAdapter adapter;
    List<String> mData;
    @ViewInject(R.id.count)
    TextView count;

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
        initCharts();
        initView();
//        loadData();
    }

    private void initView() {
        mData = new ArrayList<>();
        mData.add("法币余额");
        mData.add("数字货币余额");
        mData.add("法币余额");
        mData.add("数字货币余额");
        mData.add("法币余额");
        mData.add("数字货币余额");
        mData.add("法币余额");
        adapter = new CountMoneyAdapter(mContext,mData);
        lv_count.setAdapter(adapter);
    }



    private void loadData() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "user/balance")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        Gson gson = new Gson();
//                        BalanceBean  javaBean =gson.fromJson(data.toString(),BalanceBean.class);
//                        if(javaBean.getStatus() ==401) {
//                            ActivityUtils.toLogin(MoneyDetailActivity.this);
//                        }
//                        mData = javaBean.getBody();
//                        if(mData!=null)
//                            initView(mData);
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

    private void initCharts() {
        chart = findViewById(R.id.chart);
        chart.setOnValueTouchListener(new ValueTouchListener());
        generateData();
    }



    /**
     * 获取数据
     */
    private void setPieChartData() {
        for (int i = 0; i < data.length; ++i) {
            SliceValue sliceValue = new SliceValue((float) data[i], colorData[i]);
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
