package com.amosli.rp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ResultActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		setTitle("计算结果");

		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		int rp = Calculator.calculateRPByName(name);
		TextView tv_result = (TextView) findViewById(R.id.tv_result);
		tv_result.setText(name + ",您今天的人品值为：" + rp+"\r\n"+Calculator.getComment(rp));
		ProgressBar pg = (ProgressBar) findViewById(R.id.pgRP);
		pg.setProgress(rp);
	}

	public void testAgain(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
