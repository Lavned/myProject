package io.ionic.ylnewapp.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.view.activity.wallet.SelectCurrency;
import io.ionic.ylnewapp.view.activity.wallet.WalletAdAddActivity;
import io.ionic.ylnewapp.view.activity.wallet.WalletDetailActivity;
import io.ionic.ylnewapp.view.activity.wallet.WalletManaActivity;
import io.ionic.ylnewapp.view.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_wallet)
public class FragmentWal extends BaseFragment {


    @ViewInject(R.id.wallet_lv)
    ListView lvWallet;

    Context mContext;
    List<String> mData;

    SlidingMenu mSlidingMenu;//侧滑菜单


    @Event(type = View.OnClickListener.class,value = {R.id.wallet_ed,R.id.add_biz})
    private void click(View v){
        switch (v.getId()){
            case R.id.wallet_ed :
                if(mSlidingMenu == null){
                    initSlide();
                }else {
                    mSlidingMenu.toggle();
                }
                break;
            case R.id.add_biz:
                startActivity(new Intent(mContext,SelectCurrency.class));
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        mContext = getActivity();
        init();
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser){
            Log.i("-----------","+++++++++kj");
//            mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            Log.i("-----------","+++++++++bkj");
        }
    }


    /**
     * 初始化侧滑菜单
     */
    private void initSlide() {
        mSlidingMenu = new SlidingMenu(getActivity());
        mSlidingMenu.setMode(SlidingMenu.RIGHT);     //设置从左弹出/滑出SlidingMenu
//        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);   //设置占满屏幕
        mSlidingMenu.attachToActivity(getActivity(),SlidingMenu.SLIDING_CONTENT);    //绑定到哪一个Activity对象
        mSlidingMenu.setMenu(getshowView());                   //设置弹出的SlidingMenu的布局文件
        mSlidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);       //
        mSlidingMenu.toggle(true);
//
    }

    public View getshowView(){
        View view = getActivity().getLayoutInflater().inflate(R.layout.slidemenu, null);
        TextView btn = view.findViewById(R.id.wallet_address);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WalletManaActivity.class));
            }
        });
        return view;
    }

    /**
     * 初始化
     */
    private void init() {
        mData = new ArrayList<>();
        mData.add("ETF");
        mData.add("BNCN");
        lvWallet.setAdapter(new  ListAdapter(mContext,mData));
        lvWallet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),WalletDetailActivity.class));
            }
        });

    }

    /**
     * 内部适配器
     */
    class ListAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<String> mData;

        public ListAdapter(Context mContext, List<String> mData) {
            mLayoutInflater = LayoutInflater.from(mContext);
            this.mContext = mContext;
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
             ListAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new  ListAdapter.ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.wallet_items, parent, false);
                holder.tv_text = convertView.findViewById(R.id.name);
                convertView.setTag(holder);
            } else {
                holder = ( ListAdapter.ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        //holder
        class ViewHolder {
            private TextView tv_text;
            private ImageView tv_img;
        }
    }


}
