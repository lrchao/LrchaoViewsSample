package com.lrchao.lrchaoviewssample.tab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.views.tab.MainTabLayout;

public class MainTabActivity extends AppCompatActivity implements MainTabLayout.OnMainTabItemClickListener {

    private static final String TAG = "MainTabActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        MainTabLayout mainTabLayout = (MainTabLayout) findViewById(R.id.main_tab_layout);

        mainTabLayout.addItem(R.drawable.tab1, R.drawable.tab1_checked, R.string.button_style_3_text,
                R.color.colorAccent, R.color.colorPrimaryDark);
        mainTabLayout.addItem(R.drawable.tab1, R.drawable.tab1_checked, R.string.button_style_3_text,
                R.color.colorAccent, R.color.colorPrimaryDark);
        mainTabLayout.setOnMainTabItemClickListener(this);

        mainTabLayout.clickItem(1);
        mainTabLayout.setNoticeCount(0, 0);
        mainTabLayout.setRedNoticeShow(1, false);
    }


    @Override
    public void onMainTabItemClick(int position) {
        Log.e(TAG, "onMainTabItemClick==position==" + position);


    }
}
