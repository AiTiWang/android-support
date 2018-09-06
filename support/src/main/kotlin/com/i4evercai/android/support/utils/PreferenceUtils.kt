package com.i4evercai.android.support.utils


import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


/**
 *
 * @Description: Preference存储类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 10:10
 * @version V1.0
 */
object PreferenceUtils {
    private val DEFAULT_FILE_NAME = "sp_default_data"
    @JvmStatic
    fun getString(context: Context, key: String, defaultValue: String): String {
        return getString(context, DEFAULT_FILE_NAME, key, defaultValue)
    }

    @JvmStatic
    fun putString(context: Context, key: String, value: String) {
        putString(context, DEFAULT_FILE_NAME, key, value)
    }

    @JvmStatic
    fun getBoolean(context: Context, key: String,
                   defaultValue: Boolean): Boolean {
        return getBoolean(context, DEFAULT_FILE_NAME, key, defaultValue)
    }

    @JvmStatic
    fun hasKey(context: Context, key: String): Boolean {
        return hasKey(context, DEFAULT_FILE_NAME, key)
    }

    @JvmStatic
    fun putBoolean(context: Context, key: String, value: Boolean) {
        putBoolean(context, DEFAULT_FILE_NAME, key, value)
    }

    @JvmStatic
    fun putInt(context: Context, key: String, value: Int) {
        putInt(context, DEFAULT_FILE_NAME, key, value)
    }

    @JvmStatic
    fun getInt(context: Context, key: String, defaultValue: Int): Int {
        return getInt(context, DEFAULT_FILE_NAME, key, defaultValue)
    }

    @JvmStatic
    fun putFloat(context: Context, key: String, value: Float) {
        putFloat(context, DEFAULT_FILE_NAME, key, value)
    }

    @JvmStatic
    fun getFloat(context: Context, key: String, defaultValue: Float): Float {
        return getFloat(context, DEFAULT_FILE_NAME, key, defaultValue)
    }

    @JvmStatic
    fun putLong(context: Context, key: String, value: Long) {
        putLong(context, DEFAULT_FILE_NAME, key, value)
    }

    @JvmStatic
    fun getLong(context: Context, key: String, defaultValue: Long): Long {
        return getLong(context, DEFAULT_FILE_NAME, key, defaultValue)
    }

    @JvmStatic
    fun getString(context: Context, preferencesFileName: String, key: String, defaultValue: String): String {
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        return sp.getString(key, defaultValue)
    }

    @JvmStatic
    fun putString(context: Context, preferencesFileName: String, key: String, value: String) {
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        sp.edit().putString(key, value).apply()
    }

    @JvmStatic
    fun getBoolean(context: Context, preferencesFileName: String, key: String,
                   defaultValue: Boolean): Boolean {
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        return sp.getBoolean(key, defaultValue)
    }

    @JvmStatic
    fun hasKey(context: Context, preferencesFileName: String, key: String): Boolean {
        return context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE).contains(key)
    }

    @JvmStatic
    fun putBoolean(context: Context, preferencesFileName: String, key: String, value: Boolean) {
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        sp.edit().putBoolean(key, value).apply()
    }

    @JvmStatic
    fun putInt(context: Context, preferencesFileName: String, key: String, value: Int) {
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        sp.edit().putInt(key, value).apply()
    }

    @JvmStatic
    fun getInt(context: Context, preferencesFileName: String, key: String, defaultValue: Int): Int {
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        return sp.getInt(key, defaultValue)
    }

    @JvmStatic
    fun putFloat(context: Context, preferencesFileName: String, key: String, value: Float) {
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        sp.edit().putFloat(key, value).apply()
    }

    @JvmStatic
    fun getFloat(context: Context, preferencesFileName: String, key: String, defaultValue: Float): Float {
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        return sp.getFloat(key, defaultValue)
    }

    @JvmStatic
    fun putLong(context: Context, preferencesFileName: String, key: String, value: Long) {
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        sp.edit().putLong(key, value).apply()
    }

    @JvmStatic
    fun getLong(context: Context, preferencesFileName: String, key: String, defaultValue: Long): Long {
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        return sp.getLong(key, defaultValue)
    }
    @JvmStatic
    fun removeKey(context: Context, preferencesFileName: String, key: String){
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        sp.edit().remove(key).apply()
    }
    @JvmStatic
    fun removeKey(context: Context, key: String){
        removeKey(context,DEFAULT_FILE_NAME,key)

    }
    @JvmStatic
    fun clearPreference(context: Context, preferencesFileName: String) {
        val sp = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear()
        editor.apply()
    }

    @JvmStatic
    fun clearPreference(context: Context, sp: SharedPreferences) {
        val editor = sp.edit()
        editor.clear()
        editor.apply()
    }

}