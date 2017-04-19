package com.lrchao.views;

/**
 * Description: Intent key
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/6 下午6:01
 */

public interface BundleKey {

    String INTENT_EXTRA_PHOTO_PICKER_COUNT = "intent.extra.PHOTO_PICKER_COUNT"; // 数量
    String INTENT_EXTRA_PHOTO_PICKER_RESULT_DATA = "intent.extra.PHOTO_PICKER_RESULT_DATA"; // 图片选择成功的集合
    String INTENT_ACTION_PHOTO_PICKER_SUCCESS = "intent.action.PHOTO_PICKER_SUCCESS"; //图片选择成功

    String INTENT_EXTRA_PHOTO_VIEWER_DATA = "intent.extra.PHOTO_VIEWER_DATA"; // 图片查看器
}
