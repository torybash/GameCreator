package itu.gamecreator.sprite.resource;

import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Passive;
import itu.gamecreator.sprite.Types;

public class Resource extends Passive {
	public Resource() {
		super(null);
		setSpriteClass("Resource");
	}

	public Resource(Sprite father) {
		super(father);
		setSpriteClass("Resource");
	}

	/**
	 * Mandatory:
	 * option_sprite_img 
	 * option_sprite_color option_sprite_singleton option_sprite_speed 
	 * option_sprite_cooldown option_sprite_shrink option_sprite_orientation option_sprite_limit
	 * option_sprite_value
	 * 
	 */
	@Override
	public void build() {
		super.build();
		Random random = Parser.random;
		
		//Optional
		if(random.nextBoolean())
			addOption(Types.LIMIT, "" + random.nextInt(20));
		
		if(random.nextBoolean())
			addOption(Types.VALUE, "" + random.nextInt(20));
		
		
		Parser.addResource(this);
	}

}
