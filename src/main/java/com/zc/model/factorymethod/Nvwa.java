package com.zc.model.factorymethod;

public class Nvwa {

	public static void main(String[] args) {
		
		AbstractHumanFactory factory = new HumanFactory();
		Human human1 = factory.createHuman(WhiteHuman.class);
		human1.talk();
		human1.color();
		
		System.out.println("----------------------");
		
		Human human2 = factory.createHuman(BlackHuman.class);
		human2.talk();
		human2.color();
	}
}
