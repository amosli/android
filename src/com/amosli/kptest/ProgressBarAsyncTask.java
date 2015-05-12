package com.amosli.kptest;

import java.util.ArrayList;
import java.util.Random;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.provider.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarAsyncTask extends AsyncTask<ContentResolver, Integer, String> {

	private ProgressBar progressBar;
	private TextView textView;
	private String type;


	public ProgressBarAsyncTask(TextView textView,ProgressBar progressBar, String type) {
		this.type = type;
		this.progressBar = progressBar;
		this.textView = textView;
	}

	/**
	 * 这里的Integer参数对应AsyncTask中的第一个参数 这里的String返回值对应AsyncTask的第三个参数
	 * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改
	 * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作
	 */
	@Override
	protected String doInBackground(ContentResolver... params) {
		try {
			IDataDao dataOperator = null;
			ContentResolver contentResolver = params[0];
			switch (type) {
			case "sms":
				dataOperator = new SMSDao();
				dataOperator.DeleteFromPhone(contentResolver);
				insertSMS(contentResolver);
				break;

			case "contact":
				dataOperator = new ContactDao();
				dataOperator.DeleteFromPhone(contentResolver);
				insertContact(contentResolver);
				break;
			case "calllog":
				dataOperator = new CallLogDao();
				dataOperator.DeleteFromPhone(contentResolver);
				insertCallLog(contentResolver);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	private void insertSMS(ContentResolver contentResolver) {
		for (int i = 0; i < Const.DATASIZE; i++) {
			ContentValues values = new ContentValues();
			values.put("address", "13167081000");
			values.put("date", System.currentTimeMillis() - 1000 * (new Random().nextInt(10)));
			values.put("read", 1);
			values.put("status", -1);
			values.put("type", 1);
			values.put("body", "测试插入一条短信信息" + i);
			contentResolver.insert(Const.SMSURI, values);
			publishProgress(i);
		}
	}

	private void insertContact(ContentResolver contentResolver) throws Exception {
		for (int i = 0; i < Const.DATASIZE; i++) {

			ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
			int size = ops.size();
			ops.add(ContentProviderOperation.newInsert(Const.CONTACTURI).withValue(RawContacts.ACCOUNT_TYPE, null)
					.withValue(RawContacts.ACCOUNT_NAME, null).build());

			ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference(Data.RAW_CONTACT_ID, size)
					.withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE).withValue(StructuredName.DISPLAY_NAME, "张三" + i).build());

			ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValue(Data.RAW_CONTACT_ID, size)
					.withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE).withValue(Phone.NUMBER, "13855663344").withValue(Phone.TYPE, "手机号码").build());

			ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference(Data.RAW_CONTACT_ID, size)
					.withValue(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE).withValue(Email.DATA, "lie_me@126.com").withValue(Email.TYPE, Email.TYPE_WORK)
					.build());

			contentResolver.applyBatch(Contacts.AUTHORITY, ops);
			publishProgress(i);
		}
	}

	private void insertCallLog(ContentResolver contentResolver) {
		for (int i = 0; i < Const.DATASIZE; i++) {
			ContentValues content = new ContentValues();
			content.put(Calls.TYPE, Calls.INCOMING_TYPE);
			content.put(Calls.NUMBER, "13167081555");
			content.put(CallLog.Calls.DATE, System.currentTimeMillis() - 1000 * (new Random().nextInt(10)));
			content.put(Calls.NEW, "1");
			contentResolver.insert(Const.CALLLOGURI, content);
			publishProgress(i);
		}
	}
	

	// 该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		textView.setText("start ansync task ....");
	}

	/**
	 * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
	 * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
	 */
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		textView.setText("stop ansync task ...." + result);
	}

	/**
	 * 这里的Intege参数对应AsyncTask中的第二个参数
	 * 在doInBackground方法当中，，每次调用publishProgress方法都会触发onProgressUpdate执行
	 * onProgressUpdate是在UI线程中执行，所有可以对UI空间进行操作
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		progressBar.setProgress(values[0]);
	}

}
