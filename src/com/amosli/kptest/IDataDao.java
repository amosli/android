package com.amosli.kptest;

import android.content.ContentResolver;
import android.widget.ProgressBar;

public interface IDataDao {

	public boolean InsertIntoPhone(ContentResolver contentResolver,ProgressBar progressBar);
	public void DeleteFromPhone(ContentResolver contentResolver);

}
