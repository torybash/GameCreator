package itu.gamecreator.interaction;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

import java.util.ArrayList;
import java.util.Random;

public class SpawnIfHasMore extends Interaction {

	public SpawnIfHasMore() {
		super(IntType.SPAWN_IF_HAS_MORE);
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
		s2 = auxiliar.get(random.nextInt(auxiliar.size()));

		setSprite1(s1);
		setSprite2(s2);

		addOption(IntType.RESOURCE, resourceSprite.getName());

		//limit
		int limit = random.nextInt(10) - 5;
		if(resourceSprite.getOptions().containsKey(Types.LIMIT))
			addOption(IntType.LIMIT, "" + 
					(limit < Integer.parseInt(resourceSprite.getOptions().get(Types.LIMIT)) ? 
							limit : resourceSprite.getOptions().get(Types.LIMIT)));
		else
			addOption(IntType.LIMIT, "" + limit);
		
		//stype
		addOption(IntType.STYPE, Parser.getSprites().get(random.nextInt(Parser.getSprites().size())).getName());
		return true;
	}
}
