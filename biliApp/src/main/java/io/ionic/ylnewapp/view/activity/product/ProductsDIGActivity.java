package io.ionic.ylnewapp.view.activity.product;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.products.DigoneAdapter;
import io.ionic.ylnewapp.bean.products.ProductsDeatilBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.custom.MyListView;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class ProductsDIGActivity extends BaseActivity {



    @ViewInject(R.id.showtext1)
    LinearLayout showtext1;
    @ViewInject(R.id.showtext2)
    LinearLayout showtext2;
    @ViewInject(R.id.showtext3)
    LinearLayout showtext3;

    @ViewInject(R.id.Bbtc)
    TextView bbtc;
    @ViewInject(R.id.Betf)
    TextView betf;
    @ViewInject(R.id.bbncn)
    TextView bbncn;
    @ViewInject(R.id.b_number)
    EditText number;
    @ViewInject(R.id.de_name)
    TextView de_name;
    @ViewInject(R.id.de_rate)
    TextView de_rate;
    @ViewInject(R.id.de_btn1)
    TextView de_btn1;
    @ViewInject(R.id.de_btn2)
    TextView de_btn2;

    @ViewInject(R.id.tab_btc)
    TextView tab_btc;
    @ViewInject(R.id.tab_eth)
    TextView tab_eth;

    @ViewInject(R.id.tabbtc_line)
    View tab_btcline;
    @ViewInject(R.id.tabeth_line)
    View tab_ethline;

    @ViewInject(R.id.lv_data)
    MyListView lvData;
    @ViewInject(R.id.scrollView)
    ScrollView scrollView;
    @ViewInject(R.id.chart)
    LineChartView chart;

    DigoneAdapter mAdapter;

    public static Activity activity;

    @Event(type = View.OnClickListener.class,value = {R.id.toggle1,R.id.toggle2,R.id.toggle3
    ,R.id.Betf,R.id.Bbtc,R.id.bbncn,R.id.tab_btc,R.id.tab_eth,R.id.back_1,R.id.sumbit_btn})
    private void Click(View v){
        closeView();
        switch (v.getId()){
            case R.id.sumbit_btn:
                if(!number.getText().toString().trim().equals("")){
                    PreferenceUtils.setPrefString(mContext,"bnum",number.getText().toString().trim());
                    startActivity(new Intent(ProductsDIGActivity.this,DIGDetailActivity.class));
                }else {
                    T.showShort("起投金额不能为空");
                }
                break;
            case R.id.back_1:
                finish();
            case R.id.toggle1:
                showtext1.setVisibility(View.VISIBLE);
                break;
            case R.id.toggle2:
                showtext2.setVisibility(View.VISIBLE);
                break;
            case R.id.toggle3:
                showtext3.setVisibility(View.VISIBLE);
                break;
            case R.id.Betf:
                clearbg(1);
                betf.setBackgroundResource(R.mipmap.selectmain);
                betf.setTextColor(getColor(R.color.main));
                number.setHint("请输入数量(最小投资币额为1)");
                PreferenceUtils.setPrefString(mContext,"bz","ETH");
                break;
            case R.id.Bbtc:
                clearbg(1);
                bbtc.setBackgroundResource(R.mipmap.selectmain);
                bbtc.setTextColor(getColor(R.color.main));
                number.setHint("请输入数量(最小投资币额为1)");
                PreferenceUtils.setPrefString(mContext,"bz","BTC");
                break;
            case R.id.bbncn:
                clearbg(1);
                number.setHint("请输入数量(最小投资币额为100000)");
                bbncn.setBackgroundResource(R.mipmap.selectmain);
                bbncn.setTextColor(getColor(R.color.main));
                PreferenceUtils.setPrefString(mContext,"bz","BNCN");
                break;
            case R.id.tab_btc:
                clearbg(2);
                tab_btcline.setVisibility(View.VISIBLE);
                tab_btc.setTextColor(getColor(R.color.main));
                break;
            case R.id.tab_eth:
                clearbg(2);
                tab_ethline.setVisibility(View.VISIBLE);
                tab_eth.setTextColor(getColor(R.color.main));
                break;
        }
    }


    private List<String> mData = new ArrayList<String>(Arrays.asList("HongYang", "GuoLin", "RenYuGang", "Jiatao","LiZhao"));
    int[] scores = {10, 42, 43, 33, 10, 40, 22, 18, 40, 20};//图表的数据点
    private LineChartData data;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();   //x轴方向的坐标数据
    private List<AxisValue> mAxisYValues = new ArrayList<AxisValue>();  //y轴方向的坐标数据


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_item);
        ImmersionBar.with(this).init();
        activity = this;
        initView();
    }

    private void initView() {
        setView();
        iitChart();
        initListView();
    }

    private void setView(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        de_name.setText(bundle.getString("name"));
        de_rate.setText(bundle.getString("rate"));
        de_btn1.setText(bundle.getString("btn1"));
        de_btn2.setText(bundle.getString("btn2"));
        PreferenceUtils.setPrefString(mContext,"bz","ETH");
        PreferenceUtils.setPrefString(mContext,"brate",bundle.getString("rate"));
        PreferenceUtils.setPrefString(mContext,"btname",bundle.getString("name"));
    }
    /**
     * chart
     */
    private void iitChart() {
        chart.setInteractive(true);
        chart.setZoomType(ZoomType.HORIZONTAL);
        chart.setMaxZoom((float) 2);//最大方法比例
        chart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        chart.setVisibility(View.VISIBLE);
        chart.setViewportCalculationEnabled(false);
        initXY();
        generateData();
        resetViewport();
    }

    /**
     * view
     */
    private void initListView() {
        mAdapter = new DigoneAdapter(mContext, mData);
        lvData.setAdapter(mAdapter);
        lvData.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP) {
                    scrollView.requestDisallowInterceptTouchEvent(false);
                }else {
                    scrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }

    /**
     * chart相关
     */
    private void initXY() {
        //设置x轴上面的数据
        mAxisXValues.add(new AxisValue(0).setLabel("001"));
        mAxisXValues.add(new AxisValue(1).setLabel("002"));
        mAxisXValues.add(new AxisValue(2).setLabel("003"));
        mAxisXValues.add(new AxisValue(3).setLabel("004"));
        mAxisXValues.add(new AxisValue(4).setLabel("005"));
        mAxisXValues.add(new AxisValue(5).setLabel("006"));

        //设置Y轴上面的数据
        for (int i = 0; i < 50; i += 10) {
            mAxisYValues.add(new AxisValue(i).setLabel("" + i));
        }
    }
    /**
     * chart相关
     */
    private void resetViewport() {
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = 50;
        v.left = 0;
        v.right = mAxisXValues.size();
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }
    /**
     * chart相关
     */
    private void generateData() {
        List<Line> lines = new ArrayList<Line>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int j = 0; j < scores.length; ++j) {
            values.add(new PointValue(j, scores[j]));
        }
        Line line = new Line(values);
        line.setColor(Color.parseColor("#FEA620"));
        line.setShape(ValueShape.CIRCLE);
        line.setCubic(true);//曲线是否平滑，即是曲线还是折线
        line.setHasLabels(false);
        line.setHasLabelsOnlyForSelected(false);
        line.setHasLines(true);
        line.setHasPoints(true);
        line.setPointColor(Color.parseColor("#FEA620"));
        lines.add(line);
        data = new LineChartData(lines);
        if (hasAxes) {
            Axis axisX = new Axis().setHasTiltedLabels(true);// 设置X轴文字向左旋转45度  ;
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
//                        axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            //设置x轴的坐标
            axisX.setValues(mAxisXValues);
            //设置Y轴的坐标
            axisY.setValues(mAxisYValues);
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
        //设置Y轴的坐标
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);
    }



    //关闭下方菜单
    public  void closeView(){
        showtext1.setVisibility(View.GONE);
        showtext2.setVisibility(View.GONE);
        showtext3.setVisibility(View.GONE);
    }
    //关闭下方菜单
    public  void clearbg(int type){
        switch (type){
            case 1:
                betf.setBackgroundResource(R.mipmap.selectblack);
                betf.setTextColor(getColor(R.color.black33));
                bbtc.setBackgroundResource(R.mipmap.selectblack);
                bbtc.setTextColor(getColor(R.color.black33));
                bbncn.setBackgroundResource(R.mipmap.selectblack);
                bbncn.setTextColor(getColor(R.color.black33));
                break;
            case 2:
                tab_btcline.setVisibility(View.GONE);
                tab_btc.setTextColor(getColor(R.color.black_1));
                tab_ethline.setVisibility(View.GONE);
                tab_eth.setTextColor(getColor(R.color.black_1));
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //释放沉浸式Title,必须调用该方法，防止内存泄漏
    }
}
