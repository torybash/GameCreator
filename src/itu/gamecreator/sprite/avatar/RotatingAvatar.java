package itu.gamecreator.sprite.avatar;

import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

public class RotatingAvatar extends MovingAvatar {
	public RotatingAvatar(Sprite father) {
		super(father);
		setSpriteClass("RotatingAvatar");
	}

	public RotatingAvatar() {
		super(null);
		setSpriteClass("RotatingAvatar");
	}

	@Override
	public void build() {
		super.build();

		Random random = Parser.random;

		if(random.nextBoolean())
			addOption(Types.DRAW_ARROW, (random.nextBoolean() ? "True" : "False"));

		if(random.nextBoolean())
			addOption(Types.SPEED, ""+((random.nextInt(200) + 0.01)/100.00));
	}
}
