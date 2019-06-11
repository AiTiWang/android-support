package com.i4evercai.android.support.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.i4evercai.android.support.analytics.AnalyticsManager
import com.i4evercai.android.support.widget.LoadingDialog
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.lang.ref.WeakReference

/**
 *
 * @Description: 基类Activity
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 14:46
 * @version V1.0
 */
abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks, View.OnClickListener {

    protected val activity by lazy { this }
    protected val wrActivity by lazy { WeakReference<Activity>(activity) }
    protected lateinit var application: BaseApplication
    protected val context: Context by lazy { this }
    protected val onClickListener by lazy { this }
   // private val lifecycleRegistry by lazy { LifecycleRegistry(this) }
    private var toast: Toast? = null
    protected val loadingDialog: LoadingDialog by lazy { LoadingDialog(activity) };

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        application = getApplication() as BaseApplication
        application.onActivityCreated(wrActivity)
        // initViews()
    }

    override fun onStart() {
        super.onStart()
      //  lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    open abstract fun initViews()

    override fun onResume() {
        super.onResume()
        AnalyticsManager.onResume(this)
       // lifecycleRegistry.markState(Lifecycle.State.RESUMED)
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

      //  lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
        AnalyticsManager.onDestroy(this)
        application.onActivityDestroyed(wrActivity)
        super.onDestroy()
    }


    /**
     * 设置状态栏、屏幕下面虚拟按键栏半透明
     * [statusBarTranslucent]       状态栏是否透明
     * [navigationBarTranslucent]   虚拟按键栏是否透明
     * [lightStatusBar]             状态栏是否是亮色
     * [statusBarColor]             状态栏颜色（透明时才生效）
     * [navigationBarColor]         虚拟按键栏颜色（透明时才生效）
     */
    fun setTranslucentStatusBarAndNaviBar(statusBarTranslucent: Boolean, navigationBarTranslucent: Boolean, lightStatusBar: Boolean,
                                          @ColorInt statusBarColor: Int, @ColorInt navigationBarColor: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && (statusBarTranslucent || navigationBarTranslucent)) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            var visibility: Int = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            if (statusBarTranslucent) {
                visibility = visibility or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
            if (navigationBarTranslucent) {
                visibility = visibility or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            }
            if (lightStatusBar) {
                visibility = visibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            window.decorView.systemUiVisibility = visibility

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (statusBarTranslucent) {
                window.statusBarColor = statusBarColor
            }
            if (navigationBarTranslucent) {
                window.navigationBarColor = navigationBarColor
            }
            return true
        }
        return false
    }

    fun displayHomeAsUpEnabled() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun toast(text: CharSequence) {
        toast(text, Toast.LENGTH_SHORT)
    }

    fun toast(text: CharSequence, duration: Int) {
        val toastVal = toast
        if (toastVal != null) {
            toastVal.cancel()
        }
        toast = Toast.makeText(activity, text, duration)
        toast?.show()
    }

    fun toast(@StringRes textResId: Int) {
        toast(textResId, Toast.LENGTH_SHORT)
    }

    fun toast(@StringRes textResId: Int, duration: Int) {
        val toastVal = toast
        if (toastVal != null) {
            toastVal.cancel()
        }
        toast = Toast.makeText(activity, textResId, duration)
        toast?.show()
    }

    fun showProgressDialog(msg: String) {
        showProgressDialog(msg, true)
    }

    fun dismissProgressDialog() {
        if (loadingDialog.isShowing){
            loadingDialog.dismiss()
        }
    }

    fun showProgressDialog(msg: String, cancelable: Boolean) {
        try {
            loadingDialog.setCanceledOnTouchOutside(cancelable)
            loadingDialog.setMessage(msg)
            loadingDialog.show()
        }catch (e:Exception){

        }
    }


    // 权限请求
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    // 权限请求被拒绝
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (perms != null) {
            if (EasyPermissions.somePermissionPermanentlyDenied(activity, perms)) {
                AppSettingsDialog.Builder(this).build().show()
            }
        }
    }

    // 权限请求成功
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    inline fun <reified T : Activity> startActivity() {
        val intent = Intent(activity, T::class.java)
        activity.startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> onBackPressed()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onClick(v: View?) {

    }
}