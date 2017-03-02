package com.lrchao.lrchaoviewssample.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.utils.ImageUtils;
import com.lrchao.views.adapter.AddPicGridViewAdapter;
import com.lrchao.views.gridview.add_pic.AddPicGridView;

import java.io.File;

/**
 * Description: GridView
 *
 * @author lrc19860926@gmail.com
 * @date 2017/3/2 下午1:58
 */
public class GridViewActivity extends AppCompatActivity implements AddPicGridViewAdapter.OnDisplayImageListener {

    private AddPicGridView addPicGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        addPicGridView = (AddPicGridView) findViewById(R.id.grid_view_add_pic);
        addPicGridView.setItemLayoutId(R.layout.gridview_add_pic_item);
        addPicGridView.setColumnNumber(4);
        addPicGridView.setAddViewRes(R.drawable.add_pic_icon);
        addPicGridView.setMaxCount(9);
        addPicGridView.setOnDisplayImageListener(this);


    }

    @Override
    public void displayImage(File file, ImageView ivIcon) {
        ImageUtils.display(file,
                ivIcon,
                0,
                null,
                true,
                0,
                0,
                false,
                false);

    }

    @Override
    public void displayImage(int resId, ImageView ivIcon) {
        ImageUtils.display(resId,
                ivIcon,
                0,
                null,
                true,
                0,
                0,
                false,
                false);

    }
}
