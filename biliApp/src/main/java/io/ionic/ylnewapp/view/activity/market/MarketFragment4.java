package io.ionic.ylnewapp.view.activity.market;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.market.MarketFragAdapter3;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by mogojing on 2018/6/12/0012.
 */
@ContentView(R.layout.market_frag_04)//加载布局
public class MarketFragment4 extends Fragment {

    public MarketFragment4() {
    }

    @ViewInject(R.id.bottom_gv2)
    GridView gvBottom;
    @ViewInject(R.id.fragment_chart)
    ColumnChartView chart;


    private ColumnChartData data;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;

    MarketFragAdapter3 adapter;
    List<String> mData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = x.view().inject(this, inflater, container);
        initView();
        return view;
    }

    //数据初始化
    private void initView() {
        initChart();
//        mData = new ArrayList<>();
//        mData.add("eee ");
//        mData.add("rrrr");
//        mData.add("eee ");
//        mData.add("rrrr");
//        mData.add("eee ");
//        mData.add("rrrr");
//        adapter = new MarketFragAdapter3(getContext(), mData);
//        gvBottom.setAdapter(adapter);
    }

    private void initChart() {
        chart.setOnValueTouchListener(new ValueTouchListener());
        generateDefaultData();
        prepareDataAnimation();
        chart.startDataAnimation();

    }


    private void generateDefaultData() {
        int numSubcolumns = 1;
        int numColumns = 8;
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
            }

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        chart.setColumnChartData(data);

    }

    private void prepareDataAnimation() {
        for (Column column : data.getColumns()) {
            for (SubcolumnValue value : column.getValues()) {
                value.setTarget((float) Math.random() * 100);
            }
        }
    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {

        }

    }

}