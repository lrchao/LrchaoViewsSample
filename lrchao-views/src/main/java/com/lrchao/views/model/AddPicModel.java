package com.lrchao.views.model;

/**
 * Description: 添加图片的model
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/6 上午11:03
 */

public class AddPicModel extends ImageFileModel {


    /**
     * 0 为正常的图片
     * -1 为添加的按钮
     */
    private int mType;

    private int mAddViewResId;

    private String mFilePath;

    public String getFilePath() {
        return mFilePath;
    }

    public void setFilePath(String filePath) {
        mFilePath = filePath;
    }

    public boolean isAddView() {
        return mType == -1;
    }

    public void setIsAddView() {
        mType = -1;
    }

    public boolean isNormalView() {
        return mType == 0;
    }

    public int getAddViewResId() {
        return mAddViewResId;
    }

    public void setAddViewResId(int addViewResId) {
        mAddViewResId = addViewResId;
    }

    public boolean isShowClose() {
        if (mType == 0) {
            return true;
        }
        return false;
    }

}
