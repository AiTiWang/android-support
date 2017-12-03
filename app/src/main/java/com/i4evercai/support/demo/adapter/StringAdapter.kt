package com.i4evercai.support.demo.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.ViewGroup
import com.i4evercai.android.support.adapter.BaseRecyclerAdapter
import com.i4evercai.android.support.widget.RecyclerView.BaseViewHolder
import com.i4evercai.support.demo.R
import kotlinx.android.synthetic.main.item_string.view.*

/**
 * Created by Fitz on 2017/12/3.
 */
class StringAdapter:BaseRecyclerAdapter<StringAdapter.StringViewHolder> {
    private val context: Context
    private val  data:ArrayList<String>
    constructor(context: Context,data:ArrayList<String>):super(context){
        this.data = data
        this.context = context
    }
    override fun getAdapterItemCount(): Int= data.size

    override fun onCreateAdapterViewHolder(parent: ViewGroup?, viewType: Int): StringViewHolder {
        return StringViewHolder(context,parent)
    }

    override fun onBindAdapterViewHolder(holder: StringViewHolder, position: Int) {
        holder.setData(data[position])
    }

    class StringViewHolder :BaseViewHolder{
        constructor(context: Context, parent: ViewGroup?):super(context,parent, R.layout.item_string)

        fun setData(text:String){
            with(itemView){
                tvString.text = text
            }
        }
    }
}