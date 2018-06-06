package com.i4evercai.android.support.adapter

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.OnCompositionLoadedListener
import com.i4evercai.android.support.R
import com.i4evercai.android.support.widget.RecyclerView.BaseViewHolder
import kotlinx.android.synthetic.main.support_item_empty.view.*

/**
 *
 * @Description: BaseRecyclerAdapter
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/9/4 14:39
 * @version V1.0
 */
abstract class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    companion object {
        private val S_TYPE_EMPTY = -25555411

        const val EMPTY_NOTICE_STATUS_LOADING = 1
        const val EMPTY_NOTICE_STATUS_NO_DATA = 2

    }

    private val mContext: Context
    private var mIsShowEmptyView = true
    private var mEmptyLottieAnimName: String = "support_empty_loading_lottie.json"
    private var mEmptyMsg: String = ""

    constructor(context: Context) : this(context, true)

    constructor(context: Context, showEmptyView: Boolean) : super() {
        this.mContext = context
        this.mIsShowEmptyView = showEmptyView
        this.mEmptyMsg = context.resources.getString(R.string.support_recycler_empty_loading_msg)
    }

    constructor(context: Context, emptyLottieAnimName: String, emptyMsg: String) : super() {
        this.mContext = context
        this.mEmptyLottieAnimName = emptyLottieAnimName
        this.mEmptyMsg = emptyMsg
    }

    constructor(context: Context, emptyLottieAnimName: String, @StringRes emptyMsgRes: Int) : super() {
        this.mContext = context
        this.mEmptyLottieAnimName = emptyLottieAnimName
        this.mEmptyMsg = context.getString(emptyMsgRes)
    }

    constructor(context: Context, @StringRes emptyMsgRes: Int) : super() {
        this.mContext = context
        this.mEmptyMsg = context.getString(emptyMsgRes)
    }

    constructor(context: Context, emptyMsg: String) : super() {
        this.mContext = context
        this.mEmptyMsg = emptyMsg
    }

    open fun updateEmptyNoticeStatus(noticeStatus:Int) {
        if (noticeStatus == EMPTY_NOTICE_STATUS_LOADING ){
            mEmptyLottieAnimName =  "support_empty_loading_lottie.json"
            mEmptyMsg = mContext.getString(R.string.support_recycler_empty_loading_msg)
        }else{
            mEmptyLottieAnimName =  "support_empty_no_data_lottie.json"
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


    final override fun getItemCount(): Int {
        if (getAdapterItemCount() == 0 && mIsShowEmptyView) {
            return 1
        } else {
            return getAdapterItemCount()
        }
    }

    final override fun getItemViewType(position: Int): Int {
        if (getAdapterItemCount() == 0 && mIsShowEmptyView) {
            return S_TYPE_EMPTY
        } else {
            return getAdapterItemViewType(position)
        }
    }


    final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (isEmptyViewHolder(holder)) {
                onBindEmptyViewHolder(holder)
            } else {
                onBindAdapterViewHolder(holder as VH, position)
            }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (S_TYPE_EMPTY == viewType) {
            return getEmptyViewHolder(parent)
        } else {
            return onCreateAdapterViewHolder(parent, viewType)
        }
    }


    fun getEmptyViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {

        return EmptyViewHolder(mContext, parent)
    }

    fun onBindEmptyViewHolder(holder: RecyclerView.ViewHolder) {
        if (holder is EmptyViewHolder) {
            val emptyViewHolder: EmptyViewHolder = holder
            emptyViewHolder.setData(mEmptyLottieAnimName, mEmptyMsg)
        }

    }

    /**
     * 判断是否是EmptyViewHolder
     */
    fun isEmptyViewHolder(holder: RecyclerView.ViewHolder): Boolean {
        return holder is EmptyViewHolder
    }

    open class EmptyViewHolder  : BaseViewHolder, OnCompositionLoadedListener {
        private val context:Context
        constructor(context: Context, parent: ViewGroup?):super(context, parent, R.layout.support_item_empty){
            this.context = context
        }
        fun setData(emptyLottieAnimName: String, emptyMsg: String) = with(itemView) {
            LottieComposition.Factory.fromAssetFileName(context,emptyLottieAnimName,this@EmptyViewHolder)

          //  supportIvEmptyNotice.setImageResource(emptyIconRes)
            supportTvEmptyMsg.text = emptyMsg
        }

        override fun onCompositionLoaded(composition: LottieComposition?) {
            if (composition!=null){
                with(itemView){
                    supportLottieView.setComposition(composition)
                    supportLottieView.playAnimation()
                }
            }
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            val manager = recyclerView.layoutManager
            if (manager is GridLayoutManager) {
                manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val type = getItemViewType(position)
                        val spanCount = manager.spanCount
                        if (type == S_TYPE_EMPTY) {
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
        if ( holder is EmptyViewHolder) {
            val lp = holder.itemView.layoutParams
            if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams) {
                if (holder is EmptyViewHolder) {
                    lp.isFullSpan = true
                }
            }
        }
        super.onViewAttachedToWindow(holder)
    }

    open fun getGridLayoutManagerSpanSize(position: Int): Int = 1

    open fun getAdapterItemViewType(position: Int): Int = 0

    abstract fun getAdapterItemCount(): Int

    abstract fun onCreateAdapterViewHolder(parent: ViewGroup?, viewType: Int): VH
    abstract fun onBindAdapterViewHolder(holder: VH, position: Int)


}