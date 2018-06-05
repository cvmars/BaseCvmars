package com.cvmars.baseapp.module.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hehaifeng on 2018/6/1
 *
 */

public abstract class PermissionListener implements View.OnClickListener {

    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";


    public static final String[] CALL_PHONE = {Manifest.permission.CALL_PHONE}; //电话
    public static final String[] CAMERA = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};//相机
    public static final String[] VIDEO = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};//拍摄
    public static final String[] PHOTO = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}; //相册
    public static final String[] RECORD_AUDIO = {Manifest.permission.RECORD_AUDIO}; //麦克风
    public static final String[] Location = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}; //定位信息
    public static final String[] Contacts = {Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE}; //通讯录
    String[] pers;

    boolean isNeedHas = false;

    public String[] getPers() {
        return pers;
    }

    public PermissionListener(String[] pers) {
        this.pers = pers;
    }

    @Override
    public void onClick(View v) {

        onPermissionClick();
    }


    abstract public void onPermissionClick();

    public boolean isNeedHas() {
        if (isNeedHas) {
            return true;
        }
        return getPers() != Location;
    }

    public String getPerName() {
        if (CALL_PHONE == pers) {
            return "电话";
        } else if (CAMERA == pers) {
            return "相机";
        } else if (VIDEO == pers) {
            return "拍摄";
        } else if (PHOTO == pers) {
            return "相册";
        } else if (RECORD_AUDIO == pers) {
            return "麦克风";
        } else if (Location == pers) {
            return "定位";
        } else if (Contacts == pers) {
            return "通讯录";
        }
        return "";
    }


    /**
     * 通知是否开启
     *
     * @param context
     * @return
     */
    @SuppressLint("NewApi")
    public static boolean isNotificationEnabled(Context context) {

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
      /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}