package com.i4evercai.android.support.widget.AppBarLayout

import com.google.android.material.appbar.AppBarLayout


/**
 * Created by Fitz on 2017/12/9 0009.
 */
abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener{
    private var mCurrentState = State.IDLE

     public  enum class State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (appBarLayout!=null){
            if (verticalOffset == 0) {
                if (mCurrentState != State.EXPANDED) {
                    onStateChanged(appBarLayout, State.EXPANDED);
                }
                mCurrentState = State.EXPANDED;
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                if (mCurrentState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, State.COLLAPSED);
                }
                mCurrentState = State.COLLAPSED;
            } else {
                if (mCurrentState != State.IDLE) {
                    onStateChanged(appBarLayout, State.IDLE);
                }
                mCurrentState = State.IDLE;
            }
        }
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout, state: State)
}