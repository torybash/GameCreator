package itu.gamecreator.sprite.avatar;

import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

public class RotationAvatar extends MovingAvatar {
	public RotationAvatar(Sprite father) {
		super(father);
		setSpriteClass("RotationAvatar");
	}

	public RotationAvatar() {
		super(null);
		setSpriteClass("RotationAvatar");
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
