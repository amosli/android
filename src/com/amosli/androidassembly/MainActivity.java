package com.amosli.androidassembly;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.WindowManager;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.err.println("create...");
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
		WindowManager wm  = getWindowManager();
		Display display = wm.getDefaultDisplay();
		if(display.getWidth()>display.getHeight()){
			Fragment f1 = new Fragment1();
			beginTransaction.replace(android.R.id.content, f1);
		}else{
			Fragment f2 = new Fragment2();
			beginTransaction.replace(android.R.id.content, f2);
		}
		beginTransaction.commit();
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
