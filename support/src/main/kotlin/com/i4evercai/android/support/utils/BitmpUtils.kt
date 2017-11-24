package com.i4evercai.android.support.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.*

/**
 *
 * @Description: 图片处理类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/28 15:17
 * @version V1.0
 */
object BitmpUtils {
    @JvmStatic
    fun saveBitmap(bm: Bitmap, filePath: String): Boolean {
        return saveBitmap(bm, File(filePath))

    }

    @JvmStatic
    fun saveBitmap(bm: Bitmap, file: File): Boolean {
        try {

            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }
            if (file.exists()) {
                file.delete()
            }
            val filePath = file.path
            val suffixIndex = filePath.lastIndexOf('.')
            val suffix = filePath.substring(suffixIndex).toLowerCase()
            val out = FileOutputStream(file)
            var format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG
            if (suffix == ".jpg")
                format = Bitmap.CompressFormat.JPEG
            if (suffix == ".png")
                format = Bitmap.CompressFormat.PNG
            bm.compress(format, 80, out)
            out.flush()
            out.close()
            return true
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return false
    }

    @JvmStatic
    fun compressImageFile(context: Context, file: File, maxSize: Int): File {

        if (file.length() < maxSize * 1024 * 1024) {
            return file
        }

        val bitmap = BitmapFactory.decodeFile(file.path)
        val compressBitmap = compressImage(bitmap, maxSize)
        val timeMillis = System.currentTimeMillis()
        val thumFile = FileUtils.getNewCacheFile(context, "${timeMillis}.jpg")
        val isSave = saveBitmap(compressBitmap, thumFile)
        if (isSave) {
            return thumFile
        } else {
            throw Exception("保存压缩后的图片失败")
        }
    }

    @JvmStatic
    fun compressImage(bitmap: Bitmap, maxSize: Int): Bitmap {
        val os = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
        var options = 100
        if (os.toByteArray().size / 1024 <= maxSize) {
            return bitmap
        }
        while (os.toByteArray().size / 1024 > maxSize) {
            options = options - 10;// 每次都减少10
            os.reset();// 重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, os)
        }
        val isBm = ByteArrayInputStream(os.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        val bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap
    }
}