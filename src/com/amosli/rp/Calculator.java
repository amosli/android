package com.amosli.rp;

import java.util.Date;
import java.util.Random;

public class Calculator {
	@SuppressWarnings("deprecation")
	public static int calculateRPByName(String paramString) {
		int result = 0;
		try {

			Date dt = new Date();
			for (int i = 0; i < paramString.length(); i++) {
				int para = paramString.charAt(i);

				result += para * 2 - dt.getYear() - dt.getDate() * 3;
			}
			result = Math.abs(result) % 100;

			String valueOf = String.valueOf(result).substring(0, 2);
			result = Integer.valueOf(valueOf);

		} catch (Exception e) {
			result = new Random().nextInt(100);
		}
		System.out.println(result);
		return result;
	}

	public static String[] comments = { "你的这人品给败得...我对你无语..", "算了，跟你没什么人品好谈的...", "是我不好...不应该跟你谈人品问题的...", "杀过人没有?放过火没有?你应该无恶不做吧?", "你貌似应该三岁就偷看隔壁大妈洗澡的吧...", "你的人品之低下实在让人惊讶啊...",
			"你的人品太差了。你应该有干坏事的嗜好吧?", "你的人品真差!肯定经常做偷鸡摸狗的事...", "你拥有如此差的人品请经常祈求佛祖保佑你吧...", "老实交待..那些论坛上面经常出现的偷拍照是不是你的杰作?", "你随地大小便之类的事没少干吧?", "你的人品太差了..稍不小心就会去干坏事了吧?", "你的人品比较差了..要好好的约束自己啊..",
			"你的人品勉勉强强..要自己好自为之..", "有你这样的人品算是不错了..", "你有较好的人品..继续保持..", "你的人品不错..应该一表人才吧?", "你的人品真好..做好事应该是你的爱好吧..", "你的人品太好了..你就是当代活雷锋啊...", "你是世人的榜样！", "天啦！你不是人！你是神！！！" };

	public static String getComment(int score) {
		int i = score / 5;
		return comments[i];
	}
	
	public static void main(String[] args) {
		System.out.println("aa");
	}
}