package itu.gamecreator.sprite.missile;

import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

public class WalkerJumper extends Walker {
	public WalkerJumper() {
		super(null);
		setSpriteClass("WalkerJumper");		
	}
	
	public WalkerJumper(Sprite father) {
		super(father);
		setSpriteClass("WalkerJumper");		
	}
	
	@Override
	public void build() {
		super.build();
		
		Random random = Parser.random;
		
		if(random.nextBoolean())
			addOption(Types.PROBABILITY, "" + random.nextDouble());
		
		if(random.nextBoolean())
			addOption(Types.STRENGTH, ""+(random.nextInt(1000) + 0.01)/100);
	}
	
}
