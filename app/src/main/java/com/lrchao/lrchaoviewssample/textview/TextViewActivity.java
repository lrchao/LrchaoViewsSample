package com.lrchao.lrchaoviewssample.textview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.views.textview.CaptchaTextView;

public class TextViewActivity extends AppCompatActivity implements CaptchaTextView.OnGetCaptchaClickListener {

    private CaptchaTextView mCaptchaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        mCaptchaTextView = (CaptchaTextView) findViewById(R.id.view_get_captcha);
        mCaptchaTextView.setCaptchaView(R.layout.view_captcha);
        mCaptchaTextView.setOnGetCaptchaClickListener(this);

    }

    @Override
    public void onGetCaptchaClick() {
        mCaptchaTextView.startCountdown();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCaptchaTextView.stopCountdown();
    }
}
