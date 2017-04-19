package com.lrchao.views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.lrchao.views.photo.PhotoPickerActivity;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPreview;

/**
 * Description: 工具类
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/19 上午11:36
 */

public final class Utils {

    /**
     * dp to px
     */
    private static final float RATIO = 0.5f;

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
    public static void navToPhotoPreviewActivity(Activity context, int position, ArrayList<String> photoList) {
        PhotoPreview.builder()
                .setPhotos(photoList)
                .setCurrentItem(position)
                .setShowDeleteButton(false)
                .start(context);
    }

    /**
     * 获取strings.xml的文本
     *
     * @param resId      strings.xml
     * @param formatArgs 替换的参数
     * @return String
     */
    public static String getString(@StringRes int resId, Object... formatArgs) {
        return LrchaoViews.getInstance().getContext().getString(resId, formatArgs);
    }

    /**
     * 关闭软键盘
     */
    public static void hideKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * Description: 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue dp
     * @return px
     */
    public static int dip2px(float dpValue) {

        final float scale = getDensity(LrchaoViews.getInstance().getContext());
        return (int) (dpValue * scale + RATIO);
    }

    /**
     * Description: 获取当前设备的像素
     *
     * @param context Context
     *                0.75 - ldpi
     *                1.0 - mdpi
     *                1.5 - hdpi
     *                2.0 - xhdpi
     *                3.0 - xxhdpi
     *                4.0 - xxxhdpi
     */
    public static float getDensity(Context context) {
        return context.getResources()
                .getDisplayMetrics().density;
    }

    /**
     * 显示键盘，延时操作
     *
     * @param view    View
     * @param context Context
     */
    public static void showDelay(final View view, final Context context) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                view.requestFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 300);
    }

    /**
     * Uses static final constants to detect if the device's platform version is M or
     * later.
     */
    public static boolean hasM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * Uses static final constants to detect if the device's platform version is LOLLIPOP or
     * later.
     */
    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * 获取color 颜色
     *
     * @param colorId colors.xml
     * @return color
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static int getColor(@ColorRes int colorId) {
        if (hasM()) {
            return LrchaoViews.getInstance().getContext().getResources().getColor(colorId, null);
        } else {
            return LrchaoViews.getInstance().getContext().getResources().getColor(colorId);
        }
    }

    /**
     * 获取Dawable
     *
     * @param drawableId 资源文件ID
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getDrawable(@DrawableRes int drawableId) {
        if (hasLollipop()) {
            return LrchaoViews.getInstance().getContext().getResources().getDrawable(drawableId,
                    LrchaoViews.getInstance().getContext().getTheme());
        } else {
            return LrchaoViews.getInstance().getContext().getResources().getDrawable(drawableId);
        }

    }

}
