package com.i4evercai.android.support.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Fitz on 2017/12/3.
 */
object ViewUtils {

    fun getView(context: Context, parent: ViewGroup?, layoutId: Int, attachToRoot: Boolean): View {
        return LayoutInflater.from(if (parent == null) context else parent.context).inflate(layoutId, parent, attachToRoot)
    }

    fun getView(context: Context, parent: ViewGroup?, layoutId: Int): View {
        return getView(context,parent,layoutId,false)
    }
}