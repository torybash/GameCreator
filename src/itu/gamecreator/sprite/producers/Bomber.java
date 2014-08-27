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

public class Bomber extends SpawnPoint {
	protected static final Class [] classes = {ErraticMissile.class, Missile.class, RandomMissile.class, Walker.class,
		WalkerJumper.class};
	
	public Bomber() {
		super(null);
		setSpriteClass("Bomber");
	}

	public Bomber(Sprite father) {
		super(father);
		setSpriteClass("Bomber");
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
			addOption(Types.ORIENTATION, Types.ORIENTATIONS[random.nextInt(Types.ORIENTATIONS.length)]);

	}

}
