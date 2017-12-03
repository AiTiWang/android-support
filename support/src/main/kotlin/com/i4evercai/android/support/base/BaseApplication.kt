package com.i4evercai.android.support.base

import android.app.Activity
import android.app.Application
import com.i4evercai.android.support.analytics.AnalyticsManager
import com.i4evercai.android.support.utils.AppUtils
import java.lang.ref.WeakReference

/**
 *
 * @Description: 基类Application
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 14:44
 * @version V1.0
 */
abstract open class BaseApplication : Application() {
    private val wrActivities = ArrayList<WeakReference<Activity>>()
    override fun onCreate() {
        super.onCreate()
        AnalyticsManager.init(this)
        if (AppUtils.isMainProcess(this)) {
            initConfig()
        }

    }

    /**
     * 初始化，仅在主进程
     */
    open fun initConfig() {

    }

    fun onActivityCreated(wrActivity: WeakReference<Activity>) {
        wrActivities.add(wrActivity)
    }


    fun onActivityDestroyed(wrActivity: WeakReference<Activity>) {
        wrActivities.remove(wrActivity)
    }

    fun exit() {
        for (wrActivity in wrActivities) {
            val activity = wrActivity.get()
            if (activity != null) {
                activity.finish()
            }


        }

        System.exit(0)
    }


}