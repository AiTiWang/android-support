package com.i4evercai.android.support.base

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.i4evercai.android.support.widget.LoadingDialog
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import io.reactivex.Observable
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 *
 * @Description: 基类Activity
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 14:46
 * @version V1.0
 */
abstract class BaseFragment : Fragment(), View.OnClickListener, LifecycleOwner, EasyPermissions.PermissionCallbacks {
    private val STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"

    protected lateinit var mContext: Context
    protected lateinit var mView: View
    private var toast: Toast? = null

    private var mIsVisible: Boolean = false//是否可见状态
    private var isPrepared: Boolean = false//View已经初始化完成

    private var isFirstLoad = true  //是否第一次加载完
    private var isInitView = false
    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }
    protected val loadingDialog: LoadingDialog by lazy { LoadingDialog(mContext) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isFirstLoad = true
        //绑定View
        mView = inflater!!.inflate(getLayoutId(), container, false)
        isPrepared = true
        isInitView = false
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isInitView) {

            isInitView = true
            initViews()
        }

        lazyLoad()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState!!.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden)
    }

    override fun onAttach(context: Context?) {
        this.mContext = context!!
        super.onAttach(context)

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            mIsVisible = true
            onVisible()
        } else {
            mIsVisible = false
            onInvisible()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            mIsVisible = true
            onVisible()
        } else {
            mIsVisible = false
            onInvisible()
        }
    }

    open protected fun onVisible() {
        lazyLoad()
    }

    open protected fun onInvisible() {}
    private fun lazyLoad() {
        if (!isPrepared || !mIsVisible || !isFirstLoad) return
        isFirstLoad = false
        lazyLoadData()
    }


    abstract fun getLayoutId(): Int
    abstract fun initViews()
    abstract fun lazyLoadData()

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    override fun onStop() {

        super.onStop()
    }

    override fun onPause() {

        super.onPause()
    }

    override fun onDestroy() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
        super.onDestroy()
    }

    fun toast(text: CharSequence) {
        toast(text, Toast.LENGTH_SHORT)
    }

    fun toast(text: CharSequence, duration: Int) {
        val toastVal = toast
        if (toastVal != null) {
            toastVal.cancel()

        }
        toast = Toast.makeText(context, text, duration)
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
        toast = Toast.makeText(context, textResId, duration)
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
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>?) {
        if (perms != null) {
            if (EasyPermissions.somePermissionPermanentlyDenied(activity!!, perms)) {
                AppSettingsDialog.Builder(this).build().show()
            }
        }
    }
    inline fun <reified T: Activity> startActivity(){
        val intent = Intent(activity,T::class.java)
        activity?.startActivity(intent)
    }
    // 权限请求成功
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {

    }

    override fun onClick(v: View?) {

    }


}
