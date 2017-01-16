package com.zc.model.factorymethod;

public abstract class AbstractHumanFactory {
	
	public abstract <T extends Human> T createHuman(Class<T> c);
}
