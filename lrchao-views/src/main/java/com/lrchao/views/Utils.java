package com.lrchao.views;

import android.content.Context;
import android.content.Intent;

import com.lrchao.views.activity.PhotoPickerActivity;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPagerActivity;

/**
 * Description: 工具类
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/19 上午11:36
 */

public final class Utils {

    private Utils() {
    }

    /**
     * @param total  总数
     * @param column 列数
     * @return 总行数
     */
    public static int getTotalRowNum(int total, int column) {
        float rowAbs = (float) total / column;
        return (int) Math.ceil(rowAbs);
    }

    /**
     * @param position 当前的position
     * @param column   列数
     * @return position所在行数
     */
    public static int getCurrentRowNum(int position, int column) {
        float currentRowABS = (float) position / column;
        return (int) (Math.floor(currentRowABS) + 1);
    }

    /**
     * 跳转到图片选取的页面
     */
    public static void navToPhotoPickerActivity(int count) {
        Context context = LrchaoViews.getInstance().getContext();
        Intent intent = PhotoPickerActivity.getCallingIntent(context, count);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到图片查看的页面
     */
    public static void navToPhotoPreviewActivity(int position, List<String> photoList) {
        Context context = LrchaoViews.getInstance().getContext();
        Intent intent = new Intent(context, PhotoPagerActivity.class);
        intent.putExtra(PhotoPagerActivity.EXTRA_CURRENT_ITEM, position);
        intent.putExtra(PhotoPagerActivity.EXTRA_PHOTOS, (ArrayList) photoList);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
