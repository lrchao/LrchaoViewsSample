package com.lrchao.views;

import android.content.Context;

import com.lrchao.utils.LrchaoUtils;

/**
 * Description: LrchaoViews 必须先初始化
 *
 * @author lrc19860926@gmail.com
 * @date 2017/2/10 上午10:48
 */

public class LrchaoViews {

    private static LrchaoViews sInstance;

    private Context mContext;

    private LrchaoViews() {
    }

    public static LrchaoViews getInstance() {
        synchronized (LrchaoViews.class) {
            if (sInstance == null) {
                sInstance = new LrchaoViews();
            }
        }
        return sInstance;
    }

    /**
     * 在
     *
     * @param context Application
     */
    public void init(Context context) {
        mContext = context;
        LrchaoUtils.getInstance().init(mContext);
    }

    public Context getContext() {
        return mContext;
    }
}
