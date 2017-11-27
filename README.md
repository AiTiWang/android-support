# android-support

[![](https://jitpack.io/v/4evercai/android-support.svg)](https://jitpack.io/#4evercai/android-support)
[![License](https://img.shields.io/badge/license-WTFPL%202.0-blue.svg)](http://www.wtfpl.net/)
[![Blog](https://img.shields.io/badge/site-youquan.pro-9932CC.svg)](http://youquan.pro)

android-support是针对于Android开发封装好一些常用的基类，主要是开发过程中一些常用的工具类。
## 引入
   Gradle
   1. 在项目 build.gradle 添加 JitPack 仓库
   ```kotlin
    allprojects {
        repositories {
            ... 
            maven { url 'https://jitpack.io' }
        }
	}
```
   2. 引入库
   ```kotlin
    // 其中latest.release指代最新版本号，也可以指定明确的版本号，例如1.2.0
    compile 'com.github.4evercai:android-support:latest.release'  
```
## 注意事项
1.App的 Application 需要继承BaseApplication  

## 图片加载
### 初始化
 1.新建一个类实现ImageLoaderStrategy 方法  
 2.调用ImageLoaderManager.instance.init(context: Context,imageLoaderStrategy: ImageLoaderStrategy)初始化
 
### 图片加载
 1.对应布局中ImageView改为引用com.i4evercai.android.support.widget.AppImageView  
 2.通过AppImageView.loadImage(path: String)方法直接加载图片 

## kotlin 扩展类
Context
```kotlin
   Context.dip(float: Float)            // dp转px
```

String
```kotlin
   String?.isEmpty()            // 判断是否为null或者空字符，等同于TextUtils.isEmpty()
   String?.isNotEmpty()         // 与isEmpty()相反
   String.md5()                 // 获取MD5值
   String.sha1()                // 获取sha1值
   String.sha256()              // 获取sha256值
   String.sha512()              // 获取sha512值
   
```

## 常用工具类
 AppUtils  
```kotlin
 AppUtils.getAppName(context: Context)              // 获取程序名
 AppUtils.getVersionName(context: Context)          // 获取版本名
 AppUtils.getVersionCode(context: Context)          // 获取版本号
 AppUtils.isMainProcess(context: Context)           // 判断是否为主进程
 AppUtils.getProcessName(context: Context)          // 获取当前进程名
 AppUtils.getImei(context: Context)                 // 获取IMEI,如果获取失败，则会自动生成UUID返回
```
BitmpUtils
```kotlin
 BitmpUtils.saveBitmap(bm: Bitmap, filePath: String)    // 保存bitmap
 BitmpUtils.saveBitmap(bm: Bitmap, file: File)          // 保存bitmap
 BitmpUtils.compressImage(bitmap: Bitmap, maxSize: Int) // 压缩图片
 BitmpUtils.compressImageFile(context: Context, 
            file: File,  maxSize: Int)                  // 压缩图片
 
```
DensityUtils
```kotlin
 DensityUtils.dp2px(context: Context, dpVal: Float)   // dp转px
 DensityUtils.sp2px(context: Context, dpVal: Float)   // sp转px
 DensityUtils.px2dp(context: Context, dpVal: Float)   // px转dp
 DensityUtils.px2sp(context: Context, dpVal: Float)   // px转sp
 DensityUtils.getWidth(context: Context)              // 获取屏幕宽
 DensityUtils.getHeight(context: Context)             // 获取屏幕高
```
PreferenceUtils
```kotlin
 PreferenceUtils.getString(context: Context, key: String, defaultValue: String)
 PreferenceUtils.getBoolean(context: Context, key: String, defaultValue: Boolen)
 PreferenceUtils.getInt(context: Context, key: String, defaultValue: Int)
 PreferenceUtils.getFloat(context: Context, key: String, defaultValue: Float)
 PreferenceUtils.getLong(context: Context, key: String, defaultValue: Long)
 PreferenceUtils.clearPreference(context: Context, preferencesFileName: String) 
```
# License
               DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
                       Version 2, December 2004
    
    Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>
    
    Everyone is permitted to copy and distribute verbatim or modified
    copies of this license document, and changing it is allowed as long
    as the name is changed.
    
               DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
      TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
    
     0. You just DO WHAT THE FUCK YOU WANT TO.
