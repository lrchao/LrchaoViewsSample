package com.lrchao.views.dialog.menu;

import com.lrchao.views.R;
import com.lrchao.views.dialog.BaseDialogFragment;

/**
 * Description: Menu Dialog的基类
 *
 * @author liuranchao
 * @date 16/4/26 上午11:10
 */
public abstract class BaseMenuDialogFragment extends BaseDialogFragment {

    @Override
    protected boolean isBottom() {
        return true;
    }

    @Override
    protected int getAnimation() {
        return R.style.DialogMenuAnimation;
    }
}
