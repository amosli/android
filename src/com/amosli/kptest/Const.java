package com.amosli.kptest;

import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract.RawContacts;

public class Const {

	public static final Uri SMSURI = Uri.parse("content://sms/#");
	public static final Uri CONTACTURI = RawContacts.CONTENT_URI;
	public static final Uri CALLLOGURI =  CallLog.Calls.CONTENT_URI; 
	public static final int DATASIZE = 1* 1000;
}
