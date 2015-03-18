package com.amosli.rp;

import java.util.Date;

public class Calculator {
	@SuppressWarnings("deprecation")
	public static int calculateRPByName(String paramString) {
		Date dt = new Date();
		int result = 0;
		for (int i = 0; i < paramString.length(); i++) {
			int para = paramString.charAt(i);
			result += para * 2 - dt.getYear() - dt.getDate();
		}
		result = Math.abs(result) % 100;

		String valueOf = String.valueOf(result).substring(0, 2);
		System.out.println(valueOf);
		return result;
	}

	public static String[] comments = { "�������Ʒ���ܵ�...�Ҷ�������..", "���ˣ�����ûʲô��Ʒ��̸��...", "���Ҳ���...��Ӧ�ø���̸��Ʒ�����...", "ɱ����û��?�Ź���û��?��Ӧ���޶�����?", "��ò��Ӧ�������͵�����ڴ���ϴ��İ�...", "�����Ʒ֮����ʵ�����˾��Ȱ�...",
			"�����Ʒ̫���ˡ���Ӧ���иɻ��µ��Ⱥð�?", "�����Ʒ���!�϶�������͵����������...", "��ӵ����˲����Ʒ�뾭��������汣�����...", "��ʵ����..��Щ��̳���澭�����ֵ�͵�����ǲ�����Ľ���?", "����ش�С��֮�����û�ٸɰ�?", "�����Ʒ̫����..�Բ�С�ľͻ�ȥ�ɻ����˰�?", "�����Ʒ�Ƚϲ���..Ҫ�úõ�Լ���Լ���..",
			"�����Ʒ����ǿǿ..Ҫ�Լ�����Ϊ֮..", "������������Ʒ���ǲ�����..", "���нϺõ���Ʒ..��������..", "�����Ʒ����..Ӧ��һ���˲Ű�?", "�����Ʒ���..������Ӧ������İ��ð�..", "�����Ʒ̫����..����ǵ������׷氡...", "�������˵İ�����", "�������㲻���ˣ������񣡣���" };

	public static String getComment(int score) {
		int i = score / 5;
		return comments[i];
	}
}