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
		 * ��������Ϊ�� Activity �У����Կ���ֱ�ӵ��� getContentResolver()���������ʵ������ Context
		 * �ж���ġ�
		 */
		/* �����漰�������ṩ�ߵ�֪ʶ����ʵ������ֱ���ڲ��� Android �����ݿ⣬ʮ��ʹ�� */
		Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI, new String[] { "_id" }, null, null, "_id desc");
		Log.e(tag, "���ƣ�"+cursor.getCount() + "");

		while (cursor.moveToNext()) {
			int _id = cursor.getInt(0);
			contentResolver.delete(CallLog.Calls.CONTENT_URI, "_id=?", new String[] { _id + "" });
		}
		
	}

}
