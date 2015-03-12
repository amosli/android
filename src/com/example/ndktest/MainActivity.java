package com.example.ndktest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.e("ndktest.class", GetInt.getint() + "");
		Log.e("ndktest.class", GetString.getstr());
		GetString getString = new GetString();
		Log.e("ndktest.class", getString.add(1, 3) + "");
		Log.e("ndktest.class", getString.getStr());

		System.out.println("getStr >>>>>>>>>" + getString.getStr());
		System.out.println("getWords >>>>>>>>> " + GetString.getWords());

		
		Toast.makeText(this, GetString.getstr() + GetInt.getint(),
				Toast.LENGTH_LONG).show();
		
		
		
		TextView tv = new TextView(this);
		tv.setText(GetString.getstr());
		setContentView(tv);
		
	}

	static {
		System.loadLibrary("NDKTEST");
	}
}
