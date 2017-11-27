# android-support

[![](https://jitpack.io/v/4evercai/android-support.svg)](https://jitpack.io/#4evercai/android-support)
[![License](https://img.shields.io/badge/license-WTFPL%202.0-blue.svg)](http://www.wtfpl.net/)
[![Blog](https://img.shields.io/badge/site-youquan.pro-9932CC.svg)](http://youquan.pro)

android-support是针对于Android开发封装好一些常用的基类，主要包括通用的Adapter、Activity、Fragment、Dialog等、和一些常用的Util类，只为更简单。

#注意事项
1.App的 Application 需要继承BaseApplication 

## 图片加载
### 初始化
 1.新建一个类实现ImageLoaderStrategy 方法  
 2.调用ImageLoaderManager.instance.init(context: Context,imageLoaderStrategy: ImageLoaderStrategy)初始化
 
### 图片加载
 1.对应布局中ImageView改为引用com.i4evercai.android.support.widget.AppImageView
 2.通过AppImageView.loadImage(path: String)方法直接加载图片
 
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
