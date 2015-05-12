package com.amosli.kptest;

import java.util.Random;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.util.Log;
import android.widget.ProgressBar;

public class CallLogDao implements IDataDao {

	@Override
	public boolean InsertIntoPhone(ContentResolver contentResolver, ProgressBar progressBar) {
		try {
			for (int i = 0; i < Const.DATASIZE; i++) {
				ContentValues content = new ContentValues();
				content.put(Calls.TYPE, Calls.INCOMING_TYPE);
				content.put(Calls.NUMBER, "13167081555");
				content.put(CallLog.Calls.DATE, System.currentTimeMillis() - 1000 * (new Random().nextInt(10)));
				content.put(Calls.NEW, "1");
				contentResolver.insert(Const.CALLLOGURI, content);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private String tag = "CallLogDao.class";

	@Override
	public void DeleteFromPhone(ContentResolver contentResolver) {
		/*
		 * 本代码因为在 Activity 中，所以可以直接调用 getContentResolver()。这个方法实际上是 Context
		 * 中定义的。
		 */
		/* 这里涉及到内容提供者的知识，其实这里是直接在操作 Android 的数据库，十分痛苦 */
		Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI, new String[] { "_id" }, null, null, "_id desc");
		Log.e(tag, "共计："+cursor.getCount() + "");

		while (cursor.moveToNext()) {
			int _id = cursor.getInt(0);
			contentResolver.delete(CallLog.Calls.CONTENT_URI, "_id=?", new String[] { _id + "" });
		}
		
	}

}
