package com.zc.model.factorymethod.simplefactory;

import com.zc.model.factorymethod.Human;

public class SimpleHumanFactory {

	@SuppressWarnings("unchecked")
	public static <T extends Human> T createHuman(Class<T> clazz) {
		
		T t = null;
		try {
			t = (T)Class.forName(clazz.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
