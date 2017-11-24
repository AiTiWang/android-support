package com.i4evercai.android.support.utils


import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @Description: 正则表达式工具类，提供一些常用的正则表达式
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/21 11:05
 * @version V1.0
 */
object RegexUtils {
    /**
     * 匹配全网IP的正则表达式
     */
    val IP_REGEX = "^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))$"

    /**
     * 匹配手机号码的正则表达式
     * <br></br>支持130——139、150——153、155——159、180、183、185、186、188、189号段
     */
    val PHONE_NUMBER_REGEX = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$"
    /**
     * 匹配400/800电话
     */
    val TEL_400_800_NUMBER_REGEX = "^[48]00\\d{7}|[48]00-\\d{3}-\\d{4}$"

    val TEL_NUMBER_REGEX = "^(0\\d{2}-\\d{7,8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$"
    /**
     * 匹配邮箱的正则表达式
     * <br></br>"www."可省略不写
     */
    val EMAIL_REGEX = "^(www\\.)?\\w+@\\w+(\\.\\w+)+$"

    /**
     * 匹配汉字的正则表达式，个数限制为一个或多个
     */
    val CHINESE_REGEX = "^[\u4e00-\u9f5a]+$"

    /**
     * 匹配正整数的正则表达式，个数限制为一个或多个
     */
    val POSITIVE_INTEGER_REGEX = "^\\d+$"

    /**
     * 匹配身份证号的正则表达式
     */
    val ID_CARD = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$"

    /**
     * 匹配邮编的正则表达式
     */
    val ZIP_CODE = "^\\d{6}$"

    /**
     * 匹配URL的正则表达式
     */
    val URL = "^(([hH][tT]{2}[pP][sS]?)|([fF][tT][pP]))\\:\\/\\/[wW]{3}\\.[\\w-]+\\.\\w{2,4}(\\/.*)?$"
    /**
     * 匹配密码的正则表达式
     */
    val PASSWORD = "^(?![0-9]+\$)(?![a-zA-Z]+\$)[0-9A-Za-z]{6,14}\$$"

    /**
     * 匹配给定的字符串是否是一个邮箱账号，"www."可省略不写

     * @param string 给定的字符串
     * *
     * @return true：是
     */
    @JvmStatic
    fun isEmail(string: String): Boolean {
        return string.matches(EMAIL_REGEX.toRegex())
    }
    @JvmStatic
    fun isPassword(string: String): Boolean {
        return string.matches(PASSWORD.toRegex())
    }

    /**
     * 匹配给定的字符串是否是一个手机号码，支持130——139、150——153、155——159、180、183、185、186、188、189号段

     * @param string 给定的字符串
     * *
     * @return true：是
     */
    @JvmStatic
    fun isMobilePhoneNumber(string: String): Boolean {
        return string.matches(PHONE_NUMBER_REGEX.toRegex())
    }
    @JvmStatic
    fun isTelNumber(string: String): Boolean {
        val isTel = string.matches(TEL_NUMBER_REGEX.toRegex())
        if (isTel){
            return true
        }else{
            val isTel400 = string.matches(TEL_400_800_NUMBER_REGEX.toRegex())
            return isTel400
        }

    }
    @JvmStatic
    fun isTelOrMobilePhoneNumber(string: String): Boolean {
        val isMobile = isMobilePhoneNumber(string)
        if (isMobile){
            return  true
        }else{
            val isTel = isTelNumber(string)
            return  isTel
        }
    }

    /**
     * 匹配给定的字符串是否是一个全网IP

     * @param string 给定的字符串
     * *
     * @return true：是
     */
    @JvmStatic
    fun isIp(string: String): Boolean {
        return string.matches(IP_REGEX.toRegex())
    }

    /**
     * 匹配给定的字符串是否全部由汉子组成

     * @param string 给定的字符串
     * *
     * @return true：是
     */
    @JvmStatic
    fun isChinese(string: String): Boolean {
        return string.matches(CHINESE_REGEX.toRegex())
    }

    /**
     * 验证给定的字符串是否全部由正整数组成

     * @param string 给定的字符串
     * *
     * @return true：是
     */
    @JvmStatic
    fun isPositiveInteger(string: String): Boolean {
        return string.matches(POSITIVE_INTEGER_REGEX.toRegex())
    }

