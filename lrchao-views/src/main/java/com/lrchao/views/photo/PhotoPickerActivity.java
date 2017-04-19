package com.lrchao.views.photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Window;

import com.lrchao.views.BundleKey;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;


/**
 * Description: 处理选择图片的Activity
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/6 下午5:50
 */

public class PhotoPickerActivity extends Activity {

    public static Intent getCallingIntent(Context context, int count) {
        Intent intent = new Intent(context, PhotoPickerActivity.class);
        intent.putExtra(BundleKey.INTENT_EXTRA_PHOTO_PICKER_COUNT, count);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int count = getIntent().getIntExtra(BundleKey.INTENT_EXTRA_PHOTO_PICKER_COUNT, 1);
        startPhotoPickerIntent(count);
    }

    private void startPhotoPickerIntent(int photoCount) {

        PhotoPicker.builder()
                .setPhotoCount(photoCount)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                sendBroadcastForResult(photos);
            }
        }

        finish();
    }

    private void sendBroadcastForResult(List<String> photos) {
        Intent i = new Intent(BundleKey.INTENT_ACTION_PHOTO_PICKER_SUCCESS);
        i.putStringArrayListExtra(BundleKey.INTENT_EXTRA_PHOTO_PICKER_RESULT_DATA, (ArrayList<String>) photos);
        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }
}
