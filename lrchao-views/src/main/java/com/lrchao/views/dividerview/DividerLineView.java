package com.lrchao.views.dividerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lrchao.views.R;

/**
 * Description: 间隔线
 *
 * @author liuranchao
 * @date 16/7/20 下午2:08
 */
public class DividerLineView extends View {

    private int mMarginLeft;

    private int mMarginRight;

    private LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);

    public DividerLineView(Context context) {
        super(context);
        init(context);
    }

    public DividerLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    /**
     * @param height px
     */
    public void setHeight(int height) {
        mParams.height = height;
        setLayoutParams(mParams);
    }

    /**
     * @param left 左边的边距 px
     */
    public void setMarginLeft(int left) {
        mMarginLeft = left;
        mParams.setMargins(mMarginLeft, 0, mMarginRight, 0);
        setLayoutParams(mParams);
    }
    //=============================================
    // private
    //=============================================

    /**
     * @param right 右边的边距 px
     */
    public void setMarginRight(int right) {
        mMarginRight = right;
        mParams.setMargins(mMarginLeft, 0, mMarginRight, 0);
        setLayoutParams(mParams);
    }

    private void init(Context context) {
        setBackgroundResource(R.color.divider_line);
        setLayoutParams(mParams);
    }

}
