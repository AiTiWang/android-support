package com.i4evercai.android.support.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat

/**
 * Created by Fitz on 2017/12/15 0015.
 */
open class BaseFragmentPagerAdapter : FragmentPagerAdapter {
    private val data: ArrayList<Pair<String, Fragment>>

    companion object {
        private fun createData(fragments: List<Fragment>): ArrayList<Pair<String, Fragment>> {
            val data = ArrayList<Pair<String, Fragment>>()
            for (fragment in fragments) {
                data.add(Pair("", fragment))
            }
            return data
        }

        private fun createDataFromStringRes(context: Context, fragments: List<Pair<Int, Fragment>>): ArrayList<Pair<String, Fragment>> {
            val data = ArrayList<Pair<String, Fragment>>()
            for (pair in fragments) {
                val title = context.getString(pair.first)
                data.add(Pair(title, pair.second))
            }
            return data
        }
    }

    constructor(context: Context, fm: FragmentManager, data: List<Pair<Int, Fragment>>) : this(fm, createDataFromStringRes(context, data))

    constructor(fragments: List<Fragment>, fm: FragmentManager) : this(fm, createData(fragments))

    constructor(fm: FragmentManager, data: ArrayList<Pair<String, Fragment>>) : super(fm) {
        this.data = data
    }


    override fun getItem(position: Int): Fragment {
        return data[position].second
    }

    override fun getCount(): Int = data.size

    override fun getPageTitle(position: Int): CharSequence {
        return data[position].first
    }

}