package itu.gamecreator.sprite.producers;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Conveyor;
import itu.gamecreator.sprite.Door;
import itu.gamecreator.sprite.Flicker;
import itu.gamecreator.sprite.Immovable;
import itu.gamecreator.sprite.OrientedFlicker;
import itu.gamecreator.sprite.Passive;
import itu.gamecreator.sprite.Types;
import itu.gamecreator.sprite.avatar.AimedAvatar;
import itu.gamecreator.sprite.avatar.AimedFlakAvatar;
import itu.gamecreator.sprite.avatar.FlakAvatar;
import itu.gamecreator.sprite.avatar.HorizontalAvatar;
import itu.gamecreator.sprite.avatar.InertialAvatar;
import itu.gamecreator.sprite.avatar.MarioAvatar;
import itu.gamecreator.sprite.avatar.MovingAvatar;
import itu.gamecreator.sprite.avatar.NoisyRotatingFlippingAvatar;
import itu.gamecreator.sprite.avatar.OrientedAvatar;
import itu.gamecreator.sprite.avatar.RotatingAvatar;
import itu.gamecreator.sprite.avatar.RotatingFlippingAvatar;
import itu.gamecreator.sprite.avatar.ShootAvatar;
import itu.gamecreator.sprite.avatar.VerticalAvatar;
import itu.gamecreator.sprite.missile.ErraticMissile;
import itu.gamecreator.sprite.missile.Missile;
import itu.gamecreator.sprite.missile.RandomMissile;
import itu.gamecreator.sprite.missile.Walker;
import itu.gamecreator.sprite.missile.WalkerJumper;
import itu.gamecreator.sprite.npc.Chaser;
import itu.gamecreator.sprite.npc.Fleeing;
import itu.gamecreator.sprite.npc.RandomAltChaser;
import itu.gamecreator.sprite.npc.RandomInertial;
import itu.gamecreator.sprite.npc.RandomNPC;
import itu.gamecreator.sprite.resource.Resource;
import itu.gamecreator.sprite.resource.ResourcePack;

import java.util.ArrayList;
import java.util.Random;

public class SpawnPoint extends SpriteProducer {
	protected static final Class [] classes = {Conveyor.class, Door.class, Flicker.class, Immovable.class, OrientedFlicker.class,
		Passive.class, ErraticMissile.class, Missile.class, RandomMissile.class, Walker.class,
		WalkerJumper.class, Chaser.class, Fleeing.class, RandomAltChaser.class, RandomInertial.class, RandomNPC.class, SpawnPoint.class,
		SpriteProducer.class,Bomber.class, Portal.class,Resource.class,ResourcePack.class};

	public SpawnPoint() {
		super(null);
		setSpriteClass("SpawnPoint");
	}

	public SpawnPoint(Sprite father) {
		super(father);
		setSpriteClass("SpawnPoint");
	}

	@Override
	public void build() {
		super.build();

		Random random = Parser.random;

		ArrayList<Sprite> sprites = Parser.getSprites();
		if(sprites.size() == 1) {
			//createRandom
			Object s;
			try {
				s = (classes[random.nextInt(classes.length)].getConstructor(null).newInstance(null));
				((Sprite)s).build();
				Parser.getProducers().put((String)this.getName(),(Sprite) s);
				
				addOption(Types.STYPE, ((Sprite)s).getName());	
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			int s1 = (random.nextInt(sprites.size()));
			do {
				s1 = (random.nextInt(sprites.size()));
			} while(sprites.get(s1).equals(Parser.getAvatar()));
			addOption(Types.STYPE, sprites.get(s1).getName());
		}

		//Optional
		if(random.nextBoolean())
			addOption(Types.TOTAL, "" + random.nextInt(20));

		if(random.nextBoolean())
			addOption(Types.COOLDOWN, "" + random.nextInt(10));

		if(random.nextBoolean())
			addOption(Types.PROB, "" + random.nextDouble());

		
	}
}
