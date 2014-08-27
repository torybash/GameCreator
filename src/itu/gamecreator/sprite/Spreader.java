package itu.gamecreator.sprite;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;

import java.util.Random;

public class Spreader extends Flicker {
	//Must Be Subtipe!!!
	
	public Spreader() {
		super(null);
		setSpriteClass("Spreader");
	}
	
	public Spreader(Sprite father) {
		super(father);
		setSpriteClass("Spreader");
	}

	@Override
	public void build() {
		super.build();
		Random random = Parser.random;

		if(random.nextBoolean())
			addOption(Types.DRAW_ARROW,(random.nextBoolean() ? "True" : "False"));
		
		if(random.nextBoolean())
			addOption(Types.SPREAD_PROB,"" + random.nextDouble());
	}
}
