package itu.gamecreator.sprite;

import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;

public class Passive extends Sprite {
	private static final String[] possibleIMGs = {"missile","bomb","bullet","alien",
		"base","bee","boulder","box","butterfly",
		"camel","carcass","city","cocoon","diamond","dirt",
		"door","explosion","flower","forest","goal","hell",
		"hole","honey","key","log","mana","monster","portal",
		"spaceship","sword","truck","virus","wall","water","zombie"};	
	
	public Passive() {
		super("ID"+Parser.getAndIncreaseCurrentID(), "Passive", null);
	}

	public Passive(Sprite father) {
		super("ID"+Parser.getAndIncreaseCurrentID(), "Passive", father);
	}

	/**
	 * Mandatory:
	 * option_sprite_img 
	 * Optional:
	 * option_sprite_color option_sprite_singleton option_sprite_speed option_sprite_cooldown
	 * option_sprite_shrink option_sprite_orientation physicstype
	 * 
	 */
	@Override
	public void build() {
		super.build();
		Random random = Parser.random;
		
		//Mandatory
		addOption(Types.IMG, possibleIMGs[random.nextInt(possibleIMGs.length)]);
		
		//Optional
		if(random.nextBoolean())
			addOption(Types.SINGLETON,(random.nextBoolean() ? "True" : "False"));

		if(random.nextBoolean())
			addOption(Types.COLOR, Types.COLORS[random.nextInt(Types.COLORS.length)]);

		if(random.nextBoolean())
			addOption(Types.SPEED, ""+(random.nextInt(200) + 0.01)/100);

		if(random.nextBoolean())
			addOption(Types.COOLDOWN, ""+random.nextInt(5));

		if(random.nextBoolean())
			addOption(Types.SHRINKFACTOR, ""+random.nextDouble());

		if(random.nextBoolean())
			addOption(Types.ORIENTATION, Types.ORIENTATIONS[random.nextInt(Types.ORIENTATIONS.length)]);

	}
}
