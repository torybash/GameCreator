package itu.gamecreator.sprite;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;

import java.util.Random;

public class Door extends Immovable {
	public Door() {
		super(null);
		setSpriteClass("Door");
	}

	public Door(Sprite father) {
		super(father);
		setSpriteClass("Door");
	}
}
