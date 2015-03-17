package com.amosli.rp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private EditText et_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_name = (EditText) findViewById(R.id.et_name);
	}

	public void btnCalc(View v) {
		if (TextUtils.isEmpty(et_name.getText().toString().trim())) {
			Toast.makeText(this, "名字不能为空", Toast.LENGTH_LONG).show();
			return;
		}
		Intent intent = new Intent(this, ResultActivity.class);
		intent.putExtra("name", et_name.getText().toString().trim());
		startActivity(intent);
	}

}
