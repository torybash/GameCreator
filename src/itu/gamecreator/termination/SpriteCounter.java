package itu.gamecreator.termination;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SpriteCounter extends Termination {

	public SpriteCounter() {
		super("SpriteCounter");
	}

	@Override
	public void build() {
		super.build();
		
		ArrayList<Sprite> sprites = Parser.getSprites();
		Random random = Parser.random;
		
		boolean isSpawnable = random.nextBoolean();
		
		if(isSpawnable && Parser.getProducers().size() > 0) {
			ArrayList<Sprite> spawned = new ArrayList<Sprite>();
			Iterator it = Parser.getProducers().keySet().iterator();
			while(it.hasNext()) {
				spawned.add(Parser.getProducers().get((String)it.next()));
			}
			
			Sprite sprite = spawned.get(random.nextInt(spawned.size()));
			addOption(TermType.STYPE, sprite.getName());
			
			addOption(TermType.LIMIT, "" + random.nextInt(5));
		} else {
			Sprite sprite = sprites.get(random.nextInt(sprites.size()));
			addOption(TermType.STYPE, sprite.getName());
			
			addOption(TermType.LIMIT, "0");
		}
	}
}
