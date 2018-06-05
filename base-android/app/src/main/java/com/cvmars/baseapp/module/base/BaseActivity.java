package com.cvmars.baseapp.module.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cvmars.baseapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by hehaifeng on 2018/6/1.
 */

public class BaseActivity extends AppCompatActivity {


    PermissionListener mPermissionListener;
    Dialog prodialog;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    // 获取加载框
    public Dialog getLoadDialog() {

        if (prodialog == null) {
            prodialog = new Dialog(this, R.style.load_animate_dialog);
            View view = getLayoutInflater().inflate(R.layout._loading_dialog,
                    null);
            prodialog.setContentView(view);
        }
        return prodialog;
    }


    /**
     * 跳转界面
     */
    public void goActivity(String key, String values, Class<?> cls) {
        Intent intent = new Intent(this, cls);
        Bundle bundle1 = new Bundle();
        bundle1.putString(key, values);
        intent.putExtras(bundle1);
        startActivity(intent);
    }


    /**
     * 跳转界面
     */
    public void goActivity(Bundle bundle, Class<?> cls) {
        Intent intent = new Intent(this, cls);
        Bundle bundle1 = new Bundle();
        if (bundle != null) {
            bundle1.putAll(bundle);
        }
        intent.putExtras(bundle1);
        startActivity(intent);
    }


    /**
     * 调用如: checkPermission(Manifest.permission.CALL_PHONE);
     *
     * @param permissions
     */
    public void checkMyPermission(PermissionListener permissions) {
        this.checkMyPermission(permissions, false);
    }


    /**
     * 调用如: checkPermission(Manifest.permission.CALL_PHONE);
     *
     * @param permissions
     * @param isOnce      是否一次,只能设置一个权限
     */
    public void checkMyPermission(PermissionListener permissions, boolean isOnce) {

        if (permissions == null) {
            return;
        }

        if (Build.VERSION.SDK_INT < 23) {
            permissions.onClick(null);
            return;
        }

        if (isOnce) {
            //只提醒一次
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    permissions.getPers()[0])) {
                return;
            }
        }

        this.mPermissionListener = permissions;

        String[] arr = checkSelfPermission(this, this.mPermissionListener.getPers());

        if (arr != null) {
            ActivityCompat.requestPermissions(this,
                    arr,
                    1001);
        } else {
            this.mPermissionListener.onClick(null);
        }
    }

    /**
     * 检查是否被容许
     *
     * @param context
     * @param permissions
     * @return
     */
    public static String[] checkSelfPermission(Context context, String[] permissions) {
        ArrayList<String> list = new ArrayList<>();
        for (String permission : permissions) {

            int rl = ContextCompat.checkSelfPermission(context, permission);

            if (rl == PackageManager.PERMISSION_DENIED) {
                list.add(permission);
            }
        }

        if (list.size() > 0) {
            return list.toArray(new String[list.size()]);
        }
        return null;
    }


    /**
     * 权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1001: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0) {

                    boolean ishas = true;
                    for (int grantResult : grantResults) {
                        if (grantResult == PackageManager.PERMISSION_DENIED) {
                            //其中一个未授权，立即返回false授权失败
                            ishas = false;
                            break;
                        }
                    }

                    if (ishas) {
                        if (this.mPermissionListener != null) {
                            this.mPermissionListener.onClick(null);
                            // this.mPermissionLinstener=null;
                        }
                    } else {

                        if (this.mPermissionListener.isNeedHas()) {

                            onOpenPermission(this.mPermissionListener.getPerName());
                        }
                    }

                } else {
                }
            }
        }
    }


    //打开权限
    public void onOpenPermission(String permissionName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("请到 “应用信息 -> 权限” 中授予，不然无法使用'" + permissionName + "'功能")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        }).create().show();


    }

    //打开权限
    public void onOpenLocationPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("请在系统设置中开启定位服务（设置>隐私>定位服务>开启）")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        }).create().show();


    }
}
