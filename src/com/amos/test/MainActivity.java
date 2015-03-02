package com.amos.test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int CHANGE_UI = 1;
	protected static final int ERROR = 0;
	ImageView iv;
	EditText et;
	Button btn;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case CHANGE_UI:
				iv.setImageBitmap((Bitmap) msg.obj);
				break;
			case ERROR:
				Toast.makeText(MainActivity.this,(CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
			default:
				break;
			}
		}
	};
	public static String urlMatcher = "^((http|ftp|https):\\/\\/)?[\\w-_.]+(\\/[\\w-_]+)*\\/?.*";
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			String path = et.getText().toString().trim();
			if(Pattern.compile(urlMatcher).matcher(path.toLowerCase()).matches()){
				Message msg = new Message();
				msg.what = ERROR;
				msg.obj = "url填写正确!";
				handler.sendMessage(msg);
			}else{
				Message msg = new Message();
				msg.what = ERROR;
				msg.obj = "url填写错误!";
				handler.sendMessage(msg);
			}
			try {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5 * 1000);
				conn.setReadTimeout(10 * 1000);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.4 Safari/537.36");
				conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
				int code = conn.getResponseCode();
				if (code == 200) {
					InputStream inputStream = conn.getInputStream();
					Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
					Message msg = new Message();
					msg.what = CHANGE_UI;
					msg.obj = bitmap;
					handler.sendMessage(msg);
				} else {
					Message msg = new Message();
					msg.what = ERROR;
					msg.obj = "获取图片失败，状态码不是200,code是：" + code;
					handler.sendMessage(msg);
				}

			} catch (Exception e) {
				Message msg = new Message();
				msg.what = ERROR;
				msg.obj = "获取图片失败，e是：" + e;
				handler.sendMessage(msg);
				e.printStackTrace();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv_pic);
		et = (EditText) findViewById(R.id.et_url);
		btn = (Button) findViewById(R.id.btn_show);
	}

	public void showPic(View v) {
		new Thread(runnable).start();
	}
}
