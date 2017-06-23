package com.lrchao.lrchaoviewssample.edittext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/9 上午10:24
 */

public class TextInputBorderLayout extends LinearLayout {


    public TextInputBorderLayout(Context context) {
        super(context);
        init(context, null);
    }

    public TextInputBorderLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {


    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1.5f);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }
}
