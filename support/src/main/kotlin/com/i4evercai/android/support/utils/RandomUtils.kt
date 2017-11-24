package com.i4evercai.android.support.utils


import android.text.TextUtils
import java.util.*


/**
 *
 * @Description: 随机数工具类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 11:29
 * @version V1.0
 */
object RandomUtils {
    val NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val NUMBERS = "0123456789"
    val LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz"
    /**
     * get a fixed-length random string, its a mixture of uppercase, lowercase letters and numbers

     * @param length
     * *
     * @return
     * *
     * @see RandomUtils.getRandom
     */
    @JvmStatic
    fun getRandomNumbersAndLetters(length: Int): String {
        return getRandom(NUMBERS_AND_LETTERS, length)
    }

    /**
     * get a fixed-length random string, its a mixture of numbers

     * @param length
     * *
     * @return
     * *
     * @see RandomUtils.getRandom
     */
    @JvmStatic
    fun getRandomNumbers(length: Int): String {
        return getRandom(NUMBERS, length)
    }

    /**
     * get a fixed-length random string, its a mixture of uppercase and lowercase letters

     * @param length
     * *
     * @return
     * *
     * @see RandomUtils.getRandom
     */
    @JvmStatic
    fun getRandomLetters(length: Int): String {
        return getRandom(LETTERS, length)
    }

    /**
     * get a fixed-length random string, its a mixture of uppercase letters

     * @param length
     * *
     * @return
     * *
     * @see RandomUtils.getRandom
     */
    @JvmStatic
    fun getRandomCapitalLetters(length: Int): String {
        return getRandom(CAPITAL_LETTERS, length)
    }

    /**
     * get a fixed-length random string, its a mixture of lowercase letters

     * @param length
     * *
     * @return
     * *
     * @see RandomUtils.getRandom
     */
    @JvmStatic
    fun getRandomLowerCaseLetters(length: Int): String {
        return getRandom(LOWER_CASE_LETTERS, length)
    }

    /**
     * get a fixed-length random string, its a mixture of chars in source

     * @param source
     * *
     * @param length
     * *
     * @return
     * *          * if source is null or empty, return null
     * *          * else see [RandomUtils.getRandom]
     * *
     */
    @JvmStatic
    fun getRandom(source: String, length: Int): String {
        return if (TextUtils.isEmpty(source)) "" else getRandom(source.toCharArray(), length)
    }

    /**
     * get a fixed-length random string, its a mixture of chars in sourceChar

     * @param sourceChar
     * *
     * @param length
     * *
     * @return
     * *          * if sourceChar is null or empty, return null
     * *          * if length less than 0, return null
     * *
     */
    @JvmStatic
    fun getRandom(sourceChar: CharArray?, length: Int): String {
        if (sourceChar == null || sourceChar.size == 0 || length < 0) {
            return ""
        }

        val str = StringBuilder(length)
        val random = Random()
        for (i in 0..length - 1) {
            str.append(sourceChar[random.nextInt(sourceChar.size)])
        }
        return str.toString()
    }

    /**
     * get random int between 0 and max

     * @param max
     * *
     * @return
     * *          * if max <= 0, return 0
     * *          * else return random int between 0 and max
     * *
     */
    @JvmStatic
    fun getRandom(max: Int): Int {
        return getRandom(0, max)
    }

    /**
     * get random int between min and max

     * @param min
     * *
     * @param max
     * *
     * @return
     * *          * if min > max, return 0
     * *          * if min == max, return min
     * *          * else return random int between min and max
     * *
     */
    @JvmStatic
    fun getRandom(min: Int, max: Int): Int {
        if (min > max) {
            return 0
        }
        if (min == max) {
            return min
        }
        return min + Random().nextInt(max - min)
    }

    /**
     * Shuffling algorithm, Randomly permutes the specified array using a default source of randomness

     * @param objArray
     * *
     * @return
     */
    @JvmStatic
    fun shuffle(objArray: Array<Any>?): Boolean {
        if (objArray == null) {
            return false
        }

        return shuffle(objArray, getRandom(objArray.size))
    }

    /**
     * Shuffling algorithm, Randomly permutes the specified array

     * @param objArray
     * *
     * @param shuffleCount
     * *
     * @return
     */
    @JvmStatic
    fun shuffle(objArray: Array<Any>?, shuffleCount: Int): Boolean {
        if (objArray == null || shuffleCount < 0) {
            return false
        }
        val length: Int = objArray.size
        if (length < shuffleCount) {
            return false
        }

        for (i in 1..shuffleCount) {
            val random = getRandom(length - i)
            val temp = objArray[length - i]
            objArray[length - i] = objArray[random]
            objArray[random] = temp
        }
        return true
    }

    /**
     * Shuffling algorithm, Randomly permutes the specified int array using a default source of randomness

     * @param intArray
     * *
     * @return
     */
    @JvmStatic
    fun shuffle(intArray: IntArray?): IntArray? {
        if (intArray == null) {
            return null
        }

        return shuffle(intArray, getRandom(intArray.size))
    }

    /**
     * Shuffling algorithm, Randomly permutes the specified int array

     * @param intArray
     * *
     * @param shuffleCount
     * *
     * @return
     */
    @JvmStatic
    fun shuffle(intArray: IntArray?, shuffleCount: Int): IntArray? {

        if (intArray == null || shuffleCount < 0) {
            return null
        }
        val length: Int = intArray.size
        if (length < shuffleCount) {
            return null
        }
        val out = IntArray(shuffleCount)
        for (i in 1..shuffleCount) {
            val random = getRandom(length - i)
            out[i - 1] = intArray[random]
            val temp = intArray[length - i]
            intArray[length - i] = intArray[random]
            intArray[random] = temp
        }
        return out
    }

}