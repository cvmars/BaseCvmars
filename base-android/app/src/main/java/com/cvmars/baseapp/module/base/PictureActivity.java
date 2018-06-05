package com.cvmars.baseapp.module.base;

import android.content.Intent;

import com.cvmars.baseapp.R;
import com.cvmars.baseapp.utils.NetworkUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.List;

/**
 * 图片选择基类: 1.单选 2.多选
 */

public class PictureActivity extends BaseActivity {


    //选择多张图片
    public void selectMultPic() {

        checkMyPermission(new PermissionListener(PermissionListener.PHOTO) {
            @Override
            public void onPermissionClick() {

                PictureSelector.create(PictureActivity.this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .theme(R.style.picture_default_style)
                        .selectionMode(PictureConfig.MULTIPLE)
                        .previewImage(true)
                        .isCamera(false)
//                .previewVideo(true)
                        .enableCrop(false)// 是否裁剪 true or false
                        .compress(true)
                        .maxSelectNum(9)
                        .compressMaxKB(400)
                        .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)
                        .glideOverride(800, 800)// 是否压缩 true or false
                        .withAspectRatio(1, 1)
                        .rotateEnabled(true)
                        .scaleEnabled(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });

    }

    //选择单张图片
    public void selectSingPic() {

        checkMyPermission(new PermissionListener(PermissionListener.PHOTO) {
            @Override
            public void onPermissionClick() {

                PictureSelector.create(PictureActivity.this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .theme(R.style.picture_default_style)
                        .selectionMode(PictureConfig.SINGLE)
                        .previewImage(true)
//                .previewVideo(true)
                        .enableCrop(true)// 是否裁剪 true or false
                        .compress(true)
                        .compressMaxKB(400)
                        .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)
                        .glideOverride(800, 800)// 是否压缩 true or false
                        .withAspectRatio(1, 1)
                        .rotateEnabled(true)
                        .scaleEnabled(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                    if (localMedias != null && localMedias.size() > 0) {

                        if (NetworkUtil.isNetworkConnected(this)) {


                        } else {


                        }

                    }
                    break;
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        PictureFileUtils.deleteCacheDirFile(this);
    }
}
