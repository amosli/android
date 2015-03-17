package com.amosli.rp;

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
		setTitle("������");

		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		int rp = Calculator.calculateRPByName(name);
		TextView tv_result = (TextView) findViewById(R.id.tv_result);
		tv_result.setText(name + ",������ƷֵΪ��" + rp);
		ProgressBar pg = (ProgressBar) findViewById(R.id.pgRP);
		pg.setProgress(rp);
	}

	public void testAgain(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void exitApp(View v) {
		Toast.makeText(this, "�����˳�������", Toast.LENGTH_SHORT).show();
		android.os.Process.killProcess(android.os.Process.myPid());// ��ȡPID
		// System.exit(0); // ����java��c#�ı�׼�˳���������ֵΪ0���������˳�
		Intent home = new Intent(Intent.ACTION_MAIN);
		home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		home.addCategory(Intent.CATEGORY_HOME);
		startActivity(home);
		Toast.makeText(this, "�˳��ɹ�������", Toast.LENGTH_SHORT).show();
	}
}
