/*
 * Copyright (C) 2017 WordPlat Open Source Project
 *
 *      https://wordplat.com/InteractiveKLineView/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.ionic.ylnewapp.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.wordplat.ikvstockchart.InteractiveKLineView;
import com.wordplat.ikvstockchart.KLineHandler;
import com.wordplat.ikvstockchart.compat.ViewUtils;
import com.wordplat.ikvstockchart.entry.Entry;
import com.wordplat.ikvstockchart.render.KLineRender;

/**
 * <p>InteractiveKLineLayout</p>
 * <p>Date: 2017/3/22</p>
 *
 */

public class CuInteractiveKLineLayout extends FrameLayout implements View.OnClickListener {
    private static final String TAG = "InteractiveKLineLayout";

    private Context context;

    private InteractiveKLineView kLineView;
    private KLineHandler kLineHandler;
    private KLineRender kLineRender;


    public CuInteractiveKLineLayout(Context context) {
        this(context, null);
    }

    public CuInteractiveKLineLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CuInteractiveKLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initUI(context, attrs, defStyleAttr);
    }

    private void initUI(Context context, AttributeSet attrs, int defStyleAttr) {
        kLineView = new InteractiveKLineView(context);
        kLineRender = (KLineRender) kLineView.getRender();
        kLineRender.setSizeColor(ViewUtils.getSizeColor(context, attrs, defStyleAttr));
        kLineView.setKLineHandler(new KLineHandler() {
            @Override
            public void onLeftRefresh() {
                if (kLineHandler != null) {
                    kLineHandler.onLeftRefresh();
                }
            }

            @Override
            public void onRightRefresh() {
                if (kLineHandler != null) {
                    kLineHandler.onRightRefresh();
                }
            }

            @Override
            public void onSingleTap(MotionEvent e, float x, float y) {
                if (kLineHandler != null) {
                    kLineHandler.onSingleTap(e, x, y);
                }

                onTabClick(x, y);
            }

            @Override
            public void onDoubleTap(MotionEvent e, float x, float y) {
                if (kLineHandler != null) {
                    kLineHandler.onDoubleTap(e, x, y);
                }
            }

            @Override
            public void onHighlight(Entry entry, int entryIndex, float x, float y) {
                if (kLineHandler != null) {
                    kLineHandler.onHighlight(entry, entryIndex, x, y);
                }
            }

            @Override
            public void onCancelHighlight() {
                if (kLineHandler != null) {
                    kLineHandler.onCancelHighlight();
                }
            }
        });

        addView(kLineView);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

//        But_Group = (RadioGroup) findViewById(R.id.But_Group);
//        MACD_But = (RadioButton) findViewById(R.id.MACD_But);
//        RSI_But = (RadioButton) findViewById(R.id.RSI_But);
//        KDJ_But = (RadioButton) findViewById(R.id.KDJ_But);
//        BOLL_But = (RadioButton) findViewById(R.id.BOLL_But);
//
//        MACD_But.setOnClickListener(this);
//        RSI_But.setOnClickListener(this);
//        KDJ_But.setOnClickListener(this);
//        BOLL_But.setOnClickListener(this);
//
//        showMACD();
    }

    public InteractiveKLineView getKLineView() {
        return kLineView;
    }

    public void setKLineHandler(KLineHandler kLineHandler) {
        this.kLineHandler = kLineHandler;
    }

    private void onTabClick(float x, float y) {
//        if (currentRect.contains(x, y)) {
//            if (macdIndex.isEnable()) {
//                showRSI();
//            } else if (rsiIndex.isEnable()) {
//                showKDJ();
//            } else if (kdjIndex.isEnable()) {
//                showBOLL();
//            } else {
//                showMACD();
//            }

            if (kLineHandler != null) {
                kLineHandler.onCancelHighlight();
            }

            kLineView.notifyDataSetChanged();
//        }
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();

//        if (id == R.id.MACD_But) {
//            showMACD();
//
//        } else if (id == R.id.RSI_But) {
//            showRSI();
//
//        } else if (id == R.id.KDJ_But) {
//            showKDJ();
//
//        } else if (id == R.id.BOLL_But) {
//            showBOLL();
//
//        }
//
//        if (kLineHandler != null) {
//            kLineHandler.onCancelHighlight();
//        }
//
//        kLineView.notifyDataSetChanged();
    }

//    public void showMACD() {
//        macdIndex.setEnable(true);
//        rsiIndex.setEnable(false);
//        kdjIndex.setEnable(false);
//        bollIndex.setEnable(false);
//
//        But_Group.clearCheck();
//        MACD_But.setChecked(true);
//
//        currentRect = macdIndex.getRect();
//    }
//
//    public void showRSI() {
//        macdIndex.setEnable(false);
//        rsiIndex.setEnable(true);
//        kdjIndex.setEnable(false);
//        bollIndex.setEnable(false);
//
//        But_Group.clearCheck();
//        RSI_But.setChecked(true);
//
//        currentRect = rsiIndex.getRect();
//    }
//
//    public void showKDJ() {
//        macdIndex.setEnable(false);
//        rsiIndex.setEnable(false);
//        kdjIndex.setEnable(true);
//        bollIndex.setEnable(false);
//
//        But_Group.clearCheck();
//        KDJ_But.setChecked(true);
//
//        currentRect = kdjIndex.getRect();
//    }
//
//    public void showBOLL() {
//        macdIndex.setEnable(false);
//        rsiIndex.setEnable(false);
//        kdjIndex.setEnable(false);
//        bollIndex.setEnable(true);
//
//        But_Group.clearCheck();
//        BOLL_But.setChecked(true);
//
//        currentRect = bollIndex.getRect();
//    }

//    public boolean isShownMACD() {
//        return macdIndex.isEnable();
//    }
//
//    public boolean isShownRSI() {
//        return rsiIndex.isEnable();
//    }
//
//    public boolean isShownKDJ() {
//        return kdjIndex.isEnable();
//    }
//
//    public boolean isShownBOLL() {
//        return bollIndex.isEnable();
//    }
}
