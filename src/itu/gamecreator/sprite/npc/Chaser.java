package itu.gamecreator.sprite.npc;

import java.util.ArrayList;
import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

public class Chaser extends RandomNPC {
	public Chaser() {
		super(null);
		setSpriteClass("Chaser");
	}

	public Chaser(Sprite father) {
		super(father);
		setSpriteClass("Chaser");
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
