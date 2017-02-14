package com.lrchao.lrchaoviewssample.toolbar;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.views.statusbar.StatusBarUtil;
import com.lrchao.views.toolbar.LrchaoToolBar;
import com.lrchao.views.toolbar.OnNavigationClickListener;
import com.lrchao.views.toolbar.action_provider.IconCountActionProvider;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ToolbarActivity extends SwipeBackActivity implements OnNavigationClickListener {

    /**
     * 购物车点击的监听。
     */
    private static final int DELETE_WHAT = 0X01;
    /**
     * 其它按钮。
     */
    private static final int PIC_WHAT = 0X02;

    /**
     * Toolbar菜单中的购物车按钮。
     */
    private IconCountActionProvider mDeleteBadgeActionProvider;
    /**
     * Toolbar菜单中的其它按钮。
     */
    private IconCountActionProvider mPicBadgeActionProvider;

    private int cartGoodsCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        // 设置toolbar
        //setSupportActionBar(mToolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }

        LrchaoToolBar jiaToolBar = (LrchaoToolBar) findViewById(R.id.toolbar);
        //jiaToolBar.setNavigationIcon(R.drawable.add_pic_close);
        jiaToolBar.setOnNavigationClickListener(this);
        jiaToolBar.setCenterTitleText("好的");
        jiaToolBar.setNavigationIcon(0);
        jiaToolBar.setCenterTitleColor(R.color.black);
        findViewById(R.id.add_single).setOnClickListener(onBtnClickListener);
        findViewById(R.id.add_double).setOnClickListener(onBtnClickListener);

        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
    }

    /**
     * 按钮点击监听。
     */
    private View.OnClickListener onBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.add_single) {
                cartGoodsCount++;
                mDeleteBadgeActionProvider.setCount(cartGoodsCount);
            } else if (view.getId() == R.id.add_double) {
                mPicBadgeActionProvider.setCount(99);
            }
        }
    };

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // 购物车。
        MenuItem menuItem = menu.findItem(R.id.menu_delete);
        mDeleteBadgeActionProvider = (IconCountActionProvider) MenuItemCompat.getActionProvider(menuItem);

        mDeleteBadgeActionProvider.setOnClickListener(DELETE_WHAT, onClickListener);

        // 其它。
        MenuItem menuItemOther = menu.findItem(R.id.menu_pic);
        mPicBadgeActionProvider = (IconCountActionProvider) MenuItemCompat.getActionProvider(menuItemOther);
        mPicBadgeActionProvider.setOnClickListener(PIC_WHAT, onClickListener);

        return true;
    }

    /**
     * Toolbar自定义菜单，点击监听。
     */
    private IconCountActionProvider.OnIconCountActionProviderClickListener onClickListener =
            new IconCountActionProvider.OnIconCountActionProviderClickListener() {
                @Override
                public void onIconCountActionProviderClick(int what) {
                    if (what == DELETE_WHAT) {
                        Toast.makeText(ToolbarActivity.this, "删除", Toast.LENGTH_SHORT).show();
                    } else if (what == PIC_WHAT) {
                        Toast.makeText(ToolbarActivity.this, "图片", Toast.LENGTH_SHORT).show();
                    }
                }
            };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //mDeleteBadgeActionProvider.setIcon(R.drawable.ab_edit);
        mDeleteBadgeActionProvider.setText(R.string.action_edit);
        mDeleteBadgeActionProvider.setCountTextBackground(R.drawable.circle_red);
        mPicBadgeActionProvider.setIcon(R.drawable.ab_android);
        mPicBadgeActionProvider.setCountTextBackground(R.drawable.circle_red_1);
    }

    @Override
    public void onNavigationClick() {
        Log.e("sdfsdf", "bbbbbbb");
    }
}
