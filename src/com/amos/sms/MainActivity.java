package com.amos.sms;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et_phone;// 文本输入框中的手机号码
	private EditText et_content;// 文本输入框中的短信内容
	private String tag = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_phone = (EditText) this.findViewById(R.id.et_phone);
		et_content = (EditText) this.findViewById(R.id.et_content);

	}

	public void send_sms(View v) {
		String phone = et_phone.getText().toString();
		String content = et_content.getText().toString();
		if ("".equals(phone) || "".equals(content)) {
			Log.e(tag, "手机号或者短信内容为空!" + " phone:" + phone + "  content:" + content);
			// 提示用户输入手机号码和短信内容
			Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show();
			return;
		} else {
			System.out.println("phone:" + phone + "  content:" + content);
			SmsManager smsManger = SmsManager.getDefault();
			// sentIntent, deliveryIntent
			// 延期的意图,这里sentIntent是发送报告,运营商返回的信息短信的发送报告等;deliveryIntent表示短信的返回报告
			Log.w(tag, "点击发送按钮了");
			// smsManger.sendTextMessage(phone, null, content, null, null);
			for (String message : smsManger.divideMessage(content)) {
				smsManger.sendTextMessage(phone, null, message, null, null);
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
