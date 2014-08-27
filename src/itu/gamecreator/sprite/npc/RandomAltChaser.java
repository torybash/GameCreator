package itu.gamecreator.sprite.npc;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

import java.util.ArrayList;
import java.util.Random;

public class RandomAltChaser extends AlternateChaser {
	public RandomAltChaser() {
		super(null);
		setSpriteClass("RandomAltChaser");
	}

	public RandomAltChaser(Sprite father) {
		super(father);
		setSpriteClass("RandomAltChaser");
	}

	@Override
	public void build() {
		super.build();

		Random random = Parser.random;
		if(random.nextBoolean())
			addOption(Types.EPSILON, "" + random.nextDouble());

	}
}
