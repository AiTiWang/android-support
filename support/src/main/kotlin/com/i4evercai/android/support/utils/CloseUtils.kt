package com.i4evercai.android.support.utils

import java.io.Closeable
import java.io.IOException

object CloseUtils {

    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    @JvmStatic
    fun closeIO(vararg closeables: Closeable?) {
        if (closeables == null) return
        for (closeable in closeables) {
            if (closeable != null) {
                try {
                    closeable!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    /**
     * 安静关闭IO
     *
     * @param closeables closeable
     */
    @JvmStatic
    fun closeIOQuietly(vararg closeables: Closeable?) {
        if (closeables == null) return
        for (closeable in closeables) {
            if (closeable != null) {
                try {
                    closeable!!.close()
                } catch (ignored: IOException) {
                }

            }
        }
    }
}