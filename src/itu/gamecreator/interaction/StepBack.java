package itu.gamecreator.interaction;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;

import java.util.ArrayList;
import java.util.Random;

public class StepBack extends Interaction {

	public StepBack() {
		super(IntType.STEP_BACK);
	}

	@Override
	public boolean build() {
		super.build();
		if(Parser.getSprites().size() < 2 || Parser.getResources().size() == 0)
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

	public boolean build(Sprite sprite, Sprite wall) {
		super.build();
		
		setSprite1(sprite);
		setSprite2(wall);
		return true;
	}
}
