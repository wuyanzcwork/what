package com.zc.model.abstractfactory;

public abstract class AbstractWhiteHuman implements Human {

	@Override
	public void color() {
		System.out.println("i'm white human.");
	}

	@Override
	public void talk() {
		System.out.println("white human talk.");
	}

	@Override
	public abstract void sex();

}
