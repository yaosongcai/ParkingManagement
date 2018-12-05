package com.tcc.parkingmanagement.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.tcc.parkingmanagement.R;


public class HintView {

	private TextView mTvHint;
	private Dialog mDialog;
	private boolean canClick;

	public HintView(Context context) {
		super();
		canClick = true;
		View v = LayoutInflater.from(context).inflate(R.layout.hint_view, null);
		mDialog = new MyDialog(context,R.style.dialog);
		mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mDialog.setContentView(v);
		mDialog.setCancelable(false);
		mDialog.setCanceledOnTouchOutside(false);
		mTvHint = (TextView)v.findViewById(R.id.tv_print_view);
	}
	class MyDialog extends  Dialog{

		public MyDialog(Context context, int dialog) {
			super(context, dialog);
		}
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if(keyCode == KeyEvent.KEYCODE_BACK && canClick) { //监控/拦截/屏蔽返回键
				mDialog.dismiss();
				return true;
			} else if(keyCode == KeyEvent.KEYCODE_MENU) {
				//监控/拦截菜单键
			} else if(keyCode == KeyEvent.KEYCODE_HOME) {
				//由于Home键为系统键，此处不能捕获，需要重写onAttachedToWindow()
			}
			return super.onKeyDown(keyCode, event);
		}
	}

	public TextView getmTvHint() {
		return mTvHint;
	}

	public void setCanClick(boolean canClick){
		this.canClick =  canClick;
	}

	public HintView setmTvHint(TextView mTvHint) {
		this.mTvHint = mTvHint;
		return this;
	}

	public HintView setCancelable(boolean flag) {
		mDialog.setCancelable(flag);
		return this;
	}

	public HintView setMessage(String msg) {
		mTvHint.setText(msg);
		return this;
	}

	public void show() {

		try{
			mDialog.show();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	public void dismiss(){
		try{
			mDialog.dismiss();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	public void setOnDismissListener(OnDismissListener listener){
		mDialog.setOnDismissListener(listener);
	}

}
