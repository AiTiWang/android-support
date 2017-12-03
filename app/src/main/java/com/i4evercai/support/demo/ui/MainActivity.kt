package com.i4evercai.support.demo.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.i4evercai.android.support.base.BaseActivity
import com.i4evercai.support.demo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun initViews() {
        btnLoadingDialog.setOnClickListener(onClickListener)
        btnRvAdpter.setOnClickListener(onClickListener)
    }

    override fun onClick(v: View?) {
        when (v) {
            btnLoadingDialog -> {
                showProgressDialog("加载中")
                btnLoadingDialog.postDelayed({
                    dismissProgressDialog()
                }, 5000)
            }
            btnRvAdpter-> startActivity(Intent(activity,RecyclerAdapterActivity::class.java))

        }
    }

}
