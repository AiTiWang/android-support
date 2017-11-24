package com.i4evercai.android.support.extensions

import android.util.Base64
import java.security.MessageDigest


inline fun ByteArray.string() = map { it.string() }.joinToString("")

inline fun ByteArray.base64() = String(Base64.encode(this, Base64.DEFAULT))

inline fun ByteArray.md5() = MessageDigest.getInstance("MD5").run { digest(this@md5).string() }
inline fun ByteArray.sha1() = MessageDigest.getInstance("SHA-1").run { digest(this@sha1).string() }
inline fun ByteArray.sha256() = MessageDigest.getInstance("SHA-256").run { digest(this@sha256).string() }
inline fun ByteArray.sha512() = MessageDigest.getInstance("SHA-512").run { digest(this@sha512).string() }