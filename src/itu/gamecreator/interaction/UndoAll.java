package itu.gamecreator.interaction;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;

import java.util.ArrayList;
import java.util.Random;

public class UndoAll extends Interaction {

	public UndoAll() {
		super(IntType.UNDO_ALL);
	}

	@Override
	public boolean build() {
		super.build();
		
		if(Parser.getSprites().size() < 2)
			return false;
		
		Random random = Parser.random;
		ArrayList<Sprite> auxiliar = new ArrayList<Sprite>();
		auxiliar.addAll(Parser.getSprites());
		
		Sprite s1 = auxiliar.get(random.nextInt(auxiliar.size()));
		auxiliar.remove(s1);
		auxiliar.add(Parser.getWall());
		Sprite s2 = auxiliar.get(random.nextInt(auxiliar.size()));
		
		setSprite1(s1);
		setSprite2(s2);
		
		auxiliar = null;
		return true;
	}
}
