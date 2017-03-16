package com.zc.model.template;

public class HummerMain {

	public static void main(String[] args) {

		AbstractHummer hummer1 = new HummerFirst();
		hummer1.run();

		AbstractHummer hummer2 = new HummerSecond();
		hummer2.run();
	}
}
