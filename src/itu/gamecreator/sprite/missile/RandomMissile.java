package itu.gamecreator.sprite.missile;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;


public class RandomMissile extends Missile {
	public RandomMissile() {
		super(null);
		setSpriteClass("RandomMissile");		
	}
	
	public RandomMissile(Sprite father) {
		super(father);
		setSpriteClass("RandomMissile");		
	}
}
