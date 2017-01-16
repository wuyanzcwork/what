package com.zc.model.template;

public class HummerSecond extends AbstractHummer {

	@Override
	protected boolean isAlarm() {
		return true;
	}

	@Override
	protected void start() {
		System.out.println("HummerSecond start.");
	}

	@Override
	protected void stop() {
		System.out.println("HummerSecond stop.");
	}

	@Override
	protected void alarm() {
		System.out.println("HummerSecond alarm.");
	}

	@Override
	protected void engineBoom() {
		System.out.println("HummerSecond engineBoom.");
	}

}
