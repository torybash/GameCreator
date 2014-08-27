package itu.gamecreator.interaction;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

import java.util.ArrayList;
import java.util.Random;

public class CloneSprite extends Interaction {

	public CloneSprite() {
		super(IntType.CLONE_SPRITE);
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
		auxiliar.remove(Parser.getAvatar());
		
		s1 = auxiliar.get(random.nextInt(auxiliar.size()));
		s1.addOption(Types.SINGLETON, "False");
		
		auxiliar.add(Parser.getAvatar());
		auxiliar.remove(s1);
		s2 = auxiliar.get(random.nextInt(auxiliar.size()));
		
		setSprite1(s1);
		setSprite2(s2);
		
		Parser.getProducers().put("clone"+Parser.getProducers().size(),s1);
		
		auxiliar = null;
		return true;
	}
}
