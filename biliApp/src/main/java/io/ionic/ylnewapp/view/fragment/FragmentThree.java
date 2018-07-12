package io.ionic.ylnewapp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.view.base.BaseFragment;
import io.ionic.ylnewapp.view.activity.market.MarketFragment1;
import io.ionic.ylnewapp.view.activity.market.MarketFragment2;
import io.ionic.ylnewapp.view.activity.market.MarketSearchActivity;

import static io.ionic.ylnewapp.view.activity.market.MarketFragment2.mSocket;
import static io.ionic.ylnewapp.view.activity.market.MarketFragment1.mSockets;


/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_three)
public class FragmentThree extends BaseFragment {

    @ViewInject(R.id.text_1)
    TextView text_1;
    @ViewInject(R.id.text_2)
    TextView text_2;
//    @ViewInject(R.id.text_3)
//    TextView text_3;
//    @ViewInject(R.id.text_4)
//    TextView text_4;
//    @ViewInject(R.id.text_5)
//    TextView text_5;,R.id.text_3,R.id.text_4,R.id.text_5
    @ViewInject(R.id.textView3)
    ImageView search;



    @Event(type = View.OnClickListener.class, value = {R.id.text_1,R.id.text_2,R.id.textView3})
    private void click(View v) {
        switch (v.getId()){
            case R.id.text_1 :
                clearbg();
                search.setVisibility(View.VISIBLE);
                setSelectText(text_1);
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.container, new MarketFragment1()).commit();
                break;
            case R.id.text_2:
                clearbg();
                search.setVisibility(View.GONE);
                setSelectText(text_2);
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.container, new MarketFragment2()).commit();
                break;
            case R.id.textView3:
                startActivity(new Intent(getActivity(), MarketSearchActivity.class));
//            case R.id.text_3 :
//                search.setVisibility(View.GONE);
//                setSelectText(text_3);
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.container, new MarketFragment3()).commit();
//                break;
//            case R.id.text_4:
//                search.setVisibility(View.GONE);
//                setSelectText(text_4);
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.container, new MarketFragment4()).commit();
//                break;
//            case R.id.text_5 :
//                search.setVisibility(View.GONE);
//                setSelectText(text_5);
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.container, new MarketFragment5()).commit();
//                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new MarketFragment1()).commit();
        return view;

    }



    /**
     * 选中事件
     * @param tv
     */
    private void setSelectText(TextView tv){
        tv.setTextColor(getActivity().getColor(R.color.main));
        tv.setBackgroundResource(R.mipmap.choosebg);
    }

    //控制页面展示
    private void clearbg() {
        text_1.setTextColor(getActivity().getColor(R.color.black_1));
        text_1.setBackground(null);
        text_2.setTextColor(getActivity().getColor(R.color.black_1));
        text_2.setBackground(null);
//        text_3.setTextColor(getActivity().getColor(R.color.black_1));
//        text_3.setBackground(null);
//        text_4.setTextColor(getActivity().getColor(R.color.black_1));
//        text_4.setBackground(null);
//        text_5.setTextColor(getActivity().getColor(R.color.black_1));
//        text_5.setBackground(null);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("socketlog","我关啦lllalalallllllllll");
        if(mSocket!=null){
            mSocket.disconnect();
        }
        if(mSockets!=null){
            mSockets.disconnect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }


    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
    }

}

