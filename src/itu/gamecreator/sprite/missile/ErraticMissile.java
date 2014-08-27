package itu.gamecreator.sprite.missile;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

public class ErraticMissile extends Missile {

	public ErraticMissile() {
		super(null);
		setSpriteClass("ErraticMissile");		
	}
	
	public ErraticMissile(Sprite father) {
		super(father);
		setSpriteClass("ErraticMissile");		
	}
	
	@Override
	public void build() {
		super.build();
		
		addOption(Types.PROB, ""+Parser.random.nextDouble());
	}

}
