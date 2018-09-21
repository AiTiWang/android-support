package com.i4evercai.android.support.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.i4evercai.android.support.R;


public class LoadingDialog extends AppCompatDialog {
    private static final String TAG = "loadingDialog";
    private TextView mTitle;
    private String mMessage;
    /*  private ImageView mImageView;*/
    private boolean showing;
    private Context context;
    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_dialog_loading);
        initView();
    }



    private void initView(){
        mTitle = (TextView) findViewById(R.id.tvMsg);
        /*  mImageView = (ImageView) v.findViewById(R.id.iv_image);*/
        if(mMessage!=null)
            setMessage(mMessage);

        // ((AnimationDrawable)mImageView.getDrawable()).start();
    }

    public void setMessage(String message){
        mMessage = message;
        if(mTitle!=null&&!TextUtils.isEmpty(message))
            mTitle.setText(message);
    }
}
