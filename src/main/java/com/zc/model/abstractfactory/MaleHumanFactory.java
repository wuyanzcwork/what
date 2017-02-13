package com.zc.model.abstractfactory;

public class MaleHumanFactory implements HumanFactory {

	@Override
	public Human createBlackHuman() {
		return new MaleBlackHuman();
	}

	@Override
	public Human createWhiteHuman() {
		return new MaleWhiteHuman();
	}
}
