package com.lrchao.views.imageview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

/**
 * Description: 适配的ImageView
 * https://inthecheesefactory.com/blog/correct-imageview-adjustviewbounds-with-adjustable-imageview/en
 * 宽度填充，高度自动拉伸，高度不会被裁剪，会全部显示进来
 * 如果高度大于屏幕 则以高度为填充满
 * 如果高度小于于屏幕 则以宽度为填充满
 * <p/>
 * 需要设置android:adjustViewBounds="true"
 * 解决 android:adjustViewBounds="true" 失效的情况
 *
 * @author lrc19860926@gmail.com
 * @date 16/4/7 下午11:29
 */
public class AdjustableImageView extends ImageView {

    private boolean mAdjustViewBounds;

    public AdjustableImageView(Context context) {
        super(context);
    }

    public AdjustableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdjustableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdjustViewBounds(boolean adjustViewBounds) {
        mAdjustViewBounds = adjustViewBounds;
        super.setAdjustViewBounds(adjustViewBounds);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable mDrawable = getDrawable();
        if (mDrawable == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        if (mAdjustViewBounds) {
            int mDrawableWidth = mDrawable.getIntrinsicWidth();
            int mDrawableHeight = mDrawable.getIntrinsicHeight();
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);

            if (heightMode == MeasureSpec.EXACTLY && widthMode != MeasureSpec.EXACTLY) {
                // Fixed Height & Adjustable Width
                int height = heightSize;
                int width = height * mDrawableWidth / mDrawableHeight;
                if (isInScrollingContainer()) {
                    setMeasuredDimension(width, height);
                } else {
                    setMeasuredDimension(Math.min(width, widthSize), Math.min(height, heightSize));
                }

            } else if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
                // Fixed Width & Adjustable Height
                int width = widthSize;
                int height = width * mDrawableHeight / mDrawableWidth;
                if (isInScrollingContainer()) {
                    setMeasuredDimension(width, height);
                } else {
                    setMeasuredDimension(Math.min(width, widthSize), Math.min(height, heightSize));
                }

            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private boolean isInScrollingContainer() {
        ViewParent p = getParent();
        while (p != null && p instanceof ViewGroup) {
            if (((ViewGroup) p).shouldDelayChildPressedState()) {
                return true;
            }
            p = p.getParent();
        }
        return false;
    }
}
