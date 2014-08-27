package itu.gamecreator.sprite.avatar;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

import java.util.Random;

public class RotationFlippingAvatar extends RotationAvatar {
	public RotationFlippingAvatar(Sprite father) {
		super(father);
		setSpriteClass("RotationFlippingAvatar");
	}

	public RotationFlippingAvatar() {
		super(null);
		setSpriteClass("RotationFlippingAvatar");
	}

	@Override
	public void build() {
		super.build();

		Random random = Parser.random;

		if(random.nextBoolean())
			addOption(Types.NOISE_LEVEL,  ""+random.nextDouble());
	}
}
