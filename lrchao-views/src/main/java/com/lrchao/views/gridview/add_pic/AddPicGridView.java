package com.lrchao.views.gridview.add_pic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.lrchao.views.BundleKey;
import com.lrchao.views.LrchaoViews;
import com.lrchao.views.R;
import com.lrchao.views.Utils;
import com.lrchao.views.adapter.AddPicGridViewAdapter;
import com.lrchao.views.gridview.FixGridView;
import com.lrchao.views.gridview.OnClickAddPicGridAdapterListener;
import com.lrchao.views.model.AddPicModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Description: 添加图片的GridView
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/6 上午10:06
 */

public class AddPicGridView extends LinearLayout implements OnClickAddPicGridAdapterListener {

    private static final String TAG = "AddPicGridView";

    private FixGridView mFixGridView;

    private AddPicGridViewAdapter mAdapter;

    /**
     * Add View的res id
     */
    private int mAddViewResId;

    /**
     * 最多个个数
     */
    private int mMaxCount;

    //======================================================
    // public
    //======================================================

    public AddPicGridView(Context context) {
        super(context);
        init(context);
    }

    public AddPicGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddPicGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setMaxCount(int i) {
        mMaxCount = i;
    }

    /**
     * 设置Item布局view
     */
    public final void setItemLayoutId(@LayoutRes int layoutId) {
        mAdapter.setItemLayoutId(layoutId);
    }

    /**
     * 设置添加图片的背景图片
     */
    public final void setAddViewRes(@DrawableRes int drawableResId) {
        mAddViewResId = drawableResId;
        mAdapter.add(createAddPicData(drawableResId));
    }

    public boolean isHasAddView() {
        for (AddPicModel model : mAdapter.getDataSource()) {
            if (model.isAddView()) {
                return true;
            }
        }
        return false;
    }

    //======================================================
    // private
    //======================================================

    /**
     * 设置列数
     *
     * @param column 列数
     */
    public void setColumnNumber(int column) {
        mFixGridView.setNumColumns(column);
    }

    /**
     * 设置数据
     */
    public void setData(List<AddPicModel> data) {
        mAdapter.addAll(data);

    }

    /**
     * 获得选择的数据
     */
    public List<String> getSelectedData() {
        List<String> filePathList = new ArrayList<>();
        for (AddPicModel model : mAdapter.getDataSource()) {
            if (model.isNormalView() && !TextUtils.isEmpty(model.getFilePath())) {
                filePathList.add(model.getFilePath());
            }
        }
        return filePathList;
    }

    private AddPicModel createAddPicData(int drawableResId) {
        AddPicModel model = new AddPicModel();
        model.setIsAddView();
        model.setAddViewResId(drawableResId);
        return model;
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.gridview_add_pic, this);
        setOrientation(VERTICAL);
        setBackgroundResource(android.R.color.white);

        mFixGridView = (FixGridView) findViewById(R.id.fix_gridview);
        mAdapter = new AddPicGridViewAdapter(context, null);
        mAdapter.setOnClickAddPicGridAdapterListener(this);
        mFixGridView.setAdapter(mAdapter);

        registerBroadcast();

    }

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BundleKey.INTENT_ACTION_PHOTO_PICKER_SUCCESS);
        LocalBroadcastManager.getInstance(LrchaoViews.getInstance().getContext())
                .registerReceiver(new MyBroadcastReceiver(), filter);
    }

    @Override
    public void onClickAddPick() {
        Utils.navToPhotoPickerActivity(mMaxCount - mAdapter.getCount() + 1);
    }

    @Override
    public void onClickPic(String filePath) {
        int position = mAdapter.getFilePathList().indexOf(filePath);
        Utils.navToPhotoPreviewActivity(position, mAdapter.getFilePathList());
    }

    @Override
    public void onClickClose(int position) {
        mAdapter.remove(position);
        if (!isHasAddView()) {
            mAdapter.add(mAdapter.getCount(), createAddPicData(mAddViewResId));
        }
    }


    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BundleKey.INTENT_ACTION_PHOTO_PICKER_SUCCESS.equals(action)) {

                List<String> photoPickerResult = intent.getStringArrayListExtra(BundleKey.INTENT_EXTRA_PHOTO_PICKER_RESULT_DATA);

                List<AddPicModel> data = new ArrayList<>();
                for (String filePath : photoPickerResult) {
                    AddPicModel model = new AddPicModel();
                    model.setFilePath(filePath);
                    data.add(model);
                }
                mAdapter.addAll(0, data);

                if (mAdapter.getCount() > mMaxCount) {
                    mAdapter.remove(mAdapter.getCount() - 1);
                }

            }
        }
    }

}
