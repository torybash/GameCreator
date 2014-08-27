package itu.gamecreator.interaction;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;

import java.util.ArrayList;
import java.util.Random;

public class KillIfFromAbove extends Interaction {

	public KillIfFromAbove() {
		super(IntType.KILL_IF_FROM_ABOVE);
	}

	@Override
	public boolean build() {
		super.build();
		
		if(Parser.getSprites().size() < 2)
			return false;
		
		Random random = Parser.random;
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		sprites.addAll(Parser.getSprites());
		
		Sprite s1, s2;
		s1 = sprites.get(random.nextInt(sprites.size()));
		sprites.remove(s1);
		sprites.add(Parser.getWall());
		s2 = sprites.get(random.nextInt(sprites.size()));
		
		setSprite1(s1);
		setSprite2(s2);
		
		sprites = null;
		return true;
	}
}