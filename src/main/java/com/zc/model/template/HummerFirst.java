package com.zc.model.template;

public class HummerFirst extends AbstractHummer {

	@Override
	protected boolean isAlarm() {
		return false;
	}

	@Override
	protected void start() {
		System.out.println("HummerFirst start.");
	}

	@Override
	protected void stop() {
		System.out.println("HummerFirst stop.");
	}

	@Override
	protected void alarm() {
		System.out.println("HummerFirst alarm.");
	}

	@Override
	protected void engineBoom() {
		System.out.println("HummerFirst engineBoom.");
	}

}
