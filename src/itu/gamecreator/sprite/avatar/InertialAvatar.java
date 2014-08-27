package itu.gamecreator.sprite.avatar;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

import java.util.Random;

public class InertialAvatar extends OrientedAvatar {
	public InertialAvatar(Sprite father) {
		super(father);
		setSpriteClass("InertialAvatar");
	}

	public InertialAvatar() {
		super(null);
		setSpriteClass("InertialAvatar");
	}

	@Override
	public void build() {
		super.build();
	}
}
