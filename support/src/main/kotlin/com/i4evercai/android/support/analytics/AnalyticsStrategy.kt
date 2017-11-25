package com.i4evercai.android.support.analytics

import android.app.Activity
import android.content.Context

/**
 *
 * @Description: 统计策略
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/9/25 11:48
 * @version V1.0
 */
interface AnalyticsStrategy {
    fun init(context: Context)
    fun onLowMemory(context: Context)
    fun onStop(context: Context)
    fun onPause(context: Context)
    fun onResume(context: Context)
    fun onDestroy(context: Context)
    fun onLowMemory(activity: Activity)
    fun onStop(activity: Activity)
    fun onPause(activity: Activity)
    fun onResume(activity: Activity)
    fun reportError(context: Context, error: String)
    fun reportError(context: Context, t: Throwable)
    fun postFeedBack(context: Context, content: String, screenshotFilePath: String?)
}