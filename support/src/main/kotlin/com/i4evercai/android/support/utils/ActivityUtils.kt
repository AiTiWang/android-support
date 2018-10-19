package com.i4evercai.android.support.utils

import android.content.Context
import java.nio.file.Files.size
import android.content.Intent
import android.os.Bundle
import android.R.attr.name
import android.content.pm.ResolveInfo
import android.content.pm.PackageManager







/**
 *
 * @Description: Activity相关工具类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 11:14
 * @version V1.0
 */

object ActivityUtils {

    /**
     * 判断是否存在Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   activity全路径类名
     * @return `true`: 是<br></br>`false`: 否
     */
    fun isActivityExists(context: Context, packageName: String, className: String): Boolean {
        val intent = Intent()
        intent.setClassName(packageName, className)
        return !(context.getPackageManager().resolveActivity(intent, 0) == null ||
                intent.resolveActivity(context.getPackageManager()) == null ||
                context.getPackageManager().queryIntentActivities(intent, 0).size === 0)
    }

    /**
     * 打开Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   全类名
     */
    fun launchActivity(context: Context, packageName: String, className: String) {
        launchActivity(context, packageName, className, null)
    }

    /**
     * 打开Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   全类名
     * @param bundle      bundle
     */
    fun launchActivity(context: Context, packageName: String, className: String, bundle: Bundle?) {
        context.startActivity(IntentUtils.getComponentIntent(packageName, className, bundle))
    }

    /**
     * 获取launcher activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @return launcher activity
     */
    fun getLauncherActivity(context: Context, packageName: String): String {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pm = context.packageManager
        val infos = pm.queryIntentActivities(intent, 0)
        for (info in infos) {
            if (info.activityInfo.packageName == packageName) {
                return info.activityInfo.name
            }
        }
        return "no $packageName"
    }
}