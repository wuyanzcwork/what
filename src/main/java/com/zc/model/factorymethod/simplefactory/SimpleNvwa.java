package com.zc.model.factorymethod.simplefactory;

import com.zc.model.factorymethod.BlackHuman;
import com.zc.model.factorymethod.Human;
import com.zc.model.factorymethod.WhiteHuman;

public class SimpleNvwa {

	public static void main(String[] args) {
		Human human1 = SimpleHumanFactory.createHuman(WhiteHuman.class);
		human1.talk();
		human1.color();
		
		System.out.println("----------------------");
		
		Human human2 = SimpleHumanFactory.createHuman(BlackHuman.class);
		human2.talk();
		human2.color();
	}
}
