package com.lrchao.views.imageview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Description: 宽度填满，高度自适应，并显示上部，如果下部太长 则剪切掉
 *
 * @author lrc19860926@gmail.com
 * @date 16/7/15 下午1:37
 */
public class WidthFullHeightTopImageView extends ImageView {

    public WidthFullHeightTopImageView(Context context) {
        super(context);
    }

    public WidthFullHeightTopImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = getDrawable();

        if (d != null) {
            // ceil not round - avoid thin vertical gaps along the left/right edges
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
