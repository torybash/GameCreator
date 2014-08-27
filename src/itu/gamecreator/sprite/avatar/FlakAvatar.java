package itu.gamecreator.sprite.avatar;

import java.util.HashMap;
import java.util.Iterator;
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

public class FlakAvatar extends HorizontalAvatar {

	public FlakAvatar(Sprite father) {
		super(father);
		setSpriteClass("FlakAvatar");
	}

	public FlakAvatar() {
		super(null);
		setSpriteClass("FlakAvatar");
	}

	/**
	 * Mandatory:
	 * option_sprite_img option_sprite_singleton
	 * option_sprite_stype
	 * Optional:
	 * option_sprite_color option_sprite_speed option_sprite_cooldown 
	 * option_sprite_shrink option_sprite_orientation
	 */
	@Override
	public void build() {
		super.build();
		Random random = Parser.random;

		final int missileType = random.nextInt(5);
		switch(missileType) {
		case 1:
			RandomMissile rm = new RandomMissile();
			addOption(Types.STYPE, rm.getName());
			break;
		case 2:
			ErraticMissile em = new ErraticMissile();
			addOption(Types.STYPE, em.getName());
			break;
		case 3:
			Walker w = new Walker();
			addOption(Types.STYPE, w.getName());
			break;
		case 4:
			WalkerJumper wj = new WalkerJumper();
			addOption(Types.STYPE, wj.getName());
			break;
		case 0: default:
			Missile m = new Missile();
			addOption(Types.STYPE, m.getName());
			break;
		}

		//decide if need ammo
		if(random.nextBoolean()) {
			//create Resource
			Resource r = new Resource();
			addOption(Types.AMMO, r.getName());
			
			//amoCost
			if(random.nextBoolean())
				addOption(Types.AMMO, "" + random.nextInt(10));

			//minAmmo
			if(random.nextBoolean())
				addOption(Types.AMMO_COST, "" + random.nextInt(10));
		}
	}


}
