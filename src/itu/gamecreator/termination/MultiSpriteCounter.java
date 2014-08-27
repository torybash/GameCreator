package itu.gamecreator.termination;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

import java.util.ArrayList;
import java.util.Random;

public class MultiSpriteCounter extends Termination {

	public MultiSpriteCounter() {
		super("MultiSpriteCounter");
	}

	@Override
	public void build() {
		super.build();
		
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		sprites.addAll(Parser.getSprites());
		Random random = Parser.random;
		
		Sprite sprite1 = sprites.get(random.nextInt(sprites.size()));
		addOption(TermType.STYPE_1, sprite1.getName());
			
		sprites.remove(sprite1);
		
		Sprite sprite2 = sprites.get(random.nextInt(sprites.size()));
		addOption(TermType.STYPE_2, sprite2.getName());		
		
		if((sprite1.getOptions().containsKey(Types.SINGLETON) && sprite1.getOptions().get(Types.SINGLETON).equals("True")) ||
				(sprite2.getOptions().containsKey(Types.SINGLETON) && sprite2.getOptions().get(Types.SINGLETON).equals("True")))
			addOption(Types.LIMIT, "0");
	}
}
