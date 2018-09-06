package com.i4evercai.android.support.utils

import android.text.TextUtils
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

/**
 *
 * @Description: 日志工具类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 9:55
 * @version V1.0
 */
object LogUtils {

    //   private val DEBUG = BuildConfig.DEBUG
    private val DEBUG = AppUtils.isDebug
    private val TAG = "LogUtils.java"
    @JvmStatic
    fun e(message: String?) {
        e("", message)
    }

    @JvmStatic
    fun e(tag: String?, message: String?) {
        if (DEBUG) {
            val elements = Thread.currentThread()
                    .stackTrace
            if (elements.size < 4) {
                Log.e(TAG, "Stack to shallow")
            } else {
                var tagMsg: String;
                if (TextUtils.isEmpty(tag)) {
                    val fullClassName = elements[3].className
                    val className = fullClassName.substring(fullClassName
                            .lastIndexOf(".") + 1)
                    val methodName = elements[3].methodName
                    val lineNumber = elements[3].lineNumber
                    tagMsg = "$className.$methodName():$lineNumber";
                } else {
                    tagMsg = tag!!
                }
                if (message == null) {
                    Log.e(tagMsg, "message == null")
                } else if (message == "")
                    Log.e(tagMsg, "message == ''")
                else
                    Log.e(tagMsg, message)
            }
        }
    }

    @JvmStatic
    fun d(message: String?) {
        d("", message)
    }

    @JvmStatic
    fun d(tag: String?, message: String?) {
        if (DEBUG) {
            val elements = Thread.currentThread()
                    .stackTrace
            if (elements.size < 3) {
                Log.e(TAG, "Stack to shallow")
            } else {

                var tagMsg: String;
                if (TextUtils.isEmpty(tag)) {
                    val fullClassName = elements[3].className
                    val className = fullClassName.substring(fullClassName
                            .lastIndexOf(".") + 1)
                    val methodName = elements[3].methodName
                    val lineNumber = elements[3].lineNumber
                    tagMsg = "$className.$methodName():$lineNumber";
                } else {
                    tagMsg = tag!!
                }
                if (message == null) {
                    Log.d(tagMsg, "message == null")
                } else if (message == "")
                    Log.d(tagMsg, "message == ''")
                else
                    Log.d(tagMsg, message)
            }
        }
    }

    @JvmStatic
    fun i(message: String?) {
        i("", message)
    }

    @JvmStatic
    fun i(tag: String?, message: String?) {
        if (DEBUG) {
            val elements = Thread.currentThread()
                    .stackTrace
            if (elements.size < 3) {
                Log.e(TAG, "Stack to shallow")
            } else {
                var tagMsg: String;
                if (TextUtils.isEmpty(tag)) {
                    val fullClassName = elements[3].className
                    val className = fullClassName.substring(fullClassName
                            .lastIndexOf(".") + 1)
                    val methodName = elements[3].methodName
                    val lineNumber = elements[3].lineNumber
                    tagMsg = "$className.$methodName():$lineNumber";
                } else {
                    tagMsg = tag!!;
                }
                if (message == null) {
                    Log.i(tagMsg, "message == null")
                } else if (message == "")
                    Log.i(tagMsg, "message == ''")
                else
                    Log.i(tagMsg, message)
            }
        }
    }

    @JvmStatic
    fun w(message: String?) {
        w("", message)
    }

    @JvmStatic
    fun w(tag: String?, message: String?) {
        if (DEBUG) {
            val elements = Thread.currentThread()
                    .stackTrace
            if (elements.size < 3) {
                Log.e(TAG, "Stack to shallow")
            } else {
                var tagMsg: String;
                if (TextUtils.isEmpty(tag)) {
                    val fullClassName = elements[3].className
                    val className = fullClassName.substring(fullClassName
                            .lastIndexOf(".") + 1)
                    val methodName = elements[3].methodName
                    val lineNumber = elements[3].lineNumber
                    tagMsg = "$className.$methodName():$lineNumber";
                } else {
                    tagMsg = tag!!
                }

                if (message == null) {
                    Log.w(tagMsg,
                            "message == null")
                } else if (message == "")
                    Log.w(tagMsg,
                            "message == ''")
                else
                    Log.w(tagMsg,
                            message)
            }
        }
    }

    @JvmStatic
    fun v(message: String?) {
        v("", message)
    }

    @JvmStatic
    fun v(tag: String?, message: String?) {
        if (DEBUG) {
            val elements = Thread.currentThread()
                    .stackTrace
            if (elements.size < 3) {
                Log.e(TAG, "Stack to shallow")
            } else {
                var tagMsg: String;
                if (TextUtils.isEmpty(tag)) {
                    val fullClassName = elements[3].className
                    val className = fullClassName.substring(fullClassName
                            .lastIndexOf(".") + 1)
                    val methodName = elements[3].methodName
                    val lineNumber = elements[3].lineNumber
                    tagMsg = "$className.$methodName():$lineNumber";
                } else {
                    tagMsg = tag!!
                }
                if (message == null) {
                    Log.v(tagMsg, "message == null")
                } else if (message == "")
                    Log.v(tagMsg, "message == ''")
                else
                    Log.v(tagMsg, message)
            }
        }
    }

    @JvmStatic
    fun appendLog(text: String) {
        val logFile = File("sdcard/log.file")
        if (!logFile.exists()) {
            try {
                logFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            val buf = BufferedWriter(FileWriter(logFile, true))
            buf.append(text)
            buf.newLine()
            buf.close()
        } catch (e: IOException) {

            e.printStackTrace()
        }

    }
}

