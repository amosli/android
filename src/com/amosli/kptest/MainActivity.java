package com.amosli.kptest;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ProgressBar progressBar = null;
	TextView tv1 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar.setIndeterminate(false);
		progressBar.setMax(Const.DATASIZE);
		
		tv1 = (TextView) findViewById(R.id.tv1);
		((Button) findViewById(R.id.btnInsertSMS)).setOnClickListener(new BtnListener());
		((Button) findViewById(R.id.btnInsertContact)).setOnClickListener(new BtnListener());
		((Button) findViewById(R.id.btnInsertCallLog)).setOnClickListener(new BtnListener());
		((Button) findViewById(R.id.btnDeleteAll)).setOnClickListener(new BtnListener());

	}

	public class BtnListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			try {
				ProgressBarAsyncTask progressBarAsyncTask = null;
				ContentResolver contentResolver = getContentResolver();
				// Class<?> insertData = null;
				// Method method = null;
				boolean result = false;
				switch (v.getId()) {
				case R.id.btnInsertContact:
					((Button)findViewById(R.id.btnDeleteAll)).setEnabled(false);
					// insertData =
					// Class.forName("com.amosli.kptest.InsertContact");
					progressBarAsyncTask = new ProgressBarAsyncTask(tv1, progressBar, "contact");

					break;
				case R.id.btnInsertSMS:
					((Button)findViewById(R.id.btnInsertSMS)).setEnabled(false);
					// insertData =
					// Class.forName("com.amosli.kptest.InsertSMS");
					progressBarAsyncTask = new ProgressBarAsyncTask(tv1, progressBar, "sms");

					break;
				case R.id.btnInsertCallLog:
					((Button)findViewById(R.id.btnInsertCallLog)).setEnabled(false);
					// insertData =
					// Class.forName("com.amosli.kptest.InsertCallLog");
					progressBarAsyncTask = new ProgressBarAsyncTask(tv1, progressBar, "calllog");

					break;
				case R.id.btnDeleteAll:
					try {
						((Button)findViewById(R.id.btnDeleteAll)).setEnabled(false);

						IDataDao dao = new SMSDao();
						dao.DeleteFromPhone(contentResolver);
						Toast.makeText(getApplicationContext(), "删除sms成功!", Toast.LENGTH_LONG).show();

						dao = new ContactDao();
						dao.DeleteFromPhone(contentResolver);

						Toast.makeText(getApplicationContext(), "删除contact成功!", Toast.LENGTH_LONG).show();

						dao = new CallLogDao();
						dao.DeleteFromPhone(contentResolver);

						Toast.makeText(getApplicationContext(), "删除calls成功!", Toast.LENGTH_LONG).show();

					} catch (Exception e) {

					}
					break;
				default:
					break;
				}

				// if (insertData != null) {
				// method = insertData.getMethod("InsertIntoPhone",
				// ContentResolver.class, ProgressBar.class);
				// result = (Boolean) method.invoke(insertData.newInstance(),
				// contentResolver, progressBar);
				// }

				progressBarAsyncTask.execute(contentResolver);
				Toast.makeText(getApplicationContext(), "写入结果:" + (result ? "sucess" : "fail"), Toast.LENGTH_LONG).show();

			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "写入过程中产生异常!", Toast.LENGTH_LONG).show();
			}

		}
	}

}
