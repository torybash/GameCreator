package itu.gamecreator.sprite.avatar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

public class MovingAvatar extends Sprite {

	public MovingAvatar(Sprite father) {
		super("avatar","MovingAvatar", father);
	}
	
	public MovingAvatar() {
		super("avatar","MovingAvatar", null);
	}

	/**
	 * Mandatory:
	 * option_sprite_img option_sprite_singleton
	 * Optional:
	 * option_sprite_color
	 * option_sprite_speed option_sprite_cooldown option_sprite_shrink option_sprite_orientation
	 */
	@Override
	public void build() {
		super.build();
		//Mandatory
		addOption(Types.IMG, "avatar");
		addOption(Types.SINGLETON,"True");
		
		Random random = Parser.random;
		if(random.nextBoolean())
			addOption(Types.COLOR, Types.COLORS[random.nextInt(Types.COLORS.length)]);
		
		if(random.nextBoolean())
			addOption(Types.SPEED, "" + ((random.nextInt(200) + 0.01)/100.00));
		
		if(random.nextBoolean())
			addOption(Types.COOLDOWN, "" + random.nextInt(5));
		
		if(random.nextBoolean())
			addOption(Types.SHRINKFACTOR, "" + random.nextDouble());
		
		if(random.nextBoolean())
			addOption(Types.ORIENTATION, Types.ORIENTATIONS[random.nextInt(Types.ORIENTATIONS.length)]);
		
		Parser.setAvatar(this);
	}

}
