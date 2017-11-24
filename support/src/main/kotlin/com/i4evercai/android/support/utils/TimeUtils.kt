package com.i4evercai.android.support.utils


import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @Description: 时间相关工具类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 11:25
 * @version V1.0
 */
object TimeUtils {
    val DEFAULT_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val DATE_FORMAT_DATE = SimpleDateFormat("yyyy-MM-dd")

    /**
     * 时间戳转String 时间格式
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    @JvmStatic
    fun getTime(timeInMillis: Long, dateFormat: SimpleDateFormat): String {
        return dateFormat.format(Date(timeInMillis))
    }

    /**
     * 时间戳转默认时间格式 [.DEFAULT_DATE_FORMAT]
     *
     * @param timeInMillis
     * @return
     */
    @JvmStatic
    fun getTime(timeInMillis: Long): String {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT)
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    @JvmStatic
    fun getCurrentTimeInLong(): Long {
        return System.currentTimeMillis()
    }

    /**
     * 获取当时时间，默认时间格式 [.DEFAULT_DATE_FORMAT]
     *
     * @return
     */
    @JvmStatic
    fun getCurrentTimeInString(): String {
        return getTime(getCurrentTimeInLong())
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    @JvmStatic
    fun getCurrentTimeInString(dateFormat: SimpleDateFormat): String {
        return getTime(getCurrentTimeInLong(), dateFormat)
    }

    @JvmStatic
    fun getCalendar(date: Date): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }

    @JvmStatic
    fun secToTime(time: Int): String {
        var timeStr: String? = null
        var hour = 0
        var minute = 0
        var second = 0
        if (time <= 0)
            return "00:00"
        else {
            minute = time / 60
            if (minute < 60) {
                second = time % 60
                timeStr = unitFormat(minute) + ":" + unitFormat(second)
            } else {
                hour = minute / 60
                if (hour > 99)
                    return "99:59:59"
                minute = minute % 60
                second = time - hour * 3600 - minute * 60
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second)
            }
        }
        return timeStr
    }

    @JvmStatic
    private fun unitFormat(i: Int): String {
        var retStr: String? = null
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i)
        else
            retStr = "" + i
        return retStr
    }
}
