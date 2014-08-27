package itu.gamecreator.sprite.npc;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

import java.util.ArrayList;
import java.util.Random;

public class Fleeing extends Chaser {
	public Fleeing() {
		super(null);
		setSpriteClass("Fleeing");
	}

	public Fleeing(Sprite father) {
		super(father);
		setSpriteClass("Fleeing");
	}

	@Override
	public void build() {
		super.build();

		Random random = Parser.random;

		ArrayList<Sprite> sprites = Parser.getSprites();
		addOption(Types.STYPE, sprites.get(random.nextInt(sprites.size())).getName());

		if(random.nextBoolean())
			addOption(Types.FLEEING, (random.nextBoolean() ? "True" : "False"));

	}
}