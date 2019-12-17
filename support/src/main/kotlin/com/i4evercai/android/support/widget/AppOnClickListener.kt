package com.i4evercai.android.support.widget

import android.view.View
import androidx.annotation.NonNull
import com.i4evercai.android.support.utils.FastClickUtils

interface AppOnClickListener : View.OnClickListener {

    open fun onViewClick(@NonNull v: View)


    override fun onClick(v: View?) {
        if (v != null && !FastClickUtils.isFastClick()) {
            onViewClick(v)
        }
    }
}