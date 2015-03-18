package com.amosli.game2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout {

	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}

	public GameView(Context context) {
		super(context);
		initGameView();
	}

	public void initGameView() {
		setColumnCount(4);
		setBackgroundColor(0xbbdadf);

		setOnTouchListener(new OnTouchListener() {
			private float startX, startY, offsetX, offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;
					break;
				}

				if (Math.abs(offsetX) > Math.abs(offsetY)) {
					if (offsetX < -5) {
						System.out.println("left");
						swipeLeft();
					} else if (offsetX > 5) {
						swipeRight();
						System.out.println("right");
					}
				} else {
					if (offsetY < -5) {
						swipeUp();
						System.out.println("up");
					} else if (offsetY > 5) {
						System.out.println("down");
						swipeDown();
					}
				}
				return true;
			}
		});
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		int cardWidth = (Math.min(w, h) - 10) / 4;
		addCards(cardWidth);
		startGame();
	}

	private Card[][] cards = new Card[4][4];

	private void addCards(int cardwidth) {
		Card c;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				c = new Card(getContext());
				c.setNum(0);
				addView(c, cardwidth, cardwidth);
				cards[j][i] = c;
			}
		}
	}

	private void startGame() {
		//clear cards
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cards[j][i].setNum(0);
			}
		}

		addRandomNumber();
		addRandomNumber();
	}

	private List<Point> emptyPoint = new ArrayList<Point>();

	private void addRandomNumber() {
		emptyPoint.clear();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (cards[i][j].getNum() <= 0) {
					emptyPoint.add(new Point(j, i));
				}
			}
		}

		int a = (int) (Math.random() * emptyPoint.size());// 0-1
		System.out.println("a:" + a);
		System.out.println("p:" + new Random().nextInt(emptyPoint.size()));
		Point p = emptyPoint.remove(new Random().nextInt(emptyPoint.size()));
		cards[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);// 2or4 9:1
	}

	private void swipeLeft() {

	}

	private void swipeRight() {

	}

	private void swipeUp() {

	}

	private void swipeDown() {

	}

}
