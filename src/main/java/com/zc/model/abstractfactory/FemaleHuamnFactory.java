package com.zc.model.abstractfactory;

public class FemaleHuamnFactory implements HumanFactory {

	@Override
	public Human createBlackHuman() {
		return new FemaleBlackHuman();
	}

	@Override
	public Human createWhiteHuman() {
		return new FemaleWhiteHuman();
	}

}
