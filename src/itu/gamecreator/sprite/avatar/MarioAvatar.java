package itu.gamecreator.sprite.avatar;

import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

public class MarioAvatar extends InertialAvatar {

	public MarioAvatar(Sprite father) {
		super(father);
		setSpriteClass("MarioAvatar");
	}

	public MarioAvatar() {
		super(null);
		setSpriteClass("MarioAvatar");
	}

	@Override
	public void build() {
		super.build();

		Random random = Parser.random;
		if(random.nextBoolean())
			addOption(Types.STRENGTH, "" + (random.nextInt(10)));
		
		if(random.nextBoolean())
			addOption(Types.AIRSTEERING, (random.nextBoolean() ? "True" : "False"));
	}

}
