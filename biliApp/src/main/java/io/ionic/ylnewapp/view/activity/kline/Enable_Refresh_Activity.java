package io.ionic.ylnewapp.view.activity.kline;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wordplat.ikvstockchart.KLineHandler;
import com.wordplat.ikvstockchart.compat.PerformenceAnalyser;
import com.wordplat.ikvstockchart.entry.Entry;
import com.wordplat.ikvstockchart.entry.EntrySet;
import com.wordplat.ikvstockchart.entry.StockDataTest;
import com.wordplat.ikvstockchart.render.KLineRender;

import org.xutils.view.annotation.ViewInject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.custom.CuInteractiveKLineLayout;
import io.ionic.ylnewapp.view.base.BaseActivity;

/**
 * <p>Enable_Left_And_Right_Refresh_Activity</p>
 * <p>Date: 2017/3/10</p>
 *
 * @author afon
 */

public class Enable_Refresh_Activity extends BaseActivity {

    CuInteractiveKLineLayout kLineLayout ;
    @ViewInject(R.id.Left_Loading_Image) private ImageView Left_Loading_Image = null;
    @ViewInject(R.id.Right_Loading_Image) private ImageView Right_Loading_Image = null;

    private EntrySet entrySet;
    private int loadStartPos = 5500;
    private int loadEndPos = 6000;
    private int loadCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lr_refresh);
        kLineLayout = findViewById(R.id.myLineLayout);
        initUI();
        loadKLineData();
    }

    private void initUI() {
        kLineLayout.setKLineHandler(new KLineHandler() {
            @Override
            public void onHighlight(Entry entry, int entryIndex, float x, float y) {
            }

            @Override
            public void onCancelHighlight() {
            }

            @Override
            public void onSingleTap(MotionEvent e, float x, float y) {
                final KLineRender kLineRender = (KLineRender) kLineLayout.getKLineView().getRender();

                if (kLineRender.getKLineRect().contains(x, y)) {
                    Toast.makeText(mContext, "single tab [" + x + ", " + y + "]", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDoubleTap(MotionEvent e, float x, float y) {
                final KLineRender kLineRender = (KLineRender) kLineLayout.getKLineView().getRender();
                if (kLineRender.getKLineRect().contains(x, y)) {
                    kLineRender.zoomIn(x, y);
                }
            }

            @Override
            public void onLeftRefresh() {
                Left_Loading_Image.setVisibility(View.VISIBLE);
                ((Animatable) Left_Loading_Image.getDrawable()).start();
                // 模拟耗时
                kLineLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Left_Loading_Image.setVisibility(View.GONE);
                        ((Animatable) Left_Loading_Image.getDrawable()).stop();

                        List<Entry> entries = insertEntries();

                        kLineLayout.getKLineView().getRender().getEntrySet().insertFirst(entries);
                        kLineLayout.getKLineView().notifyDataSetChanged();
                        kLineLayout.getKLineView().refreshComplete(entries.size() > 0);

                        if (entries.size() == 0) {
                            Toast.makeText(mContext, "已经到达最左边了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1000);
            }

            @Override
            public void onRightRefresh() {
                Right_Loading_Image.setVisibility(View.VISIBLE);
                ((Animatable) Right_Loading_Image.getDrawable()).start();
                // 模拟耗时
                kLineLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Right_Loading_Image.setVisibility(View.GONE);
                        ((Animatable) Right_Loading_Image.getDrawable()).start();

                        List<Entry> entries = addEntries();

                        kLineLayout.getKLineView().getRender().getEntrySet().addEntries(entries);
                        kLineLayout.getKLineView().notifyDataSetChanged();
                        kLineLayout.getKLineView().refreshComplete(entries.size() > 0);

                        if (entries.size() == 0) {
                            Toast.makeText(mContext, "已经到达最右边了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1000);
            }
        });
    }

    private SpannableString getSpannableString(String str, int partColor0, int partColor1, int partColor2) {
        String[] splitString = str.split("[●]");
        SpannableString spanString = new SpannableString(str);

        int pos0 = splitString[0].length();
        int pos1 = pos0 + splitString[1].length() + 1;
        int end = str.length();

        spanString.setSpan(new ForegroundColorSpan(partColor0),
                pos0, pos1, SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);

        if (splitString.length > 2) {
            int pos2 = pos1 + splitString[2].length() + 1;

            spanString.setSpan(new ForegroundColorSpan(partColor1),
                    pos1, pos2, SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);

            spanString.setSpan(new ForegroundColorSpan(partColor2),
                    pos2, end, SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
        } else {
            spanString.setSpan(new ForegroundColorSpan(partColor1),
                    pos1, end, SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
        }

        return spanString;
    }

    private void loadKLineData() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                PerformenceAnalyser.getInstance().addWatcher();

                String kLineData = "";
                try {
                    InputStream in = getResources().getAssets().open("kline1.txt");
                    int length = in.available();
                    byte[] buffer = new byte[length];
                    in.read(buffer);
                    kLineData = new String(buffer, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                PerformenceAnalyser.getInstance().addWatcher();

                entrySet = StockDataTest.parseKLineData(kLineData);

                PerformenceAnalyser.getInstance().addWatcher();

                entrySet.computeStockIndex();

                PerformenceAnalyser.getInstance().addWatcher();

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                kLineLayout.getKLineView().setEntrySet(loadFirst());

                PerformenceAnalyser.getInstance().addWatcher();

                kLineLayout.getKLineView().notifyDataSetChanged();

                PerformenceAnalyser.getInstance().addWatcher();
            }
        }.execute();
    }

    private EntrySet loadFirst() {
        EntrySet set = new EntrySet();

        for (int i = loadStartPos ; i < loadEndPos ; i++) {
            set.addEntry(entrySet.getEntryList().get(i));
        }
        return set;
    }

    private List<Entry> addEntries() {
        List<Entry> entries = new ArrayList<>();

        int addCount = 0;
//        for (int i = loadEndPos ; i < loadEndPos + loadCount && i < entrySet.getEntryList().size() ; i++) {
            addCount++;
        Entry jjs= new Entry(1000.10f,8000.10f,5000.10f,9000.10f,0,"");
            entries.add(jjs);
//        }
        loadEndPos += addCount;

        return entries;
    }

    private List<Entry> insertEntries() {
        List<Entry> entries = new ArrayList<>();

        int insertCount = 0;
        for (int i = loadStartPos ; i > loadStartPos - loadCount && i > -1 ; i--) {
            insertCount++;

            entries.add(entrySet.getEntryList().get(i));
        }
        loadStartPos -= insertCount;

        return entries;
    }

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, Enable_Refresh_Activity.class);
        return intent;
    }
}
