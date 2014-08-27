package itu.gamecreator.sprite.missile;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

public class ErracticMissile extends Missile {

	public ErracticMissile() {
		super(null);
		setSpriteClass("ErracticMissile");		
	}
	
	public ErracticMissile(Sprite father) {
		super(father);
		setSpriteClass("ErracticMissile");		
	}
	
	@Override
	public void build() {
		super.build();
		
		addOption(Types.PROB, ""+Parser.random.nextDouble());
	}

}
