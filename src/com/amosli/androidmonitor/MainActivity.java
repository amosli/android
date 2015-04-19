package com.amosli.androidmonitor;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private SMSObserver smsObserver;
	private String LogTag = "MainActivity.class";
	private List<SMS> smsList = new ArrayList<>();
	private Uri SMS_URI = Uri.parse("content://sms/");
	private static Uri SMS_Inbox = Uri.parse("content://sms/inbox");
	private static Uri Contacts = Uri.parse("content:// com.android.contacts/data/phones");
	private List<Contact> contactList = new ArrayList<Contact>();
	private static Uri Sims = Uri.parse("content://icc/adn");

	private List<Call> callList = new ArrayList<Call>();

	/** 联系人显示名称 **/
	// private static final int PHONES_DISPLAY_NAME_INDEX = 0;
	// /** 电话号码 **/
	// private static final int PHONES_NUMBER_INDEX = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		smsObserver = new SMSObserver(this, handler);
		getContentResolver().registerContentObserver(SMS_Inbox, true, smsObserver);
	}

	public Handler handler = new Handler() {

	};

	public void btnClick(View v) {
		int id = ((Button) v).getId();
		Toast.makeText(this, "v.id:" + v + "  >>id:" + id, Toast.LENGTH_SHORT).show();
		switch (id) {
		case R.id.btnContact:
			Toast.makeText(this, "contact", Toast.LENGTH_SHORT).show();
			ReadMsg("contact");
			ShowMsg("contact");
			break;
		case R.id.btnLocation:
			Toast.makeText(this, "location", Toast.LENGTH_SHORT).show();
			ReadMsg("location");
			ShowMsg("location");
			break;

		case R.id.btnSms:
			ReadMsg("sms");
			ShowMsg("sms");
			Toast.makeText(this, "sms", Toast.LENGTH_SHORT).show();
			break;

		case R.id.btnCallRecords:
			ReadMsg("call");
			ShowMsg("call");
			break;

		default:
			break;
		}

	}

	// TODO showmsg
	private void ShowMsg(String name) {
		switch (name) {
		case "sms":
			for (SMS s : smsList) {
				Toast.makeText(this, s.toString(), Toast.LENGTH_SHORT).show();
			}

			break;
		case "call":
			for (Call s : callList) {
				Toast.makeText(this, "contact", Toast.LENGTH_SHORT).show();

				// System.out.println(s.toString());
			}
			break;

		case "contact":
			for (Contact s : contactList) {
				Toast.makeText(this, "contact", Toast.LENGTH_SHORT).show();
				// System.out.println(s.toString());
			}
			break;
		case "location":

			break;
		default:
			break;
		}

	}

	/**
	 * read sms
	 */
	private void ReadMsg(String name) {

		ContentResolver cr = getContentResolver();
		String[] projection = new String[] { "body" };
		Cursor cur = null;
		switch (name) {
		case "sms":
			cur = cr.query(SMS_URI, projection, null, null, "date desc");
			if (null == cur) {
				Toast.makeText(this, "no sms", Toast.LENGTH_SHORT).show();
				return;
			}
			GetSMS(cur);
			break;
		case "call":
			cur = cr.query(Calls.CONTENT_URI, null, null, null, null);
			if (null == cur) {
				Toast.makeText(this, "no call", Toast.LENGTH_SHORT).show();
				return;
			}
			GetCalls(cur);
			break;

		case "location":
			Toast.makeText(this, "no location", Toast.LENGTH_SHORT).show();
			break;

		case "contact":
			projection = new String[] { Phone.DISPLAY_NAME, Phone.NUMBER, Phone.PHOTO_ID, Phone.CONTACT_ID };
			cur = cr.query(Contacts, projection, null, null, "date desc");
			if (null == cur) {
				Toast.makeText(this, "no contacts", Toast.LENGTH_SHORT).show();
				return;
			}
			GetContacts(cur);

			cur = cr.query(Sims, null, null, null, null);
			if (null == cur) {
				Log.w(LogTag, "no calls");
				return;
			}
			GetContacts(cur);

			break;
		}
		cur.close();
	}

	private void GetCalls(Cursor cur) {
		while (cur.moveToNext()) {
			String phone = cur.getString(cur.getColumnIndex(Calls.NUMBER));
			int duration = cur.getInt(cur.getColumnIndex(Calls.DURATION));
			int type = cur.getInt(cur.getColumnIndex(Calls.TYPE));
			long date = cur.getLong(cur.getColumnIndex(Calls.DATE));

			Call c = new Call(phone, duration, type, date);
			callList.add(c);
		}
	}

	/**
	 * get sms
	 * 
	 * @param cur
	 */
	private void GetSMS(Cursor cur) {
		while (cur.moveToNext()) {
			String address = cur.getString(cur.getColumnIndex("address"));
			String person = cur.getString(cur.getColumnIndex("person"));
			String body = cur.getString(cur.getColumnIndex("body"));
			int type = cur.getInt(cur.getColumnIndex("type"));
			SMS s = new SMS(address, person, body, type);
			smsList.add(s);
		}
	}

	/**
	 * get contacts
	 * 
	 * @param cur
	 */
	private void GetContacts(Cursor cur) {
		while (cur.moveToNext()) {
			if (TextUtils.isEmpty(cur.getString(cur.getColumnIndex(Phone.NUMBER)))) {
				return;
			}
			Contact c = new Contact(cur.getString(cur.getColumnIndex(Phone.DISPLAY_NAME)), cur.getString(cur.getColumnIndex(Phone.NUMBER)));
			contactList.add(c);
		}
	}

	/**
	 * sms observer
	 * 
	 * @author amosli
	 * 
	 */
	public class SMSObserver extends ContentObserver {

		public SMSObserver(Context context, Handler handler) {
			super(handler);
		}

		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			ReadMsg("sms");
		}
	}

}
