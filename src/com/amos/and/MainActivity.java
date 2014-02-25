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
* @Description: android����ʵ�����Զ���ʵ�ֵ绰������
* @author: amosli
* @email:amosli@infomorrow.com
* @date 2014��2��25�� ����6:53:29  
*/
public class MainActivity extends Activity implements OnClickListener{
	public static String tag = "MainActivity";
	//���ڲ���Ա�����ķ�ʽ���������,�ڼ����������ʱ��ͰѴ˱������ص��ڴ���ȥ�������μ���
	private EditText mEditText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mEditText = (EditText) MainActivity.this.findViewById(R.id.et_dial);
		//�õ���activity�����ϵ�button������
		Button button = (Button) this.findViewById(R.id.bt_dial);
		/*
		 button.setOnClickListener(new OnClickListener() {
			//��������ʹ�������ڲ���ķ�ʽע��һ��onClick�����¼�
			@Override
			public void onClick(View arg0) {
				String phonenum = mEditText.getText().toString();
				Log.i(tag, phonenum);// ����ֻ�����
				Log.e(tag, "error");
				Log.d(tag, "debug");
				Log.v(tag, "verbose");
				Log.w(tag, "warn");
				System.out.println("assert:"+Log.ASSERT);
				Intent intent = new Intent();// ��ͼ������Ҫִ�ж�����һ����ͼ
				// ������,110�������һ��data
				intent.setAction(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:"+phonenum));
				startActivity(intent);
			}
		});*/
		
		//��������ʹ��ȫ�ֶ����switch�����¼��ļ���
//		button.setOnClickListener(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//��������ʹ��ȫ�ֶ����switch�����¼��ļ���,��ҵ��һ�㳣�ô˷���
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_dial:
			String phonenum = mEditText.getText().toString();
			Log.i(tag, phonenum);// ����ֻ�����
			Log.e(tag, "error");
			Log.w(tag, "���Ƿ�����");
			Intent intent = new Intent();// ��ͼ������Ҫִ�ж�����һ����ͼ
			// ������,110�������һ��data
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" + phonenum));
			startActivity(intent);
			break;
		}
		;
	}
	public void dial(View v){
		String phonenum = mEditText.getText().toString();
		Log.i(tag, phonenum);// ����ֻ�����
		Log.e(tag, "error");
		Log.d(tag, "debug");
		Log.v(tag, "verbose");
		Log.w(tag, "warn");
		System.out.println("assert:"+Log.ASSERT);
		Intent intent = new Intent();// ��ͼ������Ҫִ�ж�����һ����ͼ
		// ������,110�������һ��data
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+phonenum));
		startActivity(intent);
	}
	public void info(View v){
		
		Log.i(tag, "��ʾ��Ϣ�������һ�Σ�")	;
	}
	//����һ,�Զ���ʵ��OnclickListener���ڲ���
	private class MyButtonOnClickListner implements OnClickListener {
		@Override
		public void onClick(View v) {
			String phonenum = mEditText.getText().toString();
			Log.i(tag, phonenum);// ����ֻ�����
			Log.e(tag, "error");
			Log.d(tag, "debug");
			Log.v(tag, "verbose");
			Log.w(tag, "warn");
			System.out.println("assert:"+Log.ASSERT);
			Intent intent = new Intent();// ��ͼ������Ҫִ�ж�����һ����ͼ
			// ������,110�������һ��data
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+phonenum));
			startActivity(intent);
		}
	}
}
