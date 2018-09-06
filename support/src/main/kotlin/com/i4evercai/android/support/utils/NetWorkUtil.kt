package com.i4evercai.android.support.utils


import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils


/**
 *
 * @Description: 网络相关辅助类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 11:17
 * @version V1.0
 */
object NetWorkUtil {
    val NETWORK_TYPE_WIFI = "wifi"
    val NETWORK_TYPE_3G = "eg"
    val NETWORK_TYPE_2G = "2g"
    val NETWORK_TYPE_WAP = "wap"
    val NETWORK_TYPE_UNKNOWN = "unknown"
    val NETWORK_TYPE_DISCONNECT = "disconnect"

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    @JvmStatic
    fun isConnected(context: Context): Boolean {

        val connectivity = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (null != connectivity) {

            val info = connectivity.activeNetworkInfo
            if (null != info && info.isConnected) {
                if (info.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 判断是否是wifi连接
     */
    @JvmStatic
    fun isWifi(context: Context): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        if (cm!=null && cm is ConnectivityManager){
            val activeNetworkInfo = cm.activeNetworkInfo
            if (activeNetworkInfo!=null){
                return ConnectivityManager.TYPE_WIFI  == activeNetworkInfo.type
            }
        }
        return false

    }

    /**
     * 打开网络设置界面
     */
    @JvmStatic
    fun openSetting(activity: Activity) {
        val intent = Intent("/")
        val cm = ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings")
        intent.component = cm
        intent.action = "android.intent.action.VIEW"
        activity.startActivityForResult(intent, 0)
    }
    @JvmStatic
    fun getNetWorkType(context: Context): String {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        var type = NETWORK_TYPE_DISCONNECT
        if (manager == null) {
            return type
        }
        val networkInfo: NetworkInfo? = manager.activeNetworkInfo
        if (networkInfo == null) {
            return type
        }
        if (networkInfo.isConnected) {
            val typeName = networkInfo.typeName
            if ("WIFI".equals(typeName, ignoreCase = true)) {
                type = NETWORK_TYPE_WIFI
            } else if ("MOBILE".equals(typeName, ignoreCase = true)) {
                val proxyHost = android.net.Proxy.getDefaultHost()
                type = if (TextUtils.isEmpty(proxyHost))
                    if (isFastMobileNetwork(context)) NETWORK_TYPE_3G else NETWORK_TYPE_2G
                else
                    NETWORK_TYPE_WAP
            } else {
                type = NETWORK_TYPE_UNKNOWN
            }
        }
        return type
    }

    @JvmStatic
    private fun isFastMobileNetwork(context: Context): Boolean {
        val telephonyManager = context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager ?: return false

        when (telephonyManager.networkType) {
            TelephonyManager.NETWORK_TYPE_1xRTT -> return false
            TelephonyManager.NETWORK_TYPE_CDMA -> return false
            TelephonyManager.NETWORK_TYPE_EDGE -> return false
            TelephonyManager.NETWORK_TYPE_EVDO_0 -> return true
            TelephonyManager.NETWORK_TYPE_EVDO_A -> return true
            TelephonyManager.NETWORK_TYPE_GPRS -> return false
            TelephonyManager.NETWORK_TYPE_HSDPA -> return true
            TelephonyManager.NETWORK_TYPE_HSPA -> return true
            TelephonyManager.NETWORK_TYPE_HSUPA -> return true
            TelephonyManager.NETWORK_TYPE_UMTS -> return true
            TelephonyManager.NETWORK_TYPE_EHRPD -> return true
            TelephonyManager.NETWORK_TYPE_EVDO_B -> return true
            TelephonyManager.NETWORK_TYPE_HSPAP -> return true
            TelephonyManager.NETWORK_TYPE_IDEN -> return false
            TelephonyManager.NETWORK_TYPE_LTE -> return true
            TelephonyManager.NETWORK_TYPE_UNKNOWN -> return false
            else -> return false
        }
    }

}