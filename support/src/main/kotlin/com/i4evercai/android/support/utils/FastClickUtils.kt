package com.i4evercai.android.support.utils

import android.os.SystemClock

object FastClickUtils {
    private var lastClickTime = 0L
    private var MIN_DELAY_TIME = 0.05 * 1000


    @JvmStatic
    fun isFastClick(): Boolean {
        var flag = true
        val currentClickTime = SystemClock.elapsedRealtime()
        if (currentClickTime - lastClickTime > MIN_DELAY_TIME) {
            flag = false
        } else {
        }
        lastClickTime = currentClickTime
        return flag
    }

    /**
     * 设置两次点击事件最小时间
     * @param minDelayTime  最小时间(毫秒)
     */
    fun setMinDelayTime(minDelayTime: Long) {
        this.MIN_DELAY_TIME = minDelayTime * 1.0
    }
}