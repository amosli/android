package com.amosli.androidmonitor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GetProperty {

	public static List<Class> GetProperties(Class c) {
		List<String> list = new ArrayList<>();

		Field[] fields = c.getFields();
		for (Field field : fields) {
			list.add(field.getName());
		}
		return null;
	}

}
