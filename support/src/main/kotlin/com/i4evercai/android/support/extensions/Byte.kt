package com.i4evercai.android.support.extensions


//

infix fun Byte.and(count: Int): Byte = (toInt() and 0x000000ff and count).toByte()
infix fun Byte.or(i: Int): Byte = (toInt() and 0x000000ff or i).toByte()
infix fun Byte.xor(i: Int): Byte = (toInt() and 0x000000ff xor i).toByte()
infix fun Byte.shl(count: Int): Byte = (toInt() and 0x000000ff shl count).toByte()
infix fun Byte.shr(count: Int): Byte = (toInt() and 0x000000ff shr count).toByte()
infix fun Byte.ushr(count: Int): Byte = (toInt() and 0x000000ff ushr count).toByte()

fun Byte.char() = Pair(Character.forDigit((this ushr 4).toInt(), 16), Character.forDigit((this and 0x0f).toInt(), 16))
fun Byte.string() = char().run { "$first$second" }