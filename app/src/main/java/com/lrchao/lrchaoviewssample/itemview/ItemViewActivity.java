package com.lrchao.lrchaoviewssample.itemview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.views.itemview.CircleIconTitleDescArrowItemView;
import com.lrchao.views.itemview.ConversationItemView;
import com.lrchao.views.itemview.EditIconItemLayout;
import com.lrchao.views.itemview.EditTextItemLayout;
import com.lrchao.views.itemview.IconTextItemLayout;
import com.lrchao.views.itemview.ItemGroupLayout;
import com.lrchao.views.itemview.TextItemLayout;

public class ItemViewActivity extends AppCompatActivity {

    private static final String TAG = "ItemViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        //
        CircleIconTitleDescArrowItemView circleIconTitleDescArrowItemView =
                (CircleIconTitleDescArrowItemView) findViewById(R.id.itemview_circle_icon_title_desc);
        circleIconTitleDescArrowItemView.bindView(R.mipmap.ic_launcher, "标题是", "描述是");

        //
        ConversationItemView conversationItemView = (ConversationItemView) findViewById(R.id.itemview_conversation);
        conversationItemView.bindView("标题123", "描述123", 12);
        conversationItemView.mIvIcon.setImageResource(R.mipmap.ic_launcher);

        //
        EditIconItemLayout editIconItemLayout = (EditIconItemLayout) findViewById(R.id.edit_icon_item_layout);
        editIconItemLayout.setTitle(R.string.icon);
        editIconItemLayout.mIvIcon.setBackgroundResource(R.mipmap.ic_launcher);

        //
        EditTextItemLayout editTextItemLayout = (EditTextItemLayout) findViewById(R.id.edit_text_item_layout);
        editTextItemLayout.setTitle(R.string.title);
        editTextItemLayout.setContent("内容");
        editTextItemLayout.setEditGravity(Gravity.RIGHT);
        editTextItemLayout.maxLength(20);

        //
        TextItemLayout textItemLayout = (TextItemLayout) findViewById(R.id.text_item_layout);
        textItemLayout.setHintText(R.string.hint);
        textItemLayout.setTitle(R.string.title);
        textItemLayout.setContent("内容");

        //
        IconTextItemLayout iconTextItemLayout = (IconTextItemLayout) findViewById(R.id.icon_text_item_layout);
        iconTextItemLayout.mTvTitle.setText(R.string.title);
        iconTextItemLayout.setCustomPadding(10, 10, 10, 10);
        iconTextItemLayout.setIconSize(200);
        iconTextItemLayout.mIvIcon.setBackgroundResource(R.mipmap.ic_launcher);

        //
        ItemGroupLayout itemGroupLayout = (ItemGroupLayout) findViewById(R.id.item_group_layout);
        itemGroupLayout.setBottomLineLeftMargin(200);
        itemGroupLayout.setBottomLineRightMargin(30);
        itemGroupLayout.setBottomLineHeight(20);
        itemGroupLayout.addChildView(createItemLayout());
        itemGroupLayout.addChildView(createItemLayout());
        itemGroupLayout.addChildView(createItemLayout());
        itemGroupLayout.build();
    }

    private View createItemLayout() {
        IconTextItemLayout itemLayout = new IconTextItemLayout(this);
        itemLayout.mTvTitle.setText(R.string.title);
        itemLayout.mIvIcon.setBackgroundResource(R.mipmap.ic_launcher);
        itemLayout.setOnIconTextItemLayoutClickListener(new IconTextItemLayout.OnIconTextItemLayoutClickListener() {
            @Override
            public void onIconTextItemLayoutClick() {
                Log.e(TAG, "onIconTextItemLayoutClick()");
            }
        });
        return itemLayout;
    }
}
