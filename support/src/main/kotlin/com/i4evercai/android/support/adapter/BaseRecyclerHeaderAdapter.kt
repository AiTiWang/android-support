package com.i4evercai.android.support.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.i4evercai.android.support.R

/**
 *
 * @Description: BaseRecyclerHeaderAdapter
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/9/4 14:39
 * @version V1.0
 */
abstract class BaseRecyclerHeaderAdapter<VHH : RecyclerView.ViewHolder, VH : RecyclerView.ViewHolder>
    : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    companion object {
        private val S_TYPE_EMPTY = -25555411
        private val S_TYPE_HEADER = -25555412
    }

    private var mIsShowHeaderView = false
    protected val mContext: Context
    protected var mIsShowEmptyView = true
    protected var mLoadingLottieAnimName: String = "support_empty_loading_lottie.json"
    protected var mLoadingMsg: String = ""
    protected var mLoadingImageViewResId: Int = 0
    protected var mEmptyLottieAnimName: String = "support_empty_no_data_lottie.json"
    protected var mEmptyMsg: String = ""
    protected var mEmptyImageViewResId: Int = 0
    protected var mEmptyStatus: Int = BaseRecyclerAdapter.EMPTY_NOTICE_STATUS_NO_DATA

    constructor(context: Context) : this(context, true, true)

    constructor(context: Context, showHeaderView: Boolean, showEmptyView: Boolean) : super() {
        this.mContext = context
        this.mIsShowEmptyView = showEmptyView
        this.mIsShowHeaderView = showHeaderView
        this.mEmptyMsg = context.resources.getString(R.string.support_recycler_empty_loading_msg)
    }

    constructor(context: Context, showHeaderView: Boolean, emptyLottieAnimName: String, emptyMsg: String) : super() {
        this.mContext = context
        this.mIsShowHeaderView = showHeaderView
        this.mEmptyLottieAnimName = emptyLottieAnimName
        this.mEmptyMsg = emptyMsg
    }
    constructor(context: Context, showHeaderView: Boolean, @DrawableRes mEmptyImageViewResId: Int, @StringRes emptyMsgRes: Int) : super() {
        this.mContext = context
        this.mIsShowHeaderView = showHeaderView
        this.mEmptyImageViewResId = mEmptyImageViewResId
        this.mEmptyMsg = context.getString(emptyMsgRes)
    }
    constructor(context: Context, showHeaderView: Boolean, emptyLottieAnimName: String, @StringRes emptyMsgRes: Int) : super() {
        this.mContext = context
        this.mIsShowHeaderView = showHeaderView
        this.mEmptyLottieAnimName = emptyLottieAnimName
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


    final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder != null) {
            if (isHeaderViewHolder(holder)) {
                onBindHeaderHolder(holder as VHH)
            } else
                if (isEmptyViewHolder(holder)) {
                    onBindEmptyViewHolder(holder)
                } else {
                    var position = position
                    if (mIsShowHeaderView) {
                        position = position - 1
                    }
                    onBindAdapterViewHolder(holder as VH, position)
                }
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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
        return BaseRecyclerAdapter.EmptyViewHolder(mContext, parent)
    }

    fun onBindEmptyViewHolder(holder: RecyclerView.ViewHolder) {
        if (holder is BaseRecyclerAdapter.EmptyViewHolder) {
            val emptyViewHolder: BaseRecyclerAdapter.EmptyViewHolder = holder
            if (mEmptyStatus == BaseRecyclerAdapter.EMPTY_NOTICE_STATUS_LOADING){
                emptyViewHolder.setData(mLoadingLottieAnimName, mLoadingImageViewResId,mLoadingMsg)
            }else{
                emptyViewHolder.setData(mEmptyLottieAnimName, mEmptyImageViewResId,mEmptyMsg)
            }
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        val manager = recyclerView.layoutManager

        if (manager != null && manager is GridLayoutManager) {
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val type = getItemViewType(position)
                    val spanCount = manager.spanCount
                    if (type == S_TYPE_EMPTY) {
                        return spanCount
                    } else if (type == S_TYPE_HEADER) {
                        return spanCount
                    } else {
                        return getGridLayoutManagerSpanSize(position)
                    }
                }
            }
        }
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        if (holder is BaseRecyclerAdapter.EmptyViewHolder) {
            val lp = holder.itemView.layoutParams
            if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams) {
                //  if (holder is BaseRecyclerAdapter.EmptyViewHolder) {
                lp.isFullSpan = true
                //   }
            }
        }
        super.onViewAttachedToWindow(holder)
    }

    open fun updateEmptyNoticeStatus(noticeStatus: Int) {
        if (noticeStatus == BaseRecyclerAdapter.EMPTY_NOTICE_STATUS_LOADING) {
            mEmptyLottieAnimName = "support_empty_loading_lottie.json"
            mEmptyMsg = mContext.getString(R.string.support_recycler_empty_loading_msg)
        } else {
            mEmptyLottieAnimName = "support_empty_no_data_lottie.json"
            mEmptyMsg = mContext.getString(R.string.support_recycler_empty_no_data_msg)
        }

        if (getAdapterItemCount() == 0 && mIsShowEmptyView) {
            notifyDataSetChanged()
        }
    }

    open fun updateEmptyViewData(emptyLottieAnimName: String, @StringRes emptyMsgRes: Int) {
        updateEmptyViewData(emptyLottieAnimName, mContext.getString(emptyMsgRes))
    }

    open fun updateEmptyViewData(emptyLottieAnimName: String, emptyMsg: String) {
        this.mEmptyLottieAnimName = emptyLottieAnimName
        this.mEmptyMsg = emptyMsg

        if (getAdapterItemCount() == 0 && mIsShowEmptyView) {
            notifyDataSetChanged()
        }
    }

    /**
     * 判断是否是EmptyViewHolder
     */
    private fun isEmptyViewHolder(holder: RecyclerView.ViewHolder): Boolean {
        return holder is BaseRecyclerAdapter.EmptyViewHolder
    }


    open fun getGridLayoutManagerSpanSize(position: Int): Int = 1

    open fun getAdapterItemViewType(position: Int): Int = 0

    abstract fun getAdapterItemCount(): Int

    abstract fun onCreateAdapterViewHolder(parent: ViewGroup?, viewType: Int): VH
    abstract fun onCreateHeaderViewHolder(parent: ViewGroup?): VHH
    abstract fun onBindAdapterViewHolder(holder: VH, position: Int)

    abstract fun isHeaderViewHolder(holder: RecyclerView.ViewHolder): Boolean

    abstract fun onBindHeaderHolder(holder: VHH)
}