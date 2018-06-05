package com.cvmars.baseapp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cvmars.baseapp.R;

/**
 * Created by hehaifeng on 2018/6/1.
 */

public class ImageUtils {


    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {

        if (!isValidContextForGlide(context)) {

            return;
        }
        if (!TextUtils.isEmpty(imageUrl) && imageUrl.contains("gif")) {
            Glide.with(context)
                    .load(imageUrl).asGif()
//                    .error(R.drawable.bg_img_defult)
//                    .placeholder(R.drawable.bg_img_defult)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
        } else {

            Glide.with(context)
                    .load(imageUrl)
//                    .error(R.drawable.bg_img_defult)
//                    .placeholder(R.drawable.bg_img_defult)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);

        }

    }


    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadImageForVisaSize(Context context, String imageUrl,
                                            ImageView imageView, int width, int hight) {

        if (!isValidContextForGlide(context)) {

            return;
        }
        if (!TextUtils.isEmpty(imageUrl) && imageUrl.contains("gif")) {
            Glide.with(context)
                    .load(imageUrl).asGif()
                    .override(width, hight)
//                    .error(R.drawable.bg_img_defult)
//                    .placeholder(R.drawable.bg_img_defult)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
        } else {

            Glide.with(context)
                    .load(imageUrl)
//                    .error(R.drawable.bg_img_defult)
                    .override(width, hight)
//                    .placeholder(R.drawable.bg_img_defult)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);

        }

    }


    //
    public static boolean isValidContextForGlide(final Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return false;
            }
        }
        return true;
    }
}
