package itu.gamecreator.interaction;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;

import java.util.ArrayList;
import java.util.Random;

public class TeleportToExit extends Interaction {

	public TeleportToExit() {
		super(IntType.TELEPORT_TO_EXIT);
	}

	@Override
	public boolean build() {
		super.build();

		if(Parser.getSprites().size() < 2)
			return false;

		Random random = Parser.random;

		Sprite s1, s2;		
		ArrayList<Sprite> auxiliar = new ArrayList<Sprite>();
		auxiliar.addAll(Parser.getSprites());

		s1 = auxiliar.get(random.nextInt(auxiliar.size()));
		auxiliar.remove(s1);
		auxiliar.add(Parser.getWall());
		s2 = auxiliar.get(random.nextInt(auxiliar.size()));

		setSprite1(s1);
		setSprite2(s2);


		return true;
	}
}
