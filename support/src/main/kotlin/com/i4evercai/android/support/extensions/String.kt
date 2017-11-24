package com.i4evercai.android.support.extensions

import android.text.TextUtils
import android.util.Base64
import com.i4evercai.android.support.utils.RegexUtils

fun String.fromBase64() = Base64.decode(this, Base64.DEFAULT)

fun String.md5() = toByteArray().md5()
fun String.sha1() = toByteArray().sha1()
fun String.sha256() = toByteArray().sha256()
fun String.sha512() = toByteArray().sha512()
fun String.isPhone() = RegexUtils.isMobilePhoneNumber(this)
fun String?.isEmpty() = TextUtils.isEmpty(this)
fun String?.isNotEmpty() = !isEmpty()