package itu.gamecreator.interaction;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

import java.util.ArrayList;
import java.util.Random;

public class TransformTo extends Interaction {

	public TransformTo() {
		super(IntType.TRANSFORM_TO);
	}

	@Override
	public boolean build() {
		super.build();
		if(Parser.getSprites().size() < 2 || Parser.getResources().size() == 0)
			return false;

		Random random = Parser.random;

		//resource
		Sprite resourceSprite = Parser.getResources().get(random.nextInt(Parser.getResources().size()));

		Sprite s1, s2;		
		ArrayList<Sprite> auxiliar = new ArrayList<Sprite>();
		auxiliar.addAll(Parser.getSprites());
		auxiliar.remove(resourceSprite);

		s1 = auxiliar.get(random.nextInt(auxiliar.size()));
		auxiliar.remove(s1);
		auxiliar.add(Parser.getWall());
		s2 = auxiliar.get(random.nextInt(auxiliar.size()));

		setSprite1(s1);
		setSprite2(s2);
		
		//stype (can't be the original type)
		addOption(IntType.STYPE, auxiliar.get(random.nextInt(auxiliar.size())).getName());
		return true;
	}
}
