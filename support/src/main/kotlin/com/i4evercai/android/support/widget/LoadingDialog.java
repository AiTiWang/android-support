package com.i4evercai.android.support.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.i4evercai.android.support.R;


public class LoadingDialog extends DialogFragment {
    private static final String TAG = "loadingDialog";
    private TextView mTitle;
    private String mMessage;
  /*  private ImageView mImageView;*/
    private boolean showing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.support_dialog_loading, container);
        initView(view);
        showing = true;
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        showing = false;
        //((AnimationDrawable)mImageView.getDrawable()).stop();
    }

    private void initView(View v){
        mTitle = (TextView) v.findViewById(R.id.tvMsg);
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

   /* public boolean isShowing(){
        return  showing;
    }*/

    public void show(FragmentManager manager){
        if (!isAdded()){
            try {
                show(manager,TAG);
            }catch (Exception e){

            }
        }
    }
}
