package com.amos.crackme;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private EditText et_userName;
	private EditText et_sn;
	private Button btn_reg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_userName = (EditText) findViewById(R.id.et_userName);
		et_sn = (EditText) findViewById(R.id.et_sn);
		btn_reg = (Button) findViewById(R.id.btn_register);
		
		btn_reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v == btn_reg){
					Toast.makeText(MainActivity.this, "ÕýÔÚ×¢²áÖÐ¡£¡£¡£¡£", Toast.LENGTH_SHORT).show();
					if(checkSN(et_userName.getText().toString(),et_sn.getText().toString())){
						Toast.makeText(MainActivity.this, "×¢²á³É¹¦£¡", Toast.LENGTH_SHORT).show();
					}else{
						setTitle(R.string.unsuccessed);
//						Toast.makeText(MainActivity.this, "×¢²áÂë´íÎó£¡", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		
	}
	
	private boolean checkSN(String userName,String sn){
		if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(sn)||sn.length()!=16)
			return false;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.reset();
			digest.update(userName.getBytes());
			
			byte[] bytes= digest.digest();
			String hexstr= toHexString(bytes,"");
			StringBuilder sb =new StringBuilder();
			for(int i=0;i<hexstr.length();i+=2){
				sb.append(hexstr.charAt(i));
			}
			
			String userSN = sb.toString();
			Log.e("MainActivity.class", "sn:"+userSN);
			if(!userSN.equalsIgnoreCase(sn)){
				return false;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private static String toHexString(byte[] bytes, String separator) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if(hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex).append(separator);
        }
        return hexString.toString();
    }
}
