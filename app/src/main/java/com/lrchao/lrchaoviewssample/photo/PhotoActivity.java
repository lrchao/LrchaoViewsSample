package com.lrchao.lrchaoviewssample.photo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.utils.LogUtils;
import com.lrchao.views.BundleKey;
import com.lrchao.views.Utils;
import com.lrchao.views.photo.PhotoPickerActivity;

import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {

    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        IntentFilter filter = new IntentFilter();
        filter.addAction(BundleKey.INTENT_ACTION_PHOTO_PICKER_SUCCESS);

        myBroadcastReceiver = new MyBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(myBroadcastReceiver, filter);

        findViewById(R.id.btn_photo_picker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(PhotoPickerActivity.getCallingIntent(getApplicationContext(), 10));
            }
        });

        findViewById(R.id.btn_photo_viewer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> data = new ArrayList<>();

                data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493175375&di=1c99080c4e64114a8c038df4693bd8b3&imgtype=jpg&er=1&src=http%3A%2F%2Fimg01.cztv.com%2F201607%2F12%2Fe1275259c75568a37b84f35f284fa06e.jpg");
                data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492580666681&di=24acad577a4301d91f77233c14c15e42&imgtype=0&src=http%3A%2F%2Fwww.wyzu.cn%2Fuploadfile%2F2013%2F0128%2F20130128125713494.jpg");
                data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492580678458&di=fe607b65ba70f9823731bf7abd2d13cb&imgtype=0&src=http%3A%2F%2Fimg1.3lian.com%2F2015%2Fa2%2F239%2Fd%2F124.jpg");
                data.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=195954201,3353080794&fm=23&gp=0.jpg");
                data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492580709089&di=15d9eefdd95f68cc1ab05e55240eb7a6&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F37d12f2eb9389b5094f47d208435e5dde7116ebd.jpg");
                Utils.navToPhotoPreviewActivity(PhotoActivity.this, 0, data);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myBroadcastReceiver);
    }

    class MyBroadcastReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {

            LogUtils.e("intent=" + intent + " action=" + intent.getAction());
        }
    }
}
