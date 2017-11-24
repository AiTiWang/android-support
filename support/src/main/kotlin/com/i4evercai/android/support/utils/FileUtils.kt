package com.i4evercai.android.support.utils

import android.content.Context
import android.os.Environment
import com.i4evercai.android.support.extensions.md5
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

/**
 *
 * @Description: FileUtils
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/9/5 12:00
 * @version V1.0
 */
object FileUtils {


    /*fun copyFile(fromFile: File, toFile: File, reWrite: Boolean) {

    }*/


    /* fun saveFile(response: Response,path: String):Boolean{
         var inputStream: InputStream? = null
         // 储存下载文件的目录

         try {
             val body = response!!.body()!!
             inputStream = body.byteStream();
             if (FileUtils.saveFile(inputStream,path)){
                 // 下载完成
                 return true
             }

         } catch (e: Exception) {
             e.printStackTrace()
         } finally {
             try {
                 if (inputStream != null)
                     inputStream.close();
             } catch (e: IOException) {

             }

         }
         return false
     }*/
    @JvmStatic
    fun saveFile(inputStream: InputStream, path: String): Boolean {
        var fos: FileOutputStream? = null
        try {
            var len = inputStream.read()
            fos = FileOutputStream(File(path))
            while (len != -1) {
                fos.write(len)
                len = inputStream.read()
            }
            fos.flush()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        } finally {
            if (fos != null) {
                fos.close()
            }
        }
        return false
    }

    @JvmStatic
    fun getDownloadFileSavePath(context: Context, fileName: String): File {
        val cacheFile: File
        if (Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED)) {
            cacheFile = context.externalCacheDir
        } else {
            cacheFile = context.cacheDir
        }

        return File(cacheFile, fileName)
    }

    @JvmStatic
    fun getNewCacheFile(context: Context, fileName: String): File {
        val cacheFile: File
        if (Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED)) {
            cacheFile = context.externalCacheDir
        } else {
            cacheFile = context.cacheDir
        }
        return File(cacheFile, fileName)

    }

    @JvmStatic
    fun getMD5(file: File): String {
        if (file.isFile) {
            return file.readBytes().md5()
        }
        return ""
    }

    @JvmStatic
    fun getFileSizeString(file: File): String {
        var modifiedFileSize = ""
        var fileSize: Long = 0
        if (file.isFile) {
            fileSize = file.length()
            if (fileSize < 1024) {

                modifiedFileSize = "${fileSize} B"
            } else if (fileSize > 1024 && fileSize < (1024 * 1024)) {
                modifiedFileSize = "${Math.round((fileSize / 1024 * 100.0)) / 100.0} KB"
            } else {
                modifiedFileSize = "${Math.round((fileSize / (1024 * 1204) * 100.0)) / 100.0} MB"
            }
        } else {
            modifiedFileSize = "未知"
        }
        return modifiedFileSize
    }
}