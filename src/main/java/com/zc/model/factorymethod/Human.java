package com.zc.model.factorymethod;

public abstract class Human {

	private String s;

	public void talk() {
		System.out.println( "i'm talking" );
	}
	
	public abstract void color();
	
}
