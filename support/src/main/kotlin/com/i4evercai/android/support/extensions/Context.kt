package com.i4evercai.android.support.extensions

import android.content.Context
import com.i4evercai.android.support.utils.DensityUtils

fun Context.dip(float: Float) = DensityUtils.dp2px(this,float)