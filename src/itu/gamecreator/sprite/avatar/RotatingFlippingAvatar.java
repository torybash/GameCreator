package itu.gamecreator.sprite.avatar;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

import java.util.Random;

public class RotatingFlippingAvatar extends RotatingAvatar {
	public RotatingFlippingAvatar(Sprite father) {
		super(father);
		setSpriteClass("RotatingFlippingAvatar");
	}

	public RotatingFlippingAvatar() {
		super(null);
		setSpriteClass("RotatingFlippingAvatar");
	}

	@Override
	public void build() {
		super.build();

		Random random = Parser.random;

		if(random.nextBoolean())
			addOption(Types.NOISE_LEVEL,  ""+random.nextDouble());
	}
}
