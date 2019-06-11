package com.i4evercai.android.support.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import java.io.File


/**
 *
 * @Description: Intent相关工具类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 11:14
 * @version V1.0
 */
object IntentUtils {



    /**
     * 获取安装App(支持6.0)的意图
     *
     * @param file 文件
     * @return intent
     */
    fun getInstallAppIntent(file: File,authority:String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        val type: String?

        if (Build.VERSION.SDK_INT < 23) {
            type = "application/vnd.android.package-archive"
        } else {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(FileUtils.getFileExtension(file))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            val contentUri = FileProvider.getUriForFile(AppUtils.appContext, authority, file)
            intent.setDataAndType(contentUri, type)
        }
        intent.setDataAndType(Uri.fromFile(file), type)
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    /**
     * 获取卸载App的意图
     *
     * @param packageName 包名
     * @return intent
     */
    fun getUninstallAppIntent(packageName: String): Intent {
        val intent = Intent(Intent.ACTION_DELETE)
        intent.data = Uri.parse("package:$packageName")
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    /**
     * 获取打开App的意图
     *
     * @param context     上下文
     * @param packageName 包名
     * @return intent
     */
    fun getLaunchAppIntent(context: Context, packageName: String): Intent {
        return context.getPackageManager().getLaunchIntentForPackage(packageName)
    }

    /**
     * 获取App具体设置的意图
     *
     * @param packageName 包名
     * @return intent
     */
    fun getAppDetailsSettingsIntent(packageName: String): Intent {
        val intent = Intent("android.settings.APPLICATION_DETAILS_SETTINGS")
        intent.data = Uri.parse("package:$packageName")
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    /**
     * 获取分享文本的意图
     *
     * @param content 分享文本
     * @return intent
     */
    fun getShareTextIntent(content: String): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, content)
        return intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    /**
     * 获取分享图片的意图
     *
     * @param content   文本
     * @param imagePath 图片文件路径
     * @return intent
     */
    fun getShareImageIntent(content: String, imagePath: String): Intent?{
        return getShareImageIntent(content, FileUtils.getFileByPath(imagePath))
    }

    /**
     * 获取分享图片的意图
     *
     * @param content 文本
     * @param image   图片文件
     * @return intent
     */
    fun getShareImageIntent(content: String, image: File?): Intent? {
        return if (!FileUtils.isFileExists(image)) null else getShareImageIntent(content, Uri.fromFile(image))
    }

    /**
     * 获取分享图片的意图
     *
     * @param content 分享文本
     * @param uri     图片uri
     * @return intent
     */
    fun getShareImageIntent(content: String, uri: Uri): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, content)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.type = "image/*"
        return intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName 包名
     * @param className   全类名
     * @return intent
     */
    fun getComponentIntent(packageName: String, className: String): Intent {
        return getComponentIntent(packageName, className, null)
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName 包名
     * @param className   全类名
     * @param bundle      bundle
     * @return intent
     */
    fun getComponentIntent(packageName: String, className: String, bundle: Bundle?): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        if (bundle != null) intent.putExtras(bundle)
        val cn = ComponentName(packageName, className)
        intent.component = cn
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    /**
     * 获取关机的意图
     *
     * 需添加权限 `<uses-permission android:name="android.permission.SHUTDOWN"/>`
     *
     * @return intent
     */
    fun getShutdownIntent(): Intent {
        val intent = Intent(Intent.ACTION_SHUTDOWN)
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }



    /**
     * 获取拍照的意图
     *
     * @param outUri 输出的uri
     * @return 拍照的意图
     */
    fun getCaptureIntent(outUri: Uri): Intent {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri)
        return intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK)
    }

}