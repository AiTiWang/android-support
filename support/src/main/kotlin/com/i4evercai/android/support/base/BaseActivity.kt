package com.i4evercai.android.support.base

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.i4evercai.android.support.analytics.AnalyticsManager
import java.lang.ref.WeakReference

/**
 *
 * @Description: 基类Activity
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 14:46
 * @version V1.0
 */
class BaseActivity : AppCompatActivity(), LifecycleOwner {
    protected lateinit var wrActivity: WeakReference<Activity>
    protected lateinit var activity: BaseActivity
    protected lateinit var application: BaseApplication
    protected lateinit var context: Context
    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this

        wrActivity = WeakReference<Activity>(activity)

        application = getApplication() as BaseApplication
        application.onActivityCreated(wrActivity)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    override fun onResume() {
        super.onResume()
        AnalyticsManager.onResume(this)
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    override fun onPause() {
        AnalyticsManager.onPause(this)
        super.onPause()
    }

    override fun onStop() {
        AnalyticsManager.onStop(this)
        super.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        AnalyticsManager.onLowMemory(this)
    }

    override fun onDestroy() {

        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
        AnalyticsManager.onDestroy(this)
        application.onActivityDestroyed(wrActivity)
        super.onDestroy()
    }

}