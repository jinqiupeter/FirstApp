package com.example.peterjin.firstapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by peter.jin on 2015/5/22.
 */

public class LostChild{
    private static final String TAG = "CHILD";
    public final static String EXTRA_MESSAGE_CHILD = "com.example.peterjin.firstapp.SINGLE_CHILD";
    static final int NAME_IDX = 0;
    static final int GENDER_IDX = 1;
    static final int ID_IDX = 2;
    static final int REG_TIME_IDX = 3;
    static final int DOB_IDX = 4;
    static final int LOST_TIME_IDX = 5;
    static final int LOST_LOC_IDX = 6;
    static final int DESC_IDX = 7;
    static final int HEIGHT_IDX = 8;
    static final int SZRSZD_IDX = 9;
    static final int OTHER_INFO_IDX = 10;
    static final int CATAGORY_IDX = 11;
    static final int IMAGE_IDX = 12;
    static final int MAX_IDX = 12;


    static final String[] keys = {"姓名", "性别", "寻亲编号", "注册时间", "出生日期", "失踪时间", "失踪地点",
            "寻亲者特征描述", "失踪时身高", "失踪人所在地", "其他资料", "寻亲类别", "image"};
    private HashMap<String, String> values = new HashMap<String, String>();

    public LostChild(){

    }

    public LostChild(JSONObject data) {
        for (String k: keys) {
            if (data.has(k)) {
                try {
                    values.put(k, data.getString(k));
                } catch (JSONException e) {
                    Log.e(TAG, "error parsing json object: " + e);
                }
            }
        }
    }

    public String[] getItem(int idx) {
        if (idx > MAX_IDX) {
            return null;
        }
        String[] item = new String[2];
        item[0] = getKey(idx);
        item[1] = getValue(idx);
        return item;
    }

    public String getKey(int idx) {
        if (idx > MAX_IDX) {
            return null;
        }
        return keys[idx];
    }

    public String getValue(int idx) {
        if (idx > MAX_IDX) {
            return null;
        }
        return values.get(keys[idx]);
    }
}
