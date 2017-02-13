package com.zc.model.abstractfactory;

/**
 *抽象出sex
 */
public abstract class AbstractBlackHuman implements Human {

	@Override
	public void color() {
		System.out.println("i'm black human.");
	}

	@Override
	public void talk() {
		System.out.println("black human talk.");
	}

	@Override
	public abstract void sex();

}
