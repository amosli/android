package com.amosli.androidassembly;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.err.println("create...");
	}

	@Override
	protected void onResume() {
		super.onResume();
		System.err.println("resume...");
	}

	@Override
	protected void onStart() {
		super.onStart();
		System.err.println("start...");
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.err.println("stop...");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.err.println("destroy...");
	}

}
