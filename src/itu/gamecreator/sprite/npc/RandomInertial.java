package itu.gamecreator.sprite.npc;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

import java.util.ArrayList;
import java.util.Random;

public class RandomInertial extends RandomNPC {
	public RandomInertial() {
		super(null);
		setSpriteClass("RandomInertial");
	}

	public RandomInertial(Sprite father) {
		super(father);
		setSpriteClass("RandomInertial");
	}

	@Override
	public void build() {
		super.build();

		Random random = Parser.random;

	}
}