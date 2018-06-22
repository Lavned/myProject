package io.ionic.ylnewapp.adpater.products;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.products.BTCBean;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.view.activity.product.ProductAIActivity;

/**
 * Created by lijianchang@yy.com on 2017/4/12.
 */

public class BTCAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BTCBean> datas;
    private Context context;
    private int normalType = 0;
    private int footType = 1;
    private int headerType = 2;
    private boolean hasMore = true;
    private boolean fadeTips = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public BTCAdapter(List<BTCBean> datas, Context context, boolean hasMore) {
        this.datas = datas;
        this.context = context;
        this.hasMore = hasMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == normalType) {
            return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.two_recycle_items, null));
        } else if(viewType == footType){
            return new FootHolder(LayoutInflater.from(context).inflate(R.layout.footview, null));
        }else{
            return new HeaderHolder(LayoutInflater.from(context).inflate(R.layout.headerview, null));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderHolder) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
            headerHolder.textViewHeader.setText("");
        } else if (holder instanceof NormalHolder) {
           final BTCBean item = datas.get(position-1);
            ((NormalHolder) holder).name.setText(item.getName());
            ((NormalHolder) holder).content1.setText(item.getContent().get(0));
            ((NormalHolder) holder).content2.setText(item.getContent().get(1));
            ((NormalHolder) holder).number.setText(item.getRate());
            ((NormalHolder) holder).day.setText(item.getWeek());
            ((NormalHolder) holder).btnVal.setText(""+item.getBtn());
            ((NormalHolder) holder).content3.setText(item.getTitleRate());
            ((NormalHolder) holder).content4.setText(""+item.getTitleWeek());
            ((NormalHolder) holder).btnVal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceUtils.setPrefString(context,"pid",item.getPid());
                    PreferenceUtils.setPrefString(context,"orate",item.getRate());
                    PreferenceUtils.setPrefString(context,"oname",item.getName());
                    PreferenceUtils.setPrefString(context,"oweek",item.getWeek());
                    PreferenceUtils.setPrefString(context,"KEY",item.getKey());
                    context.startActivity(new Intent(context, ProductAIActivity.class));
                }
            });

        } else {
            ((FootHolder) holder).tips.setVisibility(View.VISIBLE);
            if (hasMore == true) {
                fadeTips = false;
                if (datas.size() > 0) {
                    ((FootHolder) holder).tips.setText("正在加载更多...");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((FootHolder) holder).tips.setVisibility(View.GONE);
                        }
                    }, 500);
                }
            } else {
                if (datas.size() > 0) {
                    ((FootHolder) holder).tips.setText("没有更多数据了");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((FootHolder) holder).tips.setVisibility(View.GONE);
                            fadeTips = true;
                            hasMore = true;
                        }
                    }, 500);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return datas.size() +2 ;
    }

    public int getRealLastPosition() {
        return datas.size();
    }


    public void updateList(List<BTCBean> newDatas, boolean hasMore) {
        if (newDatas != null) {
            datas.addAll(newDatas);
        }
        this.hasMore = hasMore;
        notifyDataSetChanged();
    }


    /*头部Item*/
    class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView textViewHeader;

        public HeaderHolder(View itemView) {
            super(itemView);
            textViewHeader = (TextView) itemView.findViewById(R.id.text1sss);
        }
    }

    class NormalHolder extends RecyclerView.ViewHolder {
        private TextView name,content1,content2,number,day,btnVal,content3,content4;

        public NormalHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_name);
            content1 = (TextView) itemView.findViewById(R.id.item_content_1);
            content2 = (TextView) itemView.findViewById(R.id.item_content_2);
            number = (TextView) itemView.findViewById(R.id.item_number);
            day = (TextView) itemView.findViewById(R.id.tv_day);
            btnVal = itemView.findViewById(R.id.item_btn);
            content3 = (TextView) itemView.findViewById(R.id.content_3);
            content4 = (TextView) itemView.findViewById(R.id.content_4);
        }
    }

    class FootHolder extends RecyclerView.ViewHolder {
        private TextView tips;

        public FootHolder(View itemView) {
            super(itemView);
            tips = (TextView) itemView.findViewById(R.id.tips);
        }
    }

    public boolean isFadeTips() {
        return fadeTips;
    }

    public void resetDatas() {
        datas = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return headerType;
        }
        else if (position == getItemCount() - 1) {
            return footType;
        } else {
            return normalType;
        }
    }
}
