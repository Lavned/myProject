package io.ionic.ylnewapp.view.fragment;


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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.MyCoinsBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.wallet.SelectCurrency;
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
    ListAdapter adapter;

    Context mContext;
    List<MyCoinsBean.BodyBean> mData;
//    SlidingMenu mSlidingMenu;//侧滑菜单


    @Event(type = View.OnClickListener.class,value = {R.id.wallet_ed,R.id.add_biz})
    private void click(View v){
        switch (v.getId()){
            case R.id.wallet_ed :
//                if(mSlidingMenu == null){
//                    initSlide();
//                }else {
//                    mSlidingMenu.toggle();
//                }
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
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser){
        }else {
        }
    }


//    /**
//     * 初始化侧滑菜单
//     */
//    private void initSlide() {
//        mSlidingMenu = new SlidingMenu(getActivity());
//        mSlidingMenu.setMode(SlidingMenu.RIGHT);     //设置从左弹出/滑出SlidingMenu
////        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);   //设置占满屏幕
//        mSlidingMenu.attachToActivity(getActivity(),SlidingMenu.SLIDING_CONTENT);    //绑定到哪一个Activity对象
//        mSlidingMenu.setMenu(getshowView());                   //设置弹出的SlidingMenu的布局文件
//        mSlidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);       //
//        mSlidingMenu.toggle(true);
////
//    }

    /**
     * 右侧菜单弹出式事件
     * @return
     */
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
    private void initList(final List<MyCoinsBean.BodyBean> mData) {
        adapter = new ListAdapter(mContext,mData);
        if(adapter!=null)
            adapter.notifyDataSetChanged();
        lvWallet.setAdapter(adapter);
        lvWallet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),WalletDetailActivity.class);
                intent.putExtra("name",mData.get(position).getName()+"|"+mData.get(position).getIcon());
                startActivity(intent);
            }
        });
    }

    /**
     * 请求数据
     */
    private void loadData() {
        OkGo.<String>get(Constants.URL_BASE + "coins/coins" )
                .tag(this)
                .cacheTime(1000)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        MyCoinsBean javaBean =gson.fromJson(response.body().toString(),MyCoinsBean.class);
                        if(javaBean.getStatus() == 401){
                            ActivityUtils.toLogin(getActivity(),1);
                        }
                        mData = javaBean.getBody();
                        if(mData!= null){
                            initList(mData);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }
                });
    }

    /**
     * 内部适配器
     */
    class ListAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<MyCoinsBean.BodyBean> mData;

        public ListAdapter(Context mContext, List<MyCoinsBean.BodyBean> mData) {
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
             ViewHolder holder;
             MyCoinsBean.BodyBean item = mData.get(position);
            if (convertView == null) {
                holder = new  ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.wallet_items, parent, false);
                holder.tv_text = convertView.findViewById(R.id.name);
                holder.tv_price = convertView.findViewById(R.id.price);
                holder.tv_img = convertView.findViewById(R.id.wallet_icon);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_text.setText(item.getName());
            holder.tv_price.setText(item.getPrice()+"");
            Glide.with(mContext).load(item.getIcon()).into(holder.tv_img);
            return convertView;
        }

        //holder
        class ViewHolder {
            private TextView tv_text;
            private ImageView tv_img;
            private TextView tv_price;
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
        if(lvWallet!=null){
            loadData();
        }
        MobclickAgent.onResume(getActivity());
    }

}
