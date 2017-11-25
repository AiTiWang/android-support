package com.i4evercai.android.support.analytics

import android.content.Context

/**
 *
 * @Description: AnalyticsManager
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/9/25 11:48
 * @version V1.0
 */
object AnalyticsManager {
    val analyticsStrategies = ArrayList<AnalyticsStrategy>()
    /**
     * 添加统计策略（必须在init之前）
     */
    fun addStrategy(strategy: AnalyticsStrategy) {
        analyticsStrategies.add(strategy)
    }

    fun init(context: Context) {
        for (strategy in analyticsStrategies) {
            strategy.init(context)
        }
    }

    fun onLowMemory(context: Context) {
        for (strategy in analyticsStrategies) {
            strategy.onLowMemory(context)
        }
    }

    fun onStop(context: Context) {
        for (strategy in analyticsStrategies) {
            strategy.onStop(context)
        }

    }

    fun onPause(context: Context) {
        for (strategy in analyticsStrategies) {
            strategy.onPause(context)
        }
    }

    fun onResume(context: Context) {
        for (strategy in analyticsStrategies) {
            strategy.onResume(context)
        }
    }
    fun  onDestroy(context: Context) {
        for (strategy in analyticsStrategies) {
            strategy.onDestroy(context)
        }
    }
    fun reportError(context: Context,error:String){
        for (strategy in analyticsStrategies) {
            strategy.reportError(context,error)
        }
    }
    fun reportError(context: Context,t:Throwable){
        for (strategy in analyticsStrategies) {
            strategy.reportError(context,t)
        }
    }

    fun postFeedBack(context:Context,content:String,screenshotFilePath:String?){
        for (strategy in analyticsStrategies) {
            strategy.onLowMemory(context)
        }
    }
}