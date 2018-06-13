package io.ionic.ylnewapp.view.market;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.ionic.ylnewapp.R;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class MarketFragment5 extends Fragment {

    public MarketFragment5() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.market_frag_05, container, false);
        return rootView;
    }
}