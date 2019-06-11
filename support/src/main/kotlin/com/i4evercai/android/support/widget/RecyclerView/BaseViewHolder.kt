package com.i4evercai.android.support.widget.RecyclerView

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.i4evercai.android.support.utils.ViewUtils

/**
 * Created by Fitz on 2017/12/3.
 */
abstract class BaseViewHolder : RecyclerView.ViewHolder{

    constructor(context: Context, parent: ViewGroup?, @LayoutRes layoutId:Int)
        :super(ViewUtils.getView(context,parent,layoutId,false))
}