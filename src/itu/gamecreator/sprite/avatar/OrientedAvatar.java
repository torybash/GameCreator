package itu.gamecreator.sprite.avatar;

import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

public class OrientedAvatar extends MovingAvatar {
	public OrientedAvatar(Sprite father) {
		super(father);
		setSpriteClass("OrientedAvatar");
	}

	public OrientedAvatar() {
		super(null);
		setSpriteClass("OrientedAvatar");
	}
	
	@Override
	public void build() {
		super.build();
		
		Random random = Parser.random;
		if(random.nextBoolean())
			addOption(Types.DRAW_ARROW, (random.nextBoolean() ? "True" : "False"));
		
	}

}
