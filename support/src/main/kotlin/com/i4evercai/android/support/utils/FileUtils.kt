package com.i4evercai.android.support.utils

import android.content.Context
import android.os.Environment
import com.i4evercai.android.support.extensions.md5
import java.io.*
import java.security.DigestInputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.collections.ArrayList


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

    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    @JvmStatic
    fun getFileByPath(filePath: String): File? {
        return if (StringUtils.isSpace(filePath)) null else File(filePath)
    }

    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return `true`: 存在<br></br>`false`: 不存在
     */
    @JvmStatic
    fun isFileExists(file: File?): Boolean {
        return file != null && file.exists()
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return `true`: 存在<br></br>`false`: 不存在
     */
    @JvmStatic
    fun isFileExists(filePath: String): Boolean {
        return isFileExists(getFileByPath(filePath))
    }

    /**
     * 重命名文件
     *
     * @param filePath 文件路径
     * @param newName  新名称
     * @return `true`: 重命名成功<br></br>`false`: 重命名失败
     */
    @JvmStatic
    fun rename(filePath: String, newName: String): Boolean {
        return rename(getFileByPath(filePath), newName)
    }

    /**
     * 重命名文件
     *
     * @param file    文件
     * @param newName 新名称
     * @return `true`: 重命名成功<br></br>`false`: 重命名失败
     */
    @JvmStatic
    fun rename(file: File?, newName: String): Boolean {
        // 文件为空返回false
        if (file == null) return false
        // 文件不存在返回false
        if (!file.exists()) return false
        // 新的文件名为空返回false
        if (StringUtils.isSpace(newName)) return false
        // 如果文件名没有改变返回true
        if (newName == file.name) return true
        val newFile = File(file.parent + File.separator + newName)
        // 如果重命名的文件已存在返回false
        return !newFile.exists() && file.renameTo(newFile)
    }

    /**
     * 判断是否是目录
     *
     * @param dirPath 目录路径
     * @return `true`: 是<br></br>`false`: 否
     */
    @JvmStatic
    fun isDir(dirPath: String): Boolean {
        val file = getFileByPath(dirPath)
        if (file == null) return false
        return isDir(file)
    }

    /**
     * 判断是否是目录
     *
     * @param file 文件
     * @return `true`: 是<br></br>`false`: 否
     */
    @JvmStatic
    fun isDir(file: File): Boolean {
        return isFileExists(file) && file.isDirectory
    }

    /**
     * 判断是否是文件
     *
     * @param filePath 文件路径
     * @return `true`: 是<br></br>`false`: 否
     */
    @JvmStatic
    fun isFile(filePath: String): Boolean {
        val file = getFileByPath(filePath)
        if (file == null) return false
        return isFile(file)
    }

    /**
     * 判断是否是文件
     *
     * @param file 文件
     * @return `true`: 是<br></br>`false`: 否
     */
    @JvmStatic
    fun isFile(file: File): Boolean {
        return isFileExists(file) && file.isFile
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath 目录路径
     * @return `true`: 存在或创建成功<br></br>`false`: 不存在或创建失败
     */
    @JvmStatic
    fun createOrExistsDir(dirPath: String): Boolean {
        return createOrExistsDir(getFileByPath(dirPath))
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return `true`: 存在或创建成功<br></br>`false`: 不存在或创建失败
     */
    @JvmStatic
    fun createOrExistsDir(file: File?): Boolean {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && if (file.exists()) file.isDirectory else file.mkdirs()
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param filePath 文件路径
     * @return `true`: 存在或创建成功<br></br>`false`: 不存在或创建失败
     */
    @JvmStatic
    fun createOrExistsFile(filePath: String): Boolean {
        return createOrExistsFile(getFileByPath(filePath))
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return `true`: 存在或创建成功<br></br>`false`: 不存在或创建失败
     */
    @JvmStatic
    fun createOrExistsFile(file: File?): Boolean {
        if (file == null) return false
        // 如果存在，是文件则返回true，是目录则返回false
        if (file.exists()) return file.isFile
        if (!createOrExistsDir(file.parentFile)) return false
        try {
            return file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }

    }

    /**
     * 判断文件是否存在，存在则在创建之前删除
     *
     * @param filePath 文件路径
     * @return `true`: 创建成功<br></br>`false`: 创建失败
     */
    @JvmStatic
    fun createFileByDeleteOldFile(filePath: String): Boolean {
        return createFileByDeleteOldFile(getFileByPath(filePath))
    }

    /**
     * 判断文件是否存在，存在则在创建之前删除
     *
     * @param file 文件
     * @return `true`: 创建成功<br></br>`false`: 创建失败
     */
    @JvmStatic
    fun createFileByDeleteOldFile(file: File?): Boolean {
        if (file == null) return false
        // 文件存在并且删除失败返回false
        if (file.exists() && file.isFile && !file.delete()) return false
        // 创建目录失败返回false
        if (!createOrExistsDir(file.parentFile)) return false
        try {
            return file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }

    }

    /**
     * 复制或移动目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @param isMove      是否移动
     * @return `true`: 复制或移动成功<br></br>`false`: 复制或移动失败
     */
    @JvmStatic
    private fun copyOrMoveDir(srcDirPath: String, destDirPath: String, isMove: Boolean): Boolean {
        return copyOrMoveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), isMove)
    }

    /**
     * 复制或移动目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @param isMove  是否移动
     * @return `true`: 复制或移动成功<br></br>`false`: 复制或移动失败
     */
    @JvmStatic
    private fun copyOrMoveDir(srcDir: File?, destDir: File?, isMove: Boolean): Boolean {
        if (srcDir == null || destDir == null) return false
        // 如果目标目录在源目录中则返回false，看不懂的话好好想想递归怎么结束
        // srcPath : F:\\MyGithub\\AndroidUtilCode\\utilcode\\src\\test\\res
        // destPath: F:\\MyGithub\\AndroidUtilCode\\utilcode\\src\\test\\res1
        // 为防止以上这种情况出现出现误判，须分别在后面加个路径分隔符
        val srcPath = srcDir.path + File.separator
        val destPath = destDir.path + File.separator
        if (destPath.contains(srcPath)) return false
        // 源文件不存在或者不是目录则返回false
        if (!srcDir.exists() || !srcDir.isDirectory) return false
        // 目标目录不存在返回false
        if (!createOrExistsDir(destDir)) return false
        val files = srcDir.listFiles()
        for (file in files) {
            val oneDestFile = File(destPath + file.name)
            if (file.isFile) {
                // 如果操作失败返回false
                if (!copyOrMoveFile(file, oneDestFile, isMove)) return false
            } else if (file.isDirectory) {
                // 如果操作失败返回false
                if (!copyOrMoveDir(file, oneDestFile, isMove)) return false
            }
        }
        return !isMove || deleteDir(srcDir)
    }

    /**
     * 复制或移动文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     * @param isMove       是否移动
     * @return `true`: 复制或移动成功<br></br>`false`: 复制或移动失败
     */
    @JvmStatic
    private fun copyOrMoveFile(srcFilePath: String, destFilePath: String, isMove: Boolean): Boolean {
        return copyOrMoveFile(getFileByPath(srcFilePath), getFileByPath(destFilePath), isMove)
    }

    /**
     * 复制或移动文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @param isMove   是否移动
     * @return `true`: 复制或移动成功<br></br>`false`: 复制或移动失败
     */
    @JvmStatic
    private fun copyOrMoveFile(srcFile: File?, destFile: File?, isMove: Boolean): Boolean {
        if (srcFile == null || destFile == null) return false
        // 源文件不存在或者不是文件则返回false
        if (!srcFile.exists() || !srcFile.isFile) return false
        // 目标文件存在且是文件则返回false
        if (destFile.exists() && destFile.isFile) return false
        // 目标目录不存在返回false
        if (!createOrExistsDir(destFile.parentFile)) return false
        try {
            return writeFileFromIS(destFile, FileInputStream(srcFile), false) && !(isMove && !deleteFile(srcFile))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return false
        }

    }

    /**
     * 复制目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @return `true`: 复制成功<br></br>`false`: 复制失败
     */
    @JvmStatic
    fun copyDir(srcDirPath: String, destDirPath: String): Boolean {
        val srcFile = getFileByPath(srcDirPath)
        val destFile = getFileByPath(destDirPath)
        if (srcFile==null || destFile ==null) return false
        return copyDir(srcFile, destFile)
    }

    /**
     * 复制目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @return `true`: 复制成功<br></br>`false`: 复制失败
     */
    @JvmStatic
    fun copyDir(srcDir: File, destDir: File): Boolean {

        return copyOrMoveDir(srcDir, destDir, false)
    }

    /**
     * 复制文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     * @return `true`: 复制成功<br></br>`false`: 复制失败
     */
    @JvmStatic
    fun copyFile(srcFilePath: String, destFilePath: String): Boolean {
        val srcFile = getFileByPath(srcFilePath)
        val destFile = getFileByPath(destFilePath)
        if (srcFile==null || destFile ==null) return false
        return copyFile(srcFile, destFile)
    }

    /**
     * 复制文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @return `true`: 复制成功<br></br>`false`: 复制失败
     */
    @JvmStatic
    fun copyFile(srcFile: File, destFile: File): Boolean {
        return copyOrMoveFile(srcFile, destFile, false)
    }

    /**
     * 移动目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @return `true`: 移动成功<br></br>`false`: 移动失败
     */
    @JvmStatic
    fun moveDir(srcDirPath: String, destDirPath: String): Boolean {
        val srcFile = getFileByPath(srcDirPath)
        val destFile = getFileByPath(destDirPath)
        if (srcFile==null || destFile ==null) return false
        return moveDir(srcFile, destFile)
    }

    /**
     * 移动目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @return `true`: 移动成功<br></br>`false`: 移动失败
     */
    @JvmStatic
    fun moveDir(srcDir: File, destDir: File): Boolean {
        return copyOrMoveDir(srcDir, destDir, true)
    }

    /**
     * 移动文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     * @return `true`: 移动成功<br></br>`false`: 移动失败
     */
    @JvmStatic
    fun moveFile(srcFilePath: String, destFilePath: String): Boolean {
        val srcFile = getFileByPath(srcFilePath)
                val destFile = getFileByPath(destFilePath)
        if (srcFile==null || destFile ==null) return false
        return moveFile(srcFile, destFile)
    }

    /**
     * 移动文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @return `true`: 移动成功<br></br>`false`: 移动失败
     */
    @JvmStatic
    fun moveFile(srcFile: File, destFile: File): Boolean {
        return copyOrMoveFile(srcFile, destFile, true)
    }

    /**
     * 删除目录
     *
     * @param dirPath 目录路径
     * @return `true`: 删除成功<br></br>`false`: 删除失败
     */
    @JvmStatic
    fun deleteDir(dirPath: String): Boolean {
        return deleteDir(getFileByPath(dirPath))
    }

    /**
     * 删除目录
     *
     * @param dir 目录
     * @return `true`: 删除成功<br></br>`false`: 删除失败
     */
    @JvmStatic
    fun deleteDir(dir: File?): Boolean {
        if (dir == null) return false
        // 目录不存在返回true
        if (!dir.exists()) return true
        // 不是目录返回false
        if (!dir.isDirectory) return false
        // 现在文件存在且是文件夹
        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (file.isFile) {
                    if (!deleteFile(file)) return false
                } else if (file.isDirectory) {
                    if (!deleteDir(file)) return false
                }
            }
        }
        return dir.delete()
    }

    /**
     * 删除文件
     *
     * @param srcFilePath 文件路径
     * @return `true`: 删除成功<br></br>`false`: 删除失败
     */
    @JvmStatic
    fun deleteFile(srcFilePath: String): Boolean {
        return deleteFile(getFileByPath(srcFilePath))
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return `true`: 删除成功<br></br>`false`: 删除失败
     */
    @JvmStatic
    fun deleteFile(file: File?): Boolean {
        return file != null && (!file.exists() || file.isFile && file.delete())
    }

    /**
     * 删除目录下的所有文件
     *
     * @param dirPath 目录路径
     * @return `true`: 删除成功<br></br>`false`: 删除失败
     */
    @JvmStatic
    fun deleteFilesInDir(dirPath: String): Boolean {
        return deleteFilesInDir(getFileByPath(dirPath))
    }

    /**
     * 删除目录下的所有文件
     *
     * @param dir 目录
     * @return `true`: 删除成功<br></br>`false`: 删除失败
     */
    @JvmStatic
    fun deleteFilesInDir(dir: File?): Boolean {
        if (dir == null) return false
        // 目录不存在返回true
        if (!dir.exists()) return true
        // 不是目录返回false
        if (!dir.isDirectory) return false
        // 现在文件存在且是文件夹
        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (file.isFile) {
                    if (!deleteFile(file)) return false
                } else if (file.isDirectory) {
                    if (!deleteDir(file)) return false
                }
            }
        }
        return true
    }

    /**
     * 获取目录下所有文件
     *
     * @param dirPath     目录路径
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDir(dirPath: String, isRecursive: Boolean): List<File> {
        val file  = getFileByPath(dirPath)
        if (file==null) return emptyList()
        return listFilesInDir(file, isRecursive)
    }

    /**
     * 获取目录下所有文件
     *
     * @param dir         目录
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDir(dir: File, isRecursive: Boolean): List<File> {
        val list = ArrayList<File>()
        if (!isDir(dir)) return list
        if (isRecursive) return listFilesInDir(dir)

        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            Collections.addAll(list, *files)
        }
        return list
    }

    /**
     * 获取目录下所有文件包括子目录
     *
     * @param dirPath 目录路径
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDir(dirPath: String): List<File> {
        val file  = getFileByPath(dirPath)
        if (file==null) return emptyList()
        return listFilesInDir(file)
    }

    /**
     * 获取目录下所有文件包括子目录
     *
     * @param dir 目录
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDir(dir: File): List<File> {
        val list = ArrayList<File>()
        if (!isDir(dir)) return list

        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                list.add(file)
                if (file.isDirectory) {
                    val files = listFilesInDir(file)
                    if (files != null) {
                        list.addAll(files)
                    }
                }
            }
        }
        return list
    }

    /**
     * 获取目录下所有后缀名为suffix的文件
     *
     * 大小写忽略
     *
     * @param dirPath     目录路径
     * @param suffix      后缀名
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDirWithFilter(dirPath: String, suffix: String, isRecursive: Boolean): List<File> {
        val file  = getFileByPath(dirPath)
        if (file==null) return emptyList()
        return listFilesInDirWithFilter(file, suffix, isRecursive)
    }

    /**
     * 获取目录下所有后缀名为suffix的文件
     *
     * 大小写忽略
     *
     * @param dir         目录
     * @param suffix      后缀名
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDirWithFilter(dir: File?, suffix: String, isRecursive: Boolean): List<File> {
        if (isRecursive) return listFilesInDirWithFilter(dir, suffix)
        val list = ArrayList<File>()
        if (dir == null || !isDir(dir)) return list
        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (file.name.toUpperCase().endsWith(suffix.toUpperCase())) {
                    list.add(file)
                }
            }
        }
        return list
    }

    /**
     * 获取目录下所有后缀名为suffix的文件包括子目录
     *
     * 大小写忽略
     *
     * @param dirPath 目录路径
     * @param suffix  后缀名
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDirWithFilter(dirPath: String, suffix: String): List<File> {
        val file  = getFileByPath(dirPath)
        if (file==null) return emptyList()
        return listFilesInDirWithFilter(file, suffix)
    }

    /**
     * 获取目录下所有后缀名为suffix的文件包括子目录
     *
     * 大小写忽略
     *
     * @param dir    目录
     * @param suffix 后缀名
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDirWithFilter(dir: File?, suffix: String): List<File> {
        val list = ArrayList<File>()
        if (dir == null || !isDir(dir)) return list

        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (file.name.toUpperCase().endsWith(suffix.toUpperCase())) {
                    list.add(file)
                }
                if (file.isDirectory) {
                    val files = listFilesInDirWithFilter(file, suffix)
                    if (files != null) {
                        list.addAll(files)
                    }

                }
            }
        }
        return list
    }

    /**
     * 获取目录下所有符合filter的文件
     *
     * @param dirPath     目录路径
     * @param filter      过滤器
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDirWithFilter(dirPath: String, filter: FilenameFilter, isRecursive: Boolean): List<File> {
        val file = getFileByPath(dirPath)
        if (file==null) return emptyList()
        return listFilesInDirWithFilter(file, filter, isRecursive)
    }

    /**
     * 获取目录下所有符合filter的文件
     *
     * @param dir         目录
     * @param filter      过滤器
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDirWithFilter(dir: File?, filter: FilenameFilter, isRecursive: Boolean): List<File> {
        if (isRecursive) return listFilesInDirWithFilter(dir, filter)
        val list = ArrayList<File>()
        if (dir == null || !isDir(dir)) return list

        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (filter.accept(file.parentFile, file.name)) {
                    list.add(file)
                }
            }
        }
        return list
    }

    /**
     * 获取目录下所有符合filter的文件包括子目录
     *
     * @param dirPath 目录路径
     * @param filter  过滤器
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDirWithFilter(dirPath: String, filter: FilenameFilter): List<File> {
        val file = getFileByPath(dirPath)
        if (file == null ) return ArrayList<File>()
        return listFilesInDirWithFilter(file, filter)
    }

    /**
     * 获取目录下所有符合filter的文件包括子目录
     *
     * @param dir    目录
     * @param filter 过滤器
     * @return 文件链表
     */
    @JvmStatic
    fun listFilesInDirWithFilter(dir: File?, filter: FilenameFilter): List<File> {
        val list = ArrayList<File>()
        if (dir == null || !isDir(dir)) return list

        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (filter.accept(file.parentFile, file.name)) {
                    list.add(file)
                }
                if (file.isDirectory) {
                    val files = listFilesInDirWithFilter(file, filter)
                    if (files != null) {
                        list.addAll(files)
                    }

                }
            }
        }
        return list
    }

    /**
     * 获取目录下指定文件名的文件包括子目录
     *
     * 大小写忽略
     *
     * @param dirPath  目录路径
     * @param fileName 文件名
     * @return 文件链表
     */
    @JvmStatic
    fun searchFileInDir(dirPath: String, fileName: String): List<File> {
        val file = getFileByPath(dirPath)
        if (file ==null) return  ArrayList<File>()
        return searchFileInDir(file, fileName)
    }

    /**
     * 获取目录下指定文件名的文件包括子目录
     *
     * 大小写忽略
     *
     * @param dir      目录
     * @param fileName 文件名
     * @return 文件链表
     */
    @JvmStatic
    fun searchFileInDir(dir: File?, fileName: String): List<File> {
        if (dir == null || !isDir(dir)) return ArrayList<File>()
        val list = ArrayList<File>()
        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (file.name.toUpperCase() == fileName.toUpperCase()) {
                    list.add(file)
                }
                if (file.isDirectory) {
                    val files = searchFileInDir(file, fileName)
                    if (files != null) {
                        list.addAll(files)
                    }

                }
            }
        }
        return list
    }

    /**
     * 将输入流写入文件
     *
     * @param filePath 路径
     * @param is       输入流
     * @param append   是否追加在文件末
     * @return `true`: 写入成功<br></br>`false`: 写入失败
     */
    @JvmStatic
    fun writeFileFromIS(filePath: String, inputStream: InputStream, append: Boolean): Boolean {
        return writeFileFromIS(getFileByPath(filePath), inputStream, append)
    }

    /**
     * 将输入流写入文件
     *
     * @param file   文件
     * @param is     输入流
     * @param append 是否追加在文件末
     * @return `true`: 写入成功<br></br>`false`: 写入失败
     */
    @JvmStatic
    fun writeFileFromIS(file: File?, inputStream: InputStream?, append: Boolean): Boolean {
        if (file == null || inputStream == null) return false
        if (!createOrExistsFile(file)) return false
        var os: OutputStream? = null
        try {
            os = BufferedOutputStream(FileOutputStream(file, append))
            val data = ByteArray(1024)
            var len: Int = inputStream.read(data, 0, 1024)
            while (len!= -1) {
                os!!.write(data, 0, len)
                        len = inputStream.read(data, 0, 1024)
            }
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } finally {
            CloseUtils.closeIO(inputStream, os)
        }
    }

    /**
     * 将字符串写入文件
     *
     * @param filePath 文件路径
     * @param content  写入内容
     * @param append   是否追加在文件末
     * @return `true`: 写入成功<br></br>`false`: 写入失败
     */
    @JvmStatic
    fun writeFileFromString(filePath: String, content: String, append: Boolean): Boolean {
        return writeFileFromString(getFileByPath(filePath), content, append)
    }

    /**
     * 将字符串写入文件
     *
     * @param file    文件
     * @param content 写入内容
     * @param append  是否追加在文件末
     * @return `true`: 写入成功<br></br>`false`: 写入失败
     */
    @JvmStatic
    fun writeFileFromString(file: File?, content: String?, append: Boolean): Boolean {
        if (file == null || content == null) return false
        if (!createOrExistsFile(file)) return false
        var bw: BufferedWriter? = null
        try {
            bw = BufferedWriter(FileWriter(file, append))
            bw!!.write(content)
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } finally {
            CloseUtils.closeIO(bw)
        }
    }

    /**
     * 指定编码按行读取文件到链表中
     *
     * @param filePath    文件路径
     * @param charsetName 编码格式
     * @return 文件行链表
     */
    @JvmStatic
    fun readFile2List(filePath: String, charsetName: String): List<String> {
        val file = getFileByPath(filePath)
        if (file ==null ) return ArrayList<String>()
        return readFile2List(file, charsetName)
    }

    /**
     * 指定编码按行读取文件到链表中
     *
     * @param file        文件
     * @param charsetName 编码格式
     * @return 文件行链表
     */
    @JvmStatic
    fun readFile2List(file: File, charsetName: String): List<String> {
        return readFile2List(file, 0, 0x7FFFFFFF, charsetName)
    }

    /**
     * 指定编码按行读取文件到链表中
     *
     * @param filePath    文件路径
     * @param st          需要读取的开始行数
     * @param end         需要读取的结束行数
     * @param charsetName 编码格式
     * @return 包含制定行的list
     */
    @JvmStatic
    fun readFile2List(filePath: String, st: Int, end: Int, charsetName: String): List<String> {
        return readFile2List(getFileByPath(filePath), st, end, charsetName)
    }

    /**
     * 指定编码按行读取文件到链表中
     *
     * @param file        文件
     * @param st          需要读取的开始行数
     * @param end         需要读取的结束行数
     * @param charsetName 编码格式
     * @return 包含从start行到end行的list
     */
    @JvmStatic
    fun readFile2List(file: File?, st: Int, end: Int, charsetName: String): List<String> {
        val list = ArrayList<String>()
        if (file == null) return list
        if (st > end) return list
        var reader: BufferedReader? = null
        try {
            var line: String
            var curLine = 1

            if (StringUtils.isSpace(charsetName)) {
                reader = BufferedReader(FileReader(file))
            } else {
                reader = BufferedReader(InputStreamReader(FileInputStream(file), charsetName))
            }
            line = reader!!.readLine()
            while (line != null) {
                if (curLine > end) break
                if (st <= curLine && curLine <= end) list.add(line)
                ++curLine
                line = reader!!.readLine()
            }
            return list
        } catch (e: IOException) {
            e.printStackTrace()
            return ArrayList<String>()
        } finally {
            CloseUtils.closeIO(reader)
        }
    }

    /**
     * 指定编码按行读取文件到字符串中
     *
     * @param filePath    文件路径
     * @param charsetName 编码格式
     * @return 字符串
     */
    @JvmStatic
    fun readFile2String(filePath: String, charsetName: String): String? {
        return readFile2String(getFileByPath(filePath), charsetName)
    }

    /**
     * 指定编码按行读取文件到字符串中
     *
     * @param file        文件
     * @param charsetName 编码格式
     * @return 字符串
     */
    @JvmStatic
    fun readFile2String(file: File?, charsetName: String): String? {
        if (file == null) return null
        var reader: BufferedReader? = null
        try {
            val sb = StringBuilder()
            if (StringUtils.isSpace(charsetName)) {
                reader = BufferedReader(InputStreamReader(FileInputStream(file)))
            } else {
                reader = BufferedReader(InputStreamReader(FileInputStream(file), charsetName))
            }
            var line: String = reader!!.readLine()
            while (line != null) {
                sb.append(line).append("\r\n")// windows系统换行为\r\n，Linux为\n
                line = reader!!.readLine()
            }
            // 要去除最后的换行符
            return sb.delete(sb.length - 2, sb.length).toString()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        } finally {
            CloseUtils.closeIO(reader)
        }
    }

    /**
     * 读取文件到字符数组中
     *
     * @param filePath 文件路径
     * @return 字符数组
     */
    @JvmStatic
    fun readFile2Bytes(filePath: String): ByteArray? {
        return readFile2Bytes(getFileByPath(filePath))
    }

    /**
     * 读取文件到字符数组中
     *
     * @param file 文件
     * @return 字符数组
     */
    @JvmStatic
    fun readFile2Bytes(file: File?): ByteArray? {
        if (file == null) return null
        try {
            return ConvertUtils.inputStream2Bytes(FileInputStream(file))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return null
        }

    }

    /**
     * 获取文件最后修改的毫秒时间戳
     *
     * @param filePath 文件路径
     * @return 文件最后修改的毫秒时间戳
     */
    @JvmStatic
    fun getFileLastModified(filePath: String): Long {
        return getFileLastModified(getFileByPath(filePath))
    }

    /**
     * 获取文件最后修改的毫秒时间戳
     *
     * @param file 文件
     * @return 文件最后修改的毫秒时间戳
     */
    @JvmStatic
    fun getFileLastModified(file: File?): Long {
        return file?.lastModified() ?: -1
    }

    /**
     * 简单获取文件编码格式
     *
     * @param filePath 文件路径
     * @return 文件编码
     */
    @JvmStatic
    fun getFileCharsetSimple(filePath: String): String {
        val file = getFileByPath(filePath)
        if (file!=null) {
            return getFileCharsetSimple(file)
        } else {
            return ""
        }

    }

    /**
     * 简单获取文件编码格式
     *
     * @param file 文件
     * @return 文件编码
     */
    @JvmStatic
    fun getFileCharsetSimple(file: File): String {
        var p = 0
        var inputStream: InputStream? = null
        try {
            inputStream = BufferedInputStream(FileInputStream(file))
            p = (inputStream!!.read() shl 8) + inputStream.read()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            CloseUtils.closeIO(inputStream)
        }
        when (p) {
            0xefbb -> return "UTF-8"
            0xfffe -> return "Unicode"
            0xfeff -> return "UTF-16BE"
            else -> return "GBK"
        }
    }

    /**
     * 获取文件行数
     *
     * @param filePath 文件路径
     * @return 文件行数
     */
    @JvmStatic
    fun getFileLines(filePath: String): Int {
        return getFileLines(getFileByPath(filePath))
    }

    /**
     * 获取文件行数
     *
     * @param file 文件
     * @return 文件行数
     */
    @JvmStatic
    fun getFileLines(file: File?): Int {
        if (file == null) return 0
        var count = 1
        var inputStream: InputStream? = null
        try {

            inputStream = BufferedInputStream(FileInputStream(file))
            if (inputStream != null) {
                val buffer = ByteArray(1024)
                var read: Int = inputStream.read(buffer, 0, 1024)
                inputStream.use { input ->
                    for (i in 0 until read) {
                        if (buffer[i] == '\n'.toByte()) {
                            ++count
                        }
                    }
                    read = inputStream.read(buffer, 0, 1024)
                }
            }


        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            CloseUtils.closeIO(inputStream)
        }
        return count
    }

    /**
     * 获取目录大小
     *
     * @param dirPath 目录路径
     * @return 文件大小
     */
    @JvmStatic
    fun getDirSize(dirPath: String): String {
        val file = getFileByPath(dirPath)
        if (file == null) return ""
        return getDirSize(file)
    }

    /**
     * 获取目录大小
     *
     * @param dir 目录
     * @return 文件大小
     */
    @JvmStatic
    fun getDirSize(dir: File): String {
        val len = getDirLength(dir)
        return if (len == -1L) "" else ConvertUtils.byte2FitMemorySize(len)
    }

    /**
     * 获取文件大小
     *
     * @param filePath 文件路径
     * @return 文件大小
     */
    @JvmStatic
    fun getFileSize(filePath: String): String {
        return getFileSize(File(filePath))
    }

    /**
     * 获取文件大小
     *
     * @param file 文件
     * @return 文件大小
     */
    @JvmStatic
    fun getFileSize(file: File): String {
        val len = getFileLength(file)
        return if (len == -1L) "" else ConvertUtils.byte2FitMemorySize(len)
    }

    /**
     * 获取目录长度
     *
     * @param dirPath 目录路径
     * @return 文件大小
     */
    @JvmStatic
    fun getDirLength(dirPath: String): Long {
        val file = getFileByPath(dirPath)
        if (file == null) return -1
        return getDirLength(file)
    }

    /**
     * 获取目录长度
     *
     * @param dir 目录
     * @return 文件大小
     */
    @JvmStatic
    fun getDirLength(dir: File): Long {
        if (!isDir(dir)) return -1
        var len: Long = 0
        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (file.isDirectory) {
                    len += getDirLength(file)
                } else {
                    len += file.length()
                }
            }
        }
        return len
    }

    /**
     * 获取文件长度
     *
     * @param filePath 文件路径
     * @return 文件大小
     */
    @JvmStatic
    fun getFileLength(filePath: String): Long {
        val file = getFileByPath(filePath)
        if (file == null) return -1
        return getFileLength(file)
    }

    /**
     * 获取文件长度
     *
     * @param file 文件
     * @return 文件大小
     */
    @JvmStatic
    fun getFileLength(file: File): Long {
        return if (!isFile(file)) -1 else file.length()
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param filePath 文件路径
     * @return 文件的MD5校验码
     */
    @JvmStatic
    fun getFileMD5ToString(filePath: String): String {
        val file = if (StringUtils.isSpace(filePath)) null else File(filePath)
        return getFileMD5ToString(file)
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param filePath 文件路径
     * @return 文件的MD5校验码
     */
    @JvmStatic
    fun getFileMD5(filePath: String): ByteArray? {
        val file = if (StringUtils.isSpace(filePath)) null else File(filePath)
        return getFileMD5(file)
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param file 文件
     * @return 文件的MD5校验码
     */
    @JvmStatic
    fun getFileMD5ToString(file: File?): String {
        return ConvertUtils.bytes2HexString(getFileMD5(file))
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param file 文件
     * @return 文件的MD5校验码
     */
    @JvmStatic
    fun getFileMD5(file: File?): ByteArray? {
        if (file == null) return null
        var dis: DigestInputStream? = null
        try {
            val fis = FileInputStream(file)
            var md = MessageDigest.getInstance("MD5")
            dis = DigestInputStream(fis, md)
            val buffer = ByteArray(1024 * 256)
            while (dis!!.read(buffer) > 0);
            md = dis!!.getMessageDigest()
            return md.digest()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            CloseUtils.closeIO(dis)
        }
        return null
    }

    /**
     * 获取全路径中的最长目录
     *
     * @param file 文件
     * @return filePath最长目录
     */
    @JvmStatic
    fun getDirName(file: File?): String? {
        return if (file == null) null else getDirName(file.path)
    }

    /**
     * 获取全路径中的最长目录
     *
     * @param filePath 文件路径
     * @return filePath最长目录
     */
    @JvmStatic
    fun getDirName(filePath: String): String {
        if (StringUtils.isSpace(filePath)) return filePath
        val lastSep = filePath.lastIndexOf(File.separator)
        return if (lastSep == -1) "" else filePath.substring(0, lastSep + 1)
    }

    /**
     * 获取全路径中的文件名
     *
     * @param file 文件
     * @return 文件名
     */
    @JvmStatic
    fun getFileName(file: File?): String? {
        return if (file == null) null else getFileName(file.path)
    }

    /**
     * 获取全路径中的文件名
     *
     * @param filePath 文件路径
     * @return 文件名
     */
    @JvmStatic
    fun getFileName(filePath: String): String {
        if (StringUtils.isSpace(filePath)) return filePath
        val lastSep = filePath.lastIndexOf(File.separator)
        return if (lastSep == -1) filePath else filePath.substring(lastSep + 1)
    }

    /**
     * 获取全路径中的不带拓展名的文件名
     *
     * @param file 文件
     * @return 不带拓展名的文件名
     */
    @JvmStatic
    fun getFileNameNoExtension(file: File?): String? {
        return if (file == null) null else getFileNameNoExtension(file.path)
    }

    /**
     * 获取全路径中的不带拓展名的文件名
     *
     * @param filePath 文件路径
     * @return 不带拓展名的文件名
     */
    @JvmStatic
    fun getFileNameNoExtension(filePath: String): String {
        if (StringUtils.isSpace(filePath)) return filePath
        val lastPoi = filePath.lastIndexOf('.')
        val lastSep = filePath.lastIndexOf(File.separator)
        if (lastSep == -1) {
            return if (lastPoi == -1) filePath else filePath.substring(0, lastPoi)
        }
        return if (lastPoi == -1 || lastSep > lastPoi) {
            filePath.substring(lastSep + 1)
        } else filePath.substring(lastSep + 1, lastPoi)
    }

    /**
     * 获取全路径中的文件拓展名
     *
     * @param file 文件
     * @return 文件拓展名
     */
    @JvmStatic
    fun getFileExtension(file: File?): String? {
        return if (file == null) null else getFileExtension(file.path)
    }

    /**
     * 获取全路径中的文件拓展名
     *
     * @param filePath 文件路径
     * @return 文件拓展名
     */
    @JvmStatic
    fun getFileExtension(filePath: String): String {
        if (StringUtils.isSpace(filePath)) return filePath
        val lastPoi = filePath.lastIndexOf('.')
        val lastSep = filePath.lastIndexOf(File.separator)
        return if (lastPoi == -1 || lastSep >= lastPoi) "" else filePath.substring(lastPoi + 1)
    }
}