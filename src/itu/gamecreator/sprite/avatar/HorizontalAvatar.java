package itu.gamecreator.sprite.avatar;

import itu.gamecreator.creator.Sprite;

public class HorizontalAvatar extends MovingAvatar {

	public HorizontalAvatar(Sprite father) {
		super(father);
		setSpriteClass("HorizontalAvatar");
	}

	public HorizontalAvatar() {
		super(null);
		setSpriteClass("HorizontalAvatar");
	}

}
