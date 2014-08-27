package itu.gamecreator.sprite;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.missile.ErraticMissile;
import itu.gamecreator.sprite.missile.Missile;
import itu.gamecreator.sprite.missile.RandomMissile;
import itu.gamecreator.sprite.missile.Walker;
import itu.gamecreator.sprite.missile.WalkerJumper;
import itu.gamecreator.sprite.resource.Resource;

import java.util.Random;

public class OrientedFlicker extends Flicker {
	public OrientedFlicker(Sprite father) {
		super(father);
		setSpriteClass("OrientedFlicker");
	}

	public OrientedFlicker() {
		super(null);
		setSpriteClass("OrientedFlicker");
	}

	@Override
	public void build() {
		super.build();
		Random random = Parser.random;

		if(random.nextBoolean())
			addOption(Types.DRAW_ARROW,(random.nextBoolean() ? "True" : "False"));
	}
}
