package com.shangxiaom.commonlist.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.shangxiaom.commonlist.MyApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetWorkUtil {
    private static Context mContext = MyApplication.getContext();

    /**
     * **************************************************
     *
     * @ 方法名称: isNetworkConnected
     * @ 功能描述: 判断是否有网络连接
     * @ 修改者    : "shangM"
     * @ 修改日期: 2017年5月15日
     * @ 修改内容: 初始创建
     * @ 参数类型: @return
     * @ 返回类型: boolean
     * @ throws
     * **************************************************
     */
    public static boolean isNetworkConnected() {
        // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        // 判断NetworkInfo对象是否为空
        if (networkInfo != null)
            return networkInfo.isAvailable();
        return false;
    }

    /**
     * **************************************************
     *
     * @ 方法名称: isWifiConnected
     * @ 功能描述: 判断wifi网络是否连接
     * @ 修改者    : "shangM"
     * @ 修改日期: 2017年5月15日
     * @ 修改内容: 初始创建
     * @ 参数类型: @return
     * @ 返回类型: boolean
     * @ throws
     * **************************************************
     */
    public static boolean isWifiConnected() {
        // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        // 判断NetworkInfo对象是否为空 并且类型是否为WIFI
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
            return networkInfo.isAvailable();
        return false;
    }

    /**
     * **************************************************
     *
     * @ 方法名称: isMobileConnected
     * @ 功能描述: 判断移动网络是否连接
     * @ 修改者    : "shangM"
     * @ 修改日期: 2017年5月15日
     * @ 修改内容: 初始创建
     * @ 参数类型: @return
     * @ 返回类型: boolean
     * @ throws
     * **************************************************
     */
    public static boolean isMobileConnected() {
        // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        // 判断NetworkInfo对象是否为空 并且类型是否为MOBILE
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
            return networkInfo.isAvailable();
        return false;
    }

    /**
     * **************************************************
     *
     * @ 方法名称: getConnectedType
     * @ 功能描述: 获取当前网络的连接类型
     * @ 修改者    : "shangM"
     * @ 修改日期: 2017年5月15日
     * @ 修改内容: 初始创建
     * @ 参数类型: @return
     * @ 返回类型: int
     * @ throws
     * **************************************************
     */
    public static int getConnectedType() {
        if (mContext != null) {
            // 获取手机所有连接管理对象
            ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取NetworkInfo对象
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                // 返回NetworkInfo的类型
                return networkInfo.getType();
            }
        }
        return -1;
    }

    /**
     * **************************************************
     *
     * @ 方法名称: getAPNType
     * @ 功能描述: 获取当前的网络状态：0-没有网络；1-wifi；2-2G；3-3G；4-4G
     * @ 修改者    : "shangM"
     * @ 修改日期: 2017年5月15日
     * @ 修改内容: 初始创建
     * @ 参数类型: @return
     * @ 返回类型: int
     * @ throws
     * **************************************************
     */
    public static int getAPNType() {
        // 结果返回值
        int netType = 0;
        // 获取手机所有连接管理对象
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        // NetworkInfo对象为空 则代表没有网络
        if (networkInfo == null) {
            return netType;
        }
        // 否则 NetworkInfo对象不为空 则获取该networkInfo的类型
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_WIFI) {
            // WIFI
            netType = 1;
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            int nSubType = networkInfo.getSubtype();
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            // 3G 联通的3G为UMTS或HSDPA 电信的3G为EVDO
            if (nSubType == TelephonyManager.NETWORK_TYPE_LTE && !telephonyManager.isNetworkRoaming()) {
                netType = 4;
            } else if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS || nSubType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || nSubType == TelephonyManager.NETWORK_TYPE_EVDO_0 && !telephonyManager.isNetworkRoaming()) {
                netType = 3;
                // 2G 移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
            } else if (nSubType == TelephonyManager.NETWORK_TYPE_GPRS || nSubType == TelephonyManager.NETWORK_TYPE_EDGE
                    || nSubType == TelephonyManager.NETWORK_TYPE_CDMA && !telephonyManager.isNetworkRoaming()) {
                netType = 2;
            } else {
                netType = 2;
            }
        }
        return netType;
    }

    /**
     * 判断是否为合法IP
     *
     * @return the ip
     */
    public static boolean isboolIp(String ipAddress) {
        if (ipAddress.length() < 7 || ipAddress.length() > 15) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(ipAddress);

        boolean flag = mat.find();

        return flag;
    }
}
