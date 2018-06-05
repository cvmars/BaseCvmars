package com.cvmars.baseapp.utils;

/** 设备工具类
 * Created by zsm on 16/5/17.
 */
public class DeviceUtils {


    /** 手机系统版本 如：4.3 4.4
     * @return
     */
    public static String osVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /** 设备名称 如 华为 小米
     * @return
     */
    public static String deviceName() {
        return android.os.Build.MODEL;
    }


//    /** 安装版本号
//     * @return
//     */
//    public static String installVersion(){
//        return MyApp.getApplication().getPackageInfo().versionName;
//    }
//
//
//    /** 安装版本号code
//     * @return
//     */
//    public static String installVersionCode(){
//        return MyApp.getApplication().getPackageInfo().versionCode+"";
//    }
//
//
//    /**
//     * 获取App安装包信息
//     *
//     * @return
//     */
//    public PackageInfo getPackageInfo() {
//        PackageInfo info = null;
//        try {
//            info = getPackageManager().getPackageInfo(getPackageName(), 0);
//        } catch (PackageManager.NameNotFoundException e) {
//            LogUtils.d(e.getMessage());
//        }
//        if (info == null)
//            info = new PackageInfo();
//        return info;
//    }
//
//    /** 设备序列号
//     * @return
//     */
//    public static String SerialNumber(){
//        String serialNumber = android.os.Build.SERIAL;
//        return  serialNumber;
//    }
//
//
//    /** 版本比较
//     * @param setupVer 字符串格式 2.5.1 或者 2.5.12
//     * @param onLineVer 字符串格式 2.5.1 或者 2.5.12
//     * @return  true:更新  false:不更新
//     */
//    public static boolean verSionCompare(String setupVer, String onLineVer) {
//
//        if (TextUtils.isEmpty(setupVer) || TextUtils.isEmpty(onLineVer)) {
//            return false;
//        }
//
//        if (setupVer.length() == 5) {
//            StringBuffer buf = new StringBuffer(setupVer);
//            buf.insert(4, "0");
//            setupVer = buf.toString();
//        }
//
//        if (onLineVer.length() == 5) {
//            StringBuffer buf = new StringBuffer(onLineVer);
//            buf.insert(4, "0");
//            onLineVer = buf.toString();
//        }
//
//        setupVer = setupVer.replace(".", "");
//        onLineVer = onLineVer.replace(".", "");
//
//        //LogUtils.debug_s("setupVer: "+setupVer+",onLineVer="+onLineVer);
//
//        if (Integer.parseInt(onLineVer) > Integer.parseInt(setupVer)) {
//            return true;
//        }
//
//        return false;
//    }


}
