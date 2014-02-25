package com.amos.sms;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText et_phone;//文本输入框中的手机号码
	private EditText et_content;//文本输入框中的短信内容
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_phone=(EditText) this.findViewById(R.id.et_phone);
		et_content=(EditText) this.findViewById(R.id.et_content);
		
	
	
	}

	public void send(View v){
		String phone = et_phone.getText().toString();
		String content = et_content.getText().toString();
		
		if("".equals(phone)||"".equals(content)){
			//提示用户
			
		}
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
