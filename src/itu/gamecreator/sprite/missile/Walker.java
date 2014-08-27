package itu.gamecreator.sprite.missile;

import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

public class Walker extends Missile {
	public Walker() {
		super(null);
		setSpriteClass("Walker");		
	}
	
	public Walker(Sprite father) {
		super(father);
		setSpriteClass("Walker");		
	}
	
	@Override
	public void build() {
		super.build();
		
		Random random = Parser.random;
		if(random.nextBoolean())
			addOption(Types.AIRSTEERING, (random.nextBoolean() ? "True" : "False"));
	}
}
