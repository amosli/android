package com.amosli.kptest;

import java.util.ArrayList;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.widget.ProgressBar;

public class ContactDao implements IDataDao {

	@Override
	public boolean InsertIntoPhone(ContentResolver contentResolver, ProgressBar progressBar) {

		try {
			for (int i = 0; i < Const.DATASIZE; i++) {

				ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
				int size = ops.size();
				ops.add(ContentProviderOperation.newInsert(Const.CONTACTURI).withValue(RawContacts.ACCOUNT_TYPE, null)
						.withValue(RawContacts.ACCOUNT_NAME, null).build());

				ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference(Data.RAW_CONTACT_ID, size)
						.withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE).withValue(StructuredName.DISPLAY_NAME, "张三" + i).build());

				ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValue(Data.RAW_CONTACT_ID, size)
						.withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE).withValue(Phone.NUMBER, "13855663344").withValue(Phone.TYPE, "手机号码")
						.build());

				ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference(Data.RAW_CONTACT_ID, size)
						.withValue(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE).withValue(Email.DATA, "lie_me@126.com")
						.withValue(Email.TYPE, Email.TYPE_WORK).build());

				contentResolver.applyBatch(Contacts.AUTHORITY, ops);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void DeleteFromPhone(ContentResolver contentResolver) {

		Cursor contactsCur = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, new String[] { "_id" }, null, null, null);
		while (contactsCur.moveToNext()) {
			// 获取ID
			String rawId = contactsCur.getString(0);
			// 删除
			contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI, ContactsContract.Data._ID + " =?", new String[] { rawId });
		}
	}

}
