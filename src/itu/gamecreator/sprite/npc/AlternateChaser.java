package itu.gamecreator.sprite.npc;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.*;
import itu.gamecreator.sprite.avatar.*;
import itu.gamecreator.sprite.missile.*;
import itu.gamecreator.sprite.producers.*;
import itu.gamecreator.sprite.resource.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;



public class AlternateChaser extends RandomNPC {
	private static final Class [] classes = {Conveyor.class, Door.class, Flicker.class, Immovable.class, OrientedFlicker.class,
		Passive.class, AimedAvatar.class, AimedFlakAvatar.class, FlakAvatar.class,
		HorizontalAvatar.class, InertialAvatar.class, MarioAvatar.class, MovingAvatar.class,
		NoisyRotatingFlippingAvatar.class, OrientedAvatar.class, RotatingAvatar.class, RotatingFlippingAvatar.class,
		ShootAvatar.class, VerticalAvatar.class,ErraticMissile.class, Missile.class, RandomMissile.class, Walker.class,
		WalkerJumper.class, Chaser.class, Fleeing.class, RandomAltChaser.class, RandomInertial.class, RandomNPC.class, SpawnPoint.class,
		SpriteProducer.class,Bomber.class, Portal.class,Resource.class,ResourcePack.class};
	
	public AlternateChaser() {
		super(null);
		setSpriteClass("AlternateChaser");
	}

	public AlternateChaser(Sprite father) {
		super(father);
		setSpriteClass("AlternateChaser");
	}

	@Override
	public void build() {
		super.build();

		Random random = Parser.random;
		
		ArrayList<Sprite> sprites = Parser.getSprites();
		if(sprites.size() == 1) {
			addOption(Types.STYPE_1, sprites.get(0).getName());
			
			//createRandom
			Object s;
			try {
				s = (classes[random.nextInt(classes.length)].getConstructor(null).newInstance(null));
				((Sprite)s).build();
				
				addOption(Types.STYPE_2, ((Sprite)s).getName());	
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		} else {
			int s1, s2;
			s1 = (random.nextInt(sprites.size()));
			do {
				s2 = (random.nextInt(sprites.size()));
			} while(s1 == s2);
			addOption(Types.STYPE_1, sprites.get(s1).getName());
			addOption(Types.STYPE_2, sprites.get(s2).getName());
		}

		if(random.nextBoolean())
			addOption(Types.FLEEING, (random.nextBoolean() ? "True" : "False"));
	}
}