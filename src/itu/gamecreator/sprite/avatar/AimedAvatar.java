package itu.gamecreator.sprite.avatar;

import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;
import itu.gamecreator.sprite.missile.ErraticMissile;
import itu.gamecreator.sprite.missile.Missile;
import itu.gamecreator.sprite.missile.RandomMissile;
import itu.gamecreator.sprite.missile.Walker;
import itu.gamecreator.sprite.missile.WalkerJumper;
import itu.gamecreator.sprite.resource.Resource;

public class AimedAvatar extends ShootAvatar{
	public AimedAvatar(Sprite father) {
		super(father);
		setSpriteClass("AimedAvatar");
	}

	public AimedAvatar() {
		super(null);
		setSpriteClass("AimedAvatar");
	}
	
	@Override
	public void build() {
		super.build();
		
		Random random = Parser.random;

		//decide if need ammo
		if(random.nextBoolean())
			addOption(Types.ANGLE_DIFF, "" + ((random.nextInt(500) + 0.01)/100.00 ));
		
		if(random.nextBoolean())
			addOption(Types.SPEED, ""+((random.nextInt(1000) + 0.01)/100.00));
	}
	
}
