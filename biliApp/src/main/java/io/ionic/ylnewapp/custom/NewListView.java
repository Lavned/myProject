package io.ionic.ylnewapp.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by mogojing on 2018/6/23/0023.
 */

public class NewListView extends ListView {
        public NewListView(Context context) {
            super(context);
        }

        public NewListView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public NewListView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        public NewListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }


        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }
}
