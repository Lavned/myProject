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

package io.ionic.ylnewapp.utils;

import com.google.gson.Gson;
import com.wordplat.ikvstockchart.entry.Entry;
import com.wordplat.ikvstockchart.entry.EntrySet;

import java.util.List;

import io.ionic.ylnewapp.bean.market.ZxDetailBean;

/**
 * <p>股票测试数据</p>
 * <p>Date: 2017/3/2</p>
 *
 * @author afon
 */

public class StockDataUtil {


    public static EntrySet parseKLineData(String data) {
        final EntrySet entrySet = new EntrySet();

        Gson gson = new Gson();
        ZxDetailBean javaBean = gson.fromJson(data, ZxDetailBean.class);
        List<ZxDetailBean.DataBean> mData = javaBean.getData();
//        final String[] candleDatas = data.split(",");

        for (ZxDetailBean.DataBean candleData : mData) {
//            String[] v = candleData.split("[|]");

            float open = Float.parseFloat(candleData.getOpen()+"");
            float high = Float.parseFloat(candleData.getHighest()+"");
            float low = Float.parseFloat(candleData.getLowest()+"");
            float close = Float.parseFloat(candleData.getClose()+"");

            String time = DateUtil.getDTofSystem(candleData.getIntervalDate()+"");

            entrySet.addEntry(new Entry(open, high, low, close, 0, time));
        }

        return entrySet;
    }

    public static EntrySet parseTimeLine(String data) {
        final EntrySet entrySet = new EntrySet();

        return entrySet;
    }
}
