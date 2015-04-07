package com.amosli.game2048;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.waps.AppConnect;

public class MainActivity extends ActionBarActivity {

	private static MainActivity mainActivity = null;
	private TextView tvScore;
	private static int score = 0;

	public MainActivity() {
		mainActivity = this;
	}

	public static MainActivity getInstance() {
		return mainActivity;
	}

	private String APP_ID = "921f1f5267ee3f7094f50a40a2e68ae8";
	private String APP_PID = "baidu";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvScore = (TextView) findViewById(R.id.tv_Score);

		AppConnect.getInstance(APP_ID, APP_PID, this);

		MiniAD();
	}

	private void MiniAD() {
		// 设置迷你广告背景颜色
		AppConnect.getInstance(this).setAdBackColor(Color.argb(50, 120, 240, 120));
		// 设置迷你广告广告语颜色
		AppConnect.getInstance(this).setAdForeColor(Color.YELLOW);
		// 若未设置以上两个颜色，则默认为黑底白字
		LinearLayout miniLayout = (LinearLayout) findViewById(R.id.miniAdLinearLayout);
		AppConnect.getInstance(this).showMiniAd(this, miniLayout, 10); // 默认 10 换一次广告
	}

	public void clearScore() {
		score = 0;
		showScore();
	}

	public void showScore() {
		tvScore.setText(score + "");
	}

	public void addScore(int s) {
		score += s;
		showScore();
	}

	public void btnMore(View v) {
		AppConnect.getInstance(this).showOffers(this);
	}
}
