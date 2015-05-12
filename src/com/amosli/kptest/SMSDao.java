package com.amosli.kptest;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.ProgressBar;

public class SMSDao implements IDataDao {

	@Override
	public boolean InsertIntoPhone(ContentResolver contentResolver, ProgressBar progressBar) {
		try {
			DeleteFromPhone(contentResolver);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void DeleteFromPhone(ContentResolver contentResolver) {
		Cursor cursor = contentResolver.query(Const.SMSURI, new String[] { "thread_id" }, null, null, null);
		if (cursor == null) {
			return;
		}
		ArrayList<Long> threadList = new ArrayList<Long>();
		long t = System.currentTimeMillis();
		int count = cursor.getCount();
		for (int i = 0; i < count; i++) {
			cursor.moveToPosition(i);
			threadList.add(cursor.getLong(0));
		}

		Log.e("SMS.class time:", (System.currentTimeMillis() - t) + "");
		
		cursor.close();
		
		t = System.currentTimeMillis();
		threadList.clear();
		cursor = contentResolver.query(Const.SMSURI, new String[] { "thread_id" }, null, null, null);
		while (cursor.moveToNext()) {
			long threadId = cursor.getLong(0);
			threadList.add(threadId);
		}
		Log.e("SMS.class time2:", (System.currentTimeMillis() - t) + "");

		for (Long threadId : threadList) {
			contentResolver.delete(Uri.parse("content://sms/conversations/" + threadId), null, null);
		}
	}

}