    /**
     * 验证给定的字符串是否是身份证号
     * <br></br>
     * <br></br>身份证15位编码规则：dddddd yymmdd xx p
     * <br></br>dddddd：6位地区编码
     * <br></br>yymmdd：出生年(两位年)月日，如：910215
     * <br></br>xx：顺序编码，系统产生，无法确定
     * <br></br>p：性别，奇数为男，偶数为女
     * <br></br>
     * <br></br>
     * <br></br>身份证18位编码规则：dddddd yyyymmdd xxx y
     * <br></br>dddddd：6位地区编码
     * <br></br>yyyymmdd：出生年(四位年)月日，如：19910215
     * <br></br>xxx：顺序编码，系统产生，无法确定，奇数为男，偶数为女
     * <br></br>y：校验码，该位数值可通过前17位计算获得
     * <br></br>前17位号码加权因子为 Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ]
     * <br></br>验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
     * <br></br>如果验证码恰好是10，为了保证身份证是十八位，那么第十八位将用X来代替 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )
     * <br></br>i为身份证号码1...17 位; Y_P为校验码Y所在校验码数组位置

     * @param string
     * *
     * @return
     */
    @JvmStatic
    fun isIdCard(string: String): Boolean {
        return string.matches(ID_CARD.toRegex())
    }

    /**
     * 验证给定的字符串是否是邮编

     * @param string
     * *
     * @return
     */
    @JvmStatic
    fun isZipCode(string: String): Boolean {
        return string.matches(ZIP_CODE.toRegex())
    }

    /**
     * 验证给定的字符串是否是URL，仅支持http、https、ftp

     * @param string
     * *
     * @return
     */
    @JvmStatic
    fun isURL(string: String): Boolean {
        return string.matches(URL.toRegex())
    }

    /**
     * 根据身份证的号码算出当前身份证持有者的性别和年龄

     * @return
     * *
     * @throws Exception
     */
    @JvmStatic
    fun getCarInfo(CardCode: String?): Map<String, Any>? {
        try {
            if (CardCode != null && isIdCard(CardCode) && CardCode.length == 18) {
                return getCarInfo18(CardCode)
            }
            if (CardCode != null && isIdCard(CardCode) && CardCode.length == 15) {
                return getCarInfo15(CardCode)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * 根据身份证的号码算出当前身份证持有者的性别和年龄 18位身份证

     * @return
     * *
     * @throws Exception
     */
    @JvmStatic
    @Throws(Exception::class)
    private fun getCarInfo18(CardCode: String): Map<String, Any> {
        val map = HashMap<String, Any>()
        val year = CardCode.substring(6).substring(0, 4)// 得到年份
        val yue = CardCode.substring(10).substring(0, 2)// 得到月份
        val day = CardCode.substring(12).substring(0, 2)//得到日
        val sex: String
        if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
            sex = "女"
        } else {
            sex = "男"
        }
        val date = Date()// 得到当前的系统时间
        val format = SimpleDateFormat("yyyy-MM-dd")
        val fyear = format.format(date).substring(0, 4)// 当前年份
        val fyue = format.format(date).substring(5, 7)// 月份
        // String fday=format.format(date).substring(8,10);
        var age = 0
        if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1
        } else {// 当前用户还没过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year)
        }
        map.put("sex", sex)
        map.put("age", age)
        map.put("birth", "$year-$yue-$day")
        return map
    }

    /**
     * 15位身份证的验证

     * @param
     * *
     * @throws Exception
     */
    @JvmStatic
    @Throws(Exception::class)
    private fun getCarInfo15(card: String): Map<String, Any> {
        val map = HashMap<String, Any>()
        val uyear = "19" + card.substring(6, 8)// 年份
        val uyue = card.substring(8, 10)// 月份
        val uday = card.substring(10, 12)//日
        val usex = card.substring(14, 15)// 用户的性别
        val sex: String
        if (Integer.parseInt(usex) % 2 == 0) {
            sex = "女"
        } else {
            sex = "男"
        }
        val date = Date()// 得到当前的系统时间
        val format = SimpleDateFormat("yyyy-MM-dd")
        val fyear = format.format(date).substring(0, 4)// 当前年份
        val fyue = format.format(date).substring(5, 7)// 月份
        // String fday=format.format(date).substring(8,10);
        var age = 0
        if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
            age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1
        } else {// 当前用户还没过生
            age = Integer.parseInt(fyear) - Integer.parseInt(uyear)
        }
        map.put("sex", sex)
        map.put("age", age)
        map.put("birth", "$uyear-$uyue-$uday")
        return map
    }
}
