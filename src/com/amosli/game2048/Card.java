package com.amosli.game2048;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

	private int num = 0;

	public Card(Context context) {
		super(context);
		label = new TextView(getContext());
		label.setTextSize(32);
		label.setGravity(3);
		label.setTextColor(0x33ffddff);

		// fill parent
		LayoutParams lp = new LayoutParams(-1, -1);
		lp.setMargins(10, 10, 10, 10);
		setNum(0);
		addView(label, lp);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		
		// int is means R.id.* ,so cannot use
		label.setText(num + "");
		if(num==0){
			label.setText("");	
		}
		
	}

	public boolean equals(Card o) {
		return o.getNum() == getNum();
	}

	private TextView label;

}
