package com.i4evercai.android.support.adapter

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i4evercai.android.support.R
import kotlinx.android.synthetic.main.support_item_empty.view.*

/**
 *
 * @Description: BaseRecyclerHeaderAdapter
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/9/4 14:39
 * @version V1.0
 */
abstract class BaseRecyclerHeaderAdapter<VHH : android.support.v7.widget.RecyclerView.ViewHolder, VH : android.support.v7.widget.RecyclerView.ViewHolder>
    : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    companion object {
        private val S_TYPE_EMPTY = -25555411
        private val S_TYPE_HEADER = -25555412
    }

    private val mContext: Context
    private var mIsShowEmptyView = true
    private var mIsShowHeaderView = false
    private var mEmptyIconRes: Int = R.drawable.support_ic_empty_notice
    private var mEmptyMsg: String = ""

    constructor(context: Context) : this(context, true, true)

    constructor(context: Context, showHeaderView: Boolean, showEmptyView: Boolean) : super() {
        this.mContext = context
        this.mIsShowEmptyView = showEmptyView
        this.mIsShowHeaderView = showHeaderView
        this.mEmptyMsg = context.resources.getString(R.string.support_recycler_empty_msg)
    }

    constructor(context: Context, showHeaderView: Boolean, @DrawableRes emptyIconRes: Int, emptyMsg: String) : super() {
        this.mContext = context
        this.mIsShowHeaderView = showHeaderView
        this.mEmptyIconRes = emptyIconRes
        this.mEmptyMsg = emptyMsg
    }

    constructor(context: Context, showHeaderView: Boolean, @DrawableRes emptyIconRes: Int, @StringRes emptyMsgRes: Int) : super() {
        this.mContext = context
        this.mIsShowHeaderView = showHeaderView
        this.mEmptyIconRes = emptyIconRes
        this.mEmptyMsg = context.getString(emptyMsgRes)
    }

    constructor(context: Context, showHeaderView: Boolean, @StringRes emptyMsgRes: Int) : super() {
        this.mContext = context
        this.mIsShowHeaderView = showHeaderView
        this.mEmptyMsg = context.getString(emptyMsgRes)
    }

    constructor(context: Context, showHeaderView: Boolean, emptyMsg: String) : super() {
        this.mContext = context
        this.mIsShowHeaderView = showHeaderView
        this.mEmptyMsg = emptyMsg
    }

    final override fun getItemCount(): Int {
        if (mIsShowHeaderView) {
            if (getAdapterItemCount() == 0 && mIsShowEmptyView) {
                return 2
            } else {
                return getAdapterItemCount() + 1
            }
        } else {
            if (getAdapterItemCount() == 0 && mIsShowEmptyView) {
                return 1
            } else {
                return getAdapterItemCount()
            }
        }

    }

    final override fun getItemViewType(position: Int): Int {
        if (mIsShowHeaderView) {

            if (position == 0) {
                return S_TYPE_HEADER
            } else if (getAdapterItemCount() == 0 && mIsShowEmptyView) {
                return S_TYPE_EMPTY
            } else {
                return getAdapterItemViewType(position - 1)
            }
        } else {
            if (getAdapterItemCount() == 0 && mIsShowEmptyView) {
                return S_TYPE_EMPTY
            } else {
                return getAdapterItemViewType(position)
            }
        }

    }


    final override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder != null) {
            if (isHeaderViewHolder(holder)) {
                onBindHeaderHolder(holder as VHH)
            } else
                if (isEmptyViewHolder(holder)) {
                    onBindEmptyViewHolder(holder)
                } else {
                    onBindAdapterViewHolder(holder as VH, position)
                }
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (S_TYPE_HEADER == viewType) {
            return onCreateHeaderViewHolder(parent)
        } else
            if (S_TYPE_EMPTY == viewType) {
                return onCreateEmptyViewHolder(parent)
            } else {
                return onCreateAdapterViewHolder(parent, viewType)
            }
    }


    fun onCreateEmptyViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val context = if (parent == null) mContext else parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.support_item_empty, parent, false)
        return EmptyViewHolder(view)
    }

    fun onBindEmptyViewHolder(holder: RecyclerView.ViewHolder) {
        if (holder is EmptyViewHolder) {
            val emptyViewHolder: EmptyViewHolder = holder
            emptyViewHolder.setData(mEmptyIconRes, mEmptyMsg)
        }

    }


    /**
     * 判断是否是EmptyViewHolder
     */
    private fun isEmptyViewHolder(holder: RecyclerView.ViewHolder): Boolean {
        return holder is EmptyViewHolder
    }

    private class EmptyViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(@DrawableRes emptyIconRes: Int, emptyMsg: String) = with(itemView) {

            supportIvEmptyNotice.setImageResource(emptyIconRes)
            supportTvEmptyMsg.text = emptyMsg
        }
    }


    open fun getAdapterItemViewType(position: Int): Int = 0

    abstract fun getAdapterItemCount(): Int

    abstract fun onCreateAdapterViewHolder(parent: ViewGroup?, viewType: Int): VH
    abstract fun onCreateHeaderViewHolder(parent: ViewGroup?): VHH
    abstract fun onBindAdapterViewHolder(holder: VH, position: Int)

    abstract fun isHeaderViewHolder(holder: RecyclerView.ViewHolder): Boolean

    abstract fun onBindHeaderHolder(holder: VHH)
}