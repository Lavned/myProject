package io.ionic.ylnewapp.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.view.base.BaseFragment;
import io.ionic.ylnewapp.view.market.MarketFragment1;
import io.ionic.ylnewapp.view.market.MarketFragment2;
import io.ionic.ylnewapp.view.market.MarketFragment3;
import io.ionic.ylnewapp.view.market.MarketFragment4;
import io.ionic.ylnewapp.view.market.MarketFragment5;


/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_three)
public class FragmentThree extends BaseFragment {

    @ViewInject(R.id.text_1)
    TextView text_1;
    @ViewInject(R.id.text_2)
    TextView text_2;
    @ViewInject(R.id.text_3)
    TextView text_3;
    @ViewInject(R.id.text_4)
    TextView text_4;
    @ViewInject(R.id.text_5)
    TextView text_5;



    @Event(type = View.OnClickListener.class, value = {R.id.text_1,R.id.text_2,R.id.text_3,R.id.text_4,R.id.text_5})
    private void click(View v) {
        clearbg();
        switch (v.getId()){
            case R.id.text_1 :
                text_1.setTextColor(getActivity().getColor(R.color.main));
                text_1.setBackgroundResource(R.mipmap.choosebg);
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.container, new MarketFragment1()).commit();
                break;
            case R.id.text_2:
                text_2.setTextColor(getActivity().getColor(R.color.main));
                text_2.setBackgroundResource(R.mipmap.choosebg);
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.container, new MarketFragment2()).commit();
                break;
            case R.id.text_3 :
                text_3.setTextColor(getActivity().getColor(R.color.main));
                text_3.setBackgroundResource(R.mipmap.choosebg);
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.container, new MarketFragment3()).commit();
                break;
            case R.id.text_4:
                text_4.setTextColor(getActivity().getColor(R.color.main));
                text_4.setBackgroundResource(R.mipmap.choosebg);
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.container, new MarketFragment4()).commit();
                break;
            case R.id.text_5 :
                text_5.setTextColor(getActivity().getColor(R.color.main));
                text_5.setBackgroundResource(R.mipmap.choosebg);
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.container, new MarketFragment5()).commit();
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new MarketFragment1()).commit();
        return view;

    }

    //控制页面展示
    private void clearbg() {
        text_1.setTextColor(getActivity().getColor(R.color.black_1));
        text_1.setBackground(null);
        text_2.setTextColor(getActivity().getColor(R.color.black_1));
        text_2.setBackground(null);
        text_3.setTextColor(getActivity().getColor(R.color.black_1));
        text_3.setBackground(null);
        text_4.setTextColor(getActivity().getColor(R.color.black_1));
        text_4.setBackground(null);
        text_5.setTextColor(getActivity().getColor(R.color.black_1));
        text_5.setBackground(null);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if(mSocket!=null){
//            mSocket.disconnect();
//            mSocket.off("ticker", onNewMessage);
//        }
    }

}

