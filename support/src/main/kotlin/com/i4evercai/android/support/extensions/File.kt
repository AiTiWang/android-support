package com.i4evercai.android.support.extensions

import java.io.File

fun File.md5() = readBytes().md5()
fun File.sha1() = readBytes().sha1()
fun File.sha256() = readBytes().sha256()
fun File.sha512() = readBytes().sha512()