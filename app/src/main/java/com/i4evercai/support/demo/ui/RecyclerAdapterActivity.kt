package com.i4evercai.support.demo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.i4evercai.android.support.base.BaseActivity
import com.i4evercai.support.demo.R
import com.i4evercai.support.demo.adapter.StringAdapter
import kotlinx.android.synthetic.main.activity_recycler_adapter.*

class RecyclerAdapterActivity : BaseActivity() {
    private val data = ArrayList<String>()
    private val adapter by lazy { StringAdapter(activity, data) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_adapter)
        initViews()
        displayHomeAsUpEnabled()
    }

    override fun initViews() {
        rvContent.layoutManager = LinearLayoutManager(activity)
        rvContent.adapter = adapter

        btnAdd.setOnClickListener {
            data.add("第${data.size+1}个")
            adapter.notifyDataSetChanged()
        }
        btnClear.setOnClickListener{
            data.clear()
            adapter.notifyDataSetChanged()
        }
    }
}
