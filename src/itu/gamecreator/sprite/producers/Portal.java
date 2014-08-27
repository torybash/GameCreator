package itu.gamecreator.sprite.producers;

import java.util.ArrayList;
import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Conveyor;
import itu.gamecreator.sprite.Door;
import itu.gamecreator.sprite.Flicker;
import itu.gamecreator.sprite.Immovable;
import itu.gamecreator.sprite.OrientedFlicker;
import itu.gamecreator.sprite.Passive;
import itu.gamecreator.sprite.Types;
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

public class Portal extends SpriteProducer {
	protected static final Class [] classes = {Conveyor.class, Door.class, Flicker.class, Immovable.class, OrientedFlicker.class,
		Passive.class, ErraticMissile.class, Missile.class, RandomMissile.class, Walker.class,
		WalkerJumper.class, Chaser.class, Fleeing.class, RandomAltChaser.class, RandomInertial.class, RandomNPC.class, SpawnPoint.class,
		SpriteProducer.class,Bomber.class, Portal.class,Resource.class,ResourcePack.class};
	
	public Portal() {
		super(null);
		setSpriteClass("Portal");
	}

	public Portal(Sprite father) {
		super(father);
		setSpriteClass("Portal");
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
	}
}
