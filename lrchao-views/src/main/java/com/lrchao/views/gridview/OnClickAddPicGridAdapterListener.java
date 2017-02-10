package com.lrchao.views.gridview;

/**
 * Description: AddPicGridViewAdapter的监听事件
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/6 下午5:42
 */

public interface OnClickAddPicGridAdapterListener {

    /**
     * 点击addView
     */
    void onClickAddPick();

    /**
     * 点击图片
     */
    void onClickPic(String filePath);

    /**
     * 点击关闭按钮
     */
    void onClickClose(int position);
}
