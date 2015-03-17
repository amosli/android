package com.amosli.rp;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		Random rnd = new Random();
		int rp = rnd.nextInt(101);
		TextView tv_result = (TextView) findViewById(R.id.tv_result);
		tv_result.setText(name + ",您的人品值为：" + rp);
		ProgressBar pg = (ProgressBar) findViewById(R.id.pgRP);
		pg.setProgress(rp);
	}

	public void testAgain(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void exitApp(View v) {
		Toast.makeText(this, "正在退出。。。", Toast.LENGTH_LONG).show();
		android.os.Process.killProcess(android.os.Process.myPid());// 获取PID
		System.exit(0); // 常规java、c#的标准退出法，返回值为0代表正常退出
	}
}
