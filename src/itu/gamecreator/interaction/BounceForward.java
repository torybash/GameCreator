package itu.gamecreator.interaction;

import java.util.ArrayList;
import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;

public class BounceForward extends Interaction {

	public BounceForward() {
		super(IntType.BOUNCE_FORWARD);
	}
	
	@Override
	public boolean build() {
		super.build();
		if(Parser.getSprites().size() < 2)
			return false;
		
		Random random = Parser.random;
		ArrayList<Sprite> sprites = new ArrayList<Sprite>(); 
		sprites.addAll(Parser.getSprites());
		int s1, s2;
		Sprite st1, st2;
		s1 = random.nextInt(sprites.size());
		st1 = sprites.get(s1);
		sprites.remove(s1);
		
		sprites.add(Parser.getWall());
		s2 = random.nextInt(sprites.size());
		st2 = sprites.get(s2);
		
		setSprite1(st1);
		setSprite2(st2);
		
		sprites = null;
		return true;
	}
	
}
