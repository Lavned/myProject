package io.ionic.ylnewapp.view.activity.market;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.market.MarketFragAdapter3;

/**
 * Created by mogojing on 2018/6/12/0012.
 */
@ContentView(R.layout.market_frag_03)//加载布局
public class MarketFragment3 extends Fragment {

    public MarketFragment3() {
    }

    @ViewInject(R.id.top_gv)
    GridView gvTop;
    @ViewInject(R.id.bottom_gv)
    GridView gvBottom;

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
        mData = new ArrayList<>();
        mData.add("ggg ");
        mData.add("hhh");
        mData.add("ggg ");
        mData.add("hhh");
        mData.add("ggg ");
        mData.add("hhh");
        adapter = new MarketFragAdapter3(getContext(), mData);
        gvTop.setAdapter(adapter);
        gvBottom.setAdapter(adapter);
    }
}