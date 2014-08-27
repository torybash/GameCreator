package itu.gamecreator.sprite.missile;

import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;

public class Missile extends Sprite {

	public Missile() {
		super("ID"+Parser.getAndIncreaseCurrentID(), "Missile", null);
	}

	public Missile(Sprite father) {
		super("ID"+Parser.getAndIncreaseCurrentID(), "Missile", father);
	}

	@Override
	/**
	 * Mandatory:
	 * option_sprite_img
	 * option_sprite_color  option_sprite_singleton option_sprite_speed option_sprite_cooldown 
	 * option_sprite_shrink option_sprite_orientation
	 */
	public void build() {
		super.build();
		Random random = Parser.random;
		
		//Mandatory
		String missileImg;
		final int missileImgType = random.nextInt(3);
		switch(missileImgType) {
		case 0: default:
			missileImg = "missile";
			break;
		case 1: 
			missileImg = "bomb";
			break;
		case 2:
			missileImg = "bullet";
			break;
		}
		addOption(Types.IMG, missileImg);
		
		//Optional
		if(random.nextBoolean())
			addOption(Types.SINGLETON,(random.nextBoolean() ? "True" : "False"));

		if(random.nextBoolean())
			addOption(Types.COLOR, Types.COLORS[random.nextInt(Types.COLORS.length)]);

		if(random.nextBoolean())
			addOption(Types.SPEED, ""+(random.nextInt(200) + 0.01)/100);

		if(random.nextBoolean())
			addOption(Types.COOLDOWN, ""+random.nextInt(10));

		if(random.nextBoolean())
			addOption(Types.SHRINKFACTOR, ""+random.nextDouble());

		if(random.nextBoolean())
			addOption(Types.ORIENTATION, Types.ORIENTATIONS[random.nextInt(Types.ORIENTATIONS.length)]);
		
		Parser.addMissile(this);
	}

}
