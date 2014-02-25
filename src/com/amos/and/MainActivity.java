package com.amos.and;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/** 
* @ClassName: MainActivity 
* @Description: android开发实例，自定义实现电话拨号器
* @author: amosli
* @email:amosli@infomorrow.com
* @date 2014年2月25日 下午6:53:29  
*/
public class MainActivity extends Activity implements OnClickListener{
	public static String tag = "MainActivity";
	//以内部成员变量的方式定义输入框,在加载主界面的时候就把此变量加载到内存中去，避免多次加载
	private EditText mEditText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mEditText = (EditText) MainActivity.this.findViewById(R.id.et_dial);
		//得到了activity界面上的button的引用
		Button button = (Button) this.findViewById(R.id.bt_dial);
		/*
		 button.setOnClickListener(new OnClickListener() {
			//方法二，使用匿名内部类的方式注册一个onClick监听事件
			@Override
			public void onClick(View arg0) {
				String phonenum = mEditText.getText().toString();
				Log.i(tag, phonenum);// 输出手机号码
				Log.e(tag, "error");
				Log.d(tag, "debug");
				Log.v(tag, "verbose");
				Log.w(tag, "warn");
				System.out.println("assert:"+Log.ASSERT);
				Intent intent = new Intent();// 意图，代表要执行动作的一个意图
				// 拨打动作,110代表的是一个data
				intent.setAction(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:"+phonenum));
				startActivity(intent);
			}
		});*/
		
		//方法三：使用全局定义的switch进行事件的监听
//		button.setOnClickListener(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//方法三：使用全局定义的switch进行事件的监听,企业中一般常用此方法
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_dial:
			String phonenum = mEditText.getText().toString();
			Log.i(tag, phonenum);// 输出手机号码
			Log.e(tag, "error");
			Log.w(tag, "这是方法三");
			Intent intent = new Intent();// 意图，代表要执行动作的一个意图
			// 拨打动作,110代表的是一个data
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" + phonenum));
			startActivity(intent);
			break;
		}
		;
	}
	public void dial(View v){
		String phonenum = mEditText.getText().toString();
		Log.i(tag, phonenum);// 输出手机号码
		Log.e(tag, "error");
		Log.d(tag, "debug");
		Log.v(tag, "verbose");
		Log.w(tag, "warn");
		System.out.println("assert:"+Log.ASSERT);
		Intent intent = new Intent();// 意图，代表要执行动作的一个意图
		// 拨打动作,110代表的是一个data
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+phonenum));
		startActivity(intent);
	}
	public void info(View v){
		
		Log.i(tag, "提示信息被点击了一次！")	;
	}
	//方法一,自定义实现OnclickListener的内部类
	private class MyButtonOnClickListner implements OnClickListener {
		@Override
		public void onClick(View v) {
			String phonenum = mEditText.getText().toString();
			Log.i(tag, phonenum);// 输出手机号码
			Log.e(tag, "error");
			Log.d(tag, "debug");
			Log.v(tag, "verbose");
			Log.w(tag, "warn");
			System.out.println("assert:"+Log.ASSERT);
			Intent intent = new Intent();// 意图，代表要执行动作的一个意图
			// 拨打动作,110代表的是一个data
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+phonenum));
			startActivity(intent);
		}
	}
}
