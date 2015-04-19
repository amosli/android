package com.amosli.androidmonitor;

import android.database.ContentObserver;
import android.os.Handler;


public class SMSObserver extends ContentObserver {

	public SMSObserver(Handler handler) {
		super(handler);
	}
	
	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);
	}
}
