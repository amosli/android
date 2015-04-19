package com.amosli.androidmonitor;

public class Call {
	private String phone;
	private int duration;
	private int type;
	private long date;

	public Call(String phone, int duration, int type, long date) {
		super();
		this.phone = phone;
		this.duration = duration;
		this.type = type;
		this.date = date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Call [phone=" + phone + ", duration=" + duration + ", type=" + type + ", date=" + date + "]";
	}

}
