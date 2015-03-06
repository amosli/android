package com.amos.webview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private Button btn;
	private EditText et_url;
	private WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.btn = (Button) findViewById(R.id.btn_navigate);
		this.et_url = (EditText) findViewById(R.id.et_url);
		this.wv = (WebView) findViewById(R.id.wv);
		this.et_url.setText("http://baidu.com");
		wv.setWebViewClient(new HelloWebViewClient());
		wv.getSettings().setJavaScriptEnabled(true);
		
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "¼´½«¿ªÊ¼ä¯ÀÀ£º" + et_url.getText().toString(), Toast.LENGTH_SHORT).show();
				wv.loadUrl(et_url.getText().toString());
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK&&wv.canGoBack()){
			wv.goBack();
			return true;
		}
		return false;
	}
	
	private class HelloWebViewClient extends WebViewClient{

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			wv.loadUrl(url);
			return true;
		}
	}
	
	
}
