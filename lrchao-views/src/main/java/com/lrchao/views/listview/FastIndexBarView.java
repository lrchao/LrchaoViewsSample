package com.lrchao.views.listview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.lrchao.utils.ResourceUtils;
import com.lrchao.views.R;


/**
 * Description: 快速索引view
 *
 * @author lrc19860926@gmail.com
 * @date 16/4/26 下午2:28
 */
public class FastIndexBarView extends View {

    // 26个字母
    private static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
            "Z"};

    // 触摸事件
    private OnTouchingLetterChangedListener mOnTouchingLetterChangedListener;

    /**
     * 弹出的dialog
     */
    private TextView mTextDialog;

    private Paint mPaint;

    private Rect mBounds;

    private int mCount;

    private int mLetterColor = R.color.index_bar_letter_nor;
    // 选中
    private int choose = -1;

    public FastIndexBarView(Context context) {
        super(context);
        init(context);
    }

    public FastIndexBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setTextDialog(TextView textDialog) {
        this.mTextDialog = textDialog;
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 获取焦点改变背景颜色.
        int height = getHeight();// 获取对应高度
        int width = getWidth(); // 获取对应宽度
        int singleHeight = height / LETTERS.length;// 获取每一个字母的高度

        for (int i = 0; i < LETTERS.length; i++) {
            mPaint.setColor(Color.GRAY);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(getResources().getDimension(R.dimen.index_bar_letter_text));
            // 选中的状态
            if (i == choose) {
                mPaint.setColor(ResourceUtils.getColor(R.color.index_bar_letter_sel));
                mPaint.setFakeBoldText(true);
            }
            // x坐标等于中间-字符串宽度的一半.
            float xPos = width / 2 - mPaint.measureText(LETTERS[i]) / 2;

            float yPos = singleHeight * i + singleHeight;


            canvas.drawText(LETTERS[i], xPos, yPos, mPaint);
            mPaint.reset();// 重置画笔
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();// 点击y坐标
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = mOnTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * LETTERS.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.

        switch (action) {
            case MotionEvent.ACTION_UP:
                choose = -1;
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default:
                if (oldChoose != c) {
                    if (c >= 0 && c < LETTERS.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(LETTERS[c]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(LETTERS[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }

                        choose = c;
                        invalidate();
                    }
                }

                break;
        }
        return true;
    }

    /**
     * 向外公开的方法
     *
     * @param listener OnTouchingLetterChangedListener
     */
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener listener) {
        this.mOnTouchingLetterChangedListener = listener;
    }

    /**
     * 触摸的字母改变事件
     */
    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String letter);
    }

}
