package itu.gamecreator.interaction;

import java.util.ArrayList;
import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;

public class CollectResource extends Interaction {

	public CollectResource() {
		super(IntType.COLLECT_RESOURCE);
	}

	@Override
	public boolean build() {
		super.build();
		if(Parser.getSprites().size() < 2 || Parser.getResources().size() == 0)
			return false;
		
		Random random = Parser.random;
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		sprites.addAll(Parser.getSprites());
		sprites.removeAll(Parser.getResources());
		
		setSprite1(Parser.getResources().get(random.nextInt(Parser.getResources().size())));
		setSprite2(sprites.get(random.nextInt(sprites.size())));
		return true;
	}
}
