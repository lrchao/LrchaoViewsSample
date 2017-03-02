package com.lrchao.views.textview;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lrchao.views.R;
import com.lrchao.views.Utils;

import java.util.concurrent.TimeUnit;

/**
 * Description: 验证码的view
 * 布局文件ID：
 * R.id.tv_get_captcha
 * R.id.tv_captcha_countdown;
 * mCaptchaTextView.startCountdown(); 开始倒计时
 *
 * @author liuranchao
 * @date 16/3/29 下午5:35
 */
public class CaptchaTextView extends LinearLayout implements View.OnClickListener {

    /**
     * 时长为60秒
     */
    private static final long COUNTDOWN_DURATION = 60 * 1000;

    /**
     * 间隔为1秒
     */
    private static final long COUNTDOWN_INTERVAL = 1000;

    /**
     * 点击获取验证码监听
     */
    private OnGetCaptchaClickListener mOnGetCaptchaClickListener;

    /**
     * 获取验证码
     */
    private TextView mTvGetCaptcha;
    /**
     * 倒计时
     */
    private TextView mTvCountdown;
    /**
     * 倒计时器
     */
    private MyCountdown mMyCountdown;
    /**
     * 是否正在倒计时
     */
    private boolean mIsCounting;

    private Context mContext;

    public CaptchaTextView(Context context) {
        super(context);
        init(context);
    }

    public CaptchaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mMyCountdown = new MyCountdown(COUNTDOWN_DURATION, COUNTDOWN_INTERVAL);
    }

    /**
     * 设置layout 布局文件
     *
     * @param layoutView layout id
     */
    public void setCaptchaView(int layoutView) {
        LayoutInflater.from(mContext).inflate(layoutView, this);
        mTvGetCaptcha = (TextView) findViewById(R.id.tv_get_captcha);
        mTvGetCaptcha.setOnClickListener(this);
        mTvCountdown = (TextView) findViewById(R.id.tv_captcha_countdown);
        bindView();
    }

    private void bindView() {
        if (mIsCounting) {
            mTvGetCaptcha.setVisibility(View.GONE);
            mTvCountdown.setVisibility(View.VISIBLE);
        } else {
            mTvGetCaptcha.setVisibility(View.VISIBLE);
            mTvCountdown.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_get_captcha) {
            if (mOnGetCaptchaClickListener != null) {
                mOnGetCaptchaClickListener.onGetCaptchaClick();
            }
        }
    }

    /**
     * 开始倒计时
     */
    public void startCountdown() {
        mIsCounting = true;
        bindView();
        mMyCountdown.start();
    }

    /**
     * 停止倒计时
     */
    public void stopCountdown() {
        mIsCounting = false;
        bindView();
        mMyCountdown.cancel();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mMyCountdown.cancel();
    }

    public void setOnGetCaptchaClickListener(OnGetCaptchaClickListener onGetCaptchaClickListener) {
        mOnGetCaptchaClickListener = onGetCaptchaClickListener;
    }

    public interface OnGetCaptchaClickListener {
        void onGetCaptchaClick();
    }

    class MyCountdown extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountdown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
            mTvCountdown.setText(Utils.getString(R.string.login_captcha_counting, seconds));
        }

        @Override
        public void onFinish() {
            mIsCounting = false;
            bindView();
        }
    }
}
