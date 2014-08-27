package itu.gamecreator.creator;

import itu.gamecreator.interaction.BounceForward;
import itu.gamecreator.interaction.ChangeResouce;
import itu.gamecreator.interaction.CloneSprite;
import itu.gamecreator.interaction.CollectResource;
import itu.gamecreator.interaction.FlipDirection;
import itu.gamecreator.interaction.Interaction;
import itu.gamecreator.interaction.KillIfFromAbove;
import itu.gamecreator.interaction.KillIfHasLess;
import itu.gamecreator.interaction.KillIfHasMore;
import itu.gamecreator.interaction.KillIfOtherHasMore;
import itu.gamecreator.interaction.KillSprite;
import itu.gamecreator.interaction.PullWithIt;
import itu.gamecreator.interaction.ReverseDirection;
import itu.gamecreator.interaction.SpawnIfHasMore;
import itu.gamecreator.interaction.StepBack;
import itu.gamecreator.interaction.TeleportToExit;
import itu.gamecreator.interaction.TransformTo;
import itu.gamecreator.interaction.TurnAround;
import itu.gamecreator.interaction.UndoAll;
import itu.gamecreator.interaction.WallStop;
import itu.gamecreator.interaction.WrapAround;
import itu.gamecreator.sprite.Conveyor;
import itu.gamecreator.sprite.Door;
import itu.gamecreator.sprite.Flicker;
import itu.gamecreator.sprite.Immovable;
import itu.gamecreator.sprite.OrientedFlicker;
import itu.gamecreator.sprite.Passive;
import itu.gamecreator.sprite.Spreader;
import itu.gamecreator.sprite.Wall;
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
import itu.gamecreator.sprite.npc.AlternateChaser;
import itu.gamecreator.sprite.npc.Chaser;
import itu.gamecreator.sprite.npc.Fleeing;
import itu.gamecreator.sprite.npc.RandomAltChaser;
import itu.gamecreator.sprite.npc.RandomInertial;
import itu.gamecreator.sprite.npc.RandomNPC;
import itu.gamecreator.sprite.producers.Bomber;
import itu.gamecreator.sprite.producers.Portal;
import itu.gamecreator.sprite.producers.SpawnPoint;
import itu.gamecreator.sprite.producers.SpriteProducer;
import itu.gamecreator.sprite.resource.Resource;
import itu.gamecreator.sprite.resource.ResourcePack;
import itu.gamecreator.termination.MultiSpriteCounter;
import itu.gamecreator.termination.SpriteCounter;
import itu.gamecreator.termination.Termination;
import itu.gamecreator.termination.Timeout;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Parser {
	private final Class [] possibleAvatarClass = {AimedAvatar.class, AimedFlakAvatar.class, FlakAvatar.class,
			HorizontalAvatar.class, InertialAvatar.class, MarioAvatar.class, MovingAvatar.class,
			NoisyRotatingFlippingAvatar.class, OrientedAvatar.class,
			RotatingFlippingAvatar.class,ShootAvatar.class, VerticalAvatar.class};
	private final Class[] avatarSubClass = {RotatingAvatar.class};

	private final Class [] interactionClass = {BounceForward.class, ChangeResouce.class, CloneSprite.class,
			CollectResource.class, FlipDirection.class, KillIfFromAbove.class, KillIfHasLess.class,
			KillIfHasMore.class, KillIfOtherHasMore.class, KillSprite.class,
			PullWithIt.class,ReverseDirection.class, SpawnIfHasMore.class,
			StepBack.class, TransformTo.class, TurnAround.class,
			UndoAll.class, WallStop.class, WrapAround.class};

	private Class [] terminationClass = {SpriteCounter.class, MultiSpriteCounter.class, Timeout.class};

	private ArrayList<Class> spriteClasses = null;

	public static Random random;

	private static ArrayList<Missile> missiles;
	private static ArrayList<Resource> resources;
	private static Sprite avatar;
	private static ArrayList<Sprite> sprites;
	private static Sprite wall;
	private static int currentID;
	private static HashMap<String, Sprite> producers;
	private ArrayList<Interaction> interactions;
	private ArrayList<Termination> terminations;
	private String stringMap;

	public Parser(String rulesFile, String terminalFile) {
		init(rulesFile, terminalFile);
	}

	public void createGame() {

		missiles = new ArrayList<Missile>();
		resources = new ArrayList<Resource>();
		sprites = new ArrayList<Sprite>();
		interactions = new ArrayList<Interaction>();
		terminations = new ArrayList<Termination>();
		producers = new HashMap<String, Sprite> ();
		
		currentID = 0;
		avatar = null;
		wall = null;

		createSprites();
		createLevelMapping();
		createInteraction();
		createTermination();

		createMap();

		createGameOutput();
	}

	private void createMap() {
		final int width = 15, heigth = 15;
		char[][] map = new char[heigth][width];

		char EMPTY = ' ';

		for(int i = 0; i < heigth; i++)
			for(int j = 0; j < width; j++)
				map[i][j] = EMPTY;

		//upper and lower
		for(int i = 0; i < width; i++) {
			map[0][i] = wall.getLevelMapping();
			map[heigth-1][i] = wall.getLevelMapping();
		}

		//left and rigth
		for(int i = 0; i < heigth; i++) {
			map[i][0] = wall.getLevelMapping();
			map[i][width-1] = wall.getLevelMapping();
		}

		int x,y;
		for(int i = 0; i < sprites.size(); i++) {
			do {
				x = random.nextInt(width-1)+1;
				y = random.nextInt(heigth-1)+1;
			} while(map[y][x] != EMPTY);
			map[y][x] = sprites.get(i).getLevelMapping();
		}

		stringMap = "";
		for(int i = 0; i < heigth; i++) {
			for(int j = 0; j < width; j++)
				stringMap += map[i][j];
			stringMap += "\n";
		}

	}

	private void saveGame(String output) {
		File directory = new File("output");
		final int latest = (directory.listFiles().length/2) + 1;

		FileWriter fw;
		try {
			fw = new FileWriter("output/game "+latest + ".txt");

			fw.write(output);

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fw = new FileWriter("output/game "+latest + "_lvl0.txt");

			fw.write(stringMap);

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void init(String rulesFile, String terminalFile) {
		random = new Random();
		missiles = new ArrayList<Missile>();
		resources = new ArrayList<Resource>();
		sprites = new ArrayList<Sprite>();
		interactions = new ArrayList<Interaction>();
		terminations = new ArrayList<Termination>();

		spriteClasses = new ArrayList<Class>();
		Class[] auxiliar = {Conveyor.class, Door.class, Flicker.class,
				Immovable.class, OrientedFlicker.class, Passive.class, ErraticMissile.class,
				Missile.class, RandomMissile.class, Walker.class,
				WalkerJumper.class,AlternateChaser.class, Chaser.class,
				Fleeing.class, RandomAltChaser.class, RandomInertial.class, RandomNPC.class,
				Bomber.class, Portal.class, SpawnPoint.class, SpriteProducer.class, Resource.class, Spreader.class};
		for(int i = 0; i < auxiliar.length; i++)
			spriteClasses.add(auxiliar[i]);

		avatar = null;
		wall = null;
	}

	private void createInteraction() {
		int quantity = random.nextInt(10) + 3;

		// So the sprites won't pass through walls
		for(int i = 0; i < sprites.size(); i++) {
			StepBack interaction = new StepBack();
			interaction.build(sprites.get(i),wall);
			interactions.add(interaction);
		}

		for(int i = 0; i < quantity; i++) {
			boolean result = true;
			int chosen = random.nextInt(interactionClass.length);
			try {
				Interaction interaction = (Interaction) interactionClass[chosen].getConstructor(new Class[]{}).
						newInstance(new Object[]{});
				result = interaction.build();
				if(result)
					interactions.add(interaction);
				else
					i--;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void createLevelMapping() {
		final String characters = "bcdefghijklmnopqrstuvwxyzBCDEFGHIJKLMNOPQRSTUVWXYZ1234567890+=!�%&/()=?";
		int currectChar = 0;

		avatar.setLevelMapping('A');

		for(int i = 0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if(!sprite.equals(avatar)) {
				sprite.setLevelMapping(characters.charAt(currectChar++));
			}
		}
	}


	public void createTermination() {
		int quantity = random.nextInt(3);

		createNewTermination().setWinnable(true);

		createNewTermination().setWinnable(true);
	}


	private Termination createNewTermination() {
		int chosen = random.nextInt(terminationClass.length);
		try {
			Termination termination = (Termination) terminationClass[chosen].getConstructor(new Class[]{}).
					newInstance(new Object[]{});
			termination.build();
			terminations.add(termination);
			return termination;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	private void createSprites() {
		//Create avatar

		final int chosenAvatar = random.nextInt(possibleAvatarClass.length);
		try {
			avatar = (Sprite) possibleAvatarClass[chosenAvatar].getConstructor(new Class[]{}).newInstance(new Object[]{});
			avatar.build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		wall = new Wall();

		boolean [][] compMatrix = new boolean[spriteClasses.size()][spriteClasses.size()];
		for(int i = 0; i < spriteClasses.size(); i++) {
			for(int j = 0; j < spriteClasses.size(); j++) {
				compMatrix[i][j] = true;
			}	
		}

		for(int i = 0; i < spriteClasses.size(); i++)
			compMatrix[i][i] = false;

		compMatrix[spriteClasses.indexOf(Passive.class)][spriteClasses.indexOf(RandomAltChaser.class)] = false;
		compMatrix[spriteClasses.indexOf(RandomNPC.class)][spriteClasses.indexOf(Spreader.class)] = false;

		//create other sprites
		int quantity = random.nextInt(3)+2;		
		for(int i = 0; i < quantity; i++) {
			int chosen = random.nextInt(spriteClasses.size());
			try {
				Sprite sprite = (Sprite) spriteClasses.get(chosen).getConstructor(new Class[]{}).
						newInstance(new Object[]{});
				sprite.build();

				Sprite previous = sprite;
				int prof = 0;
				boolean mustGoOn = random.nextBoolean();
				if(sprite.getClass().equals(Spreader.class))
					mustGoOn = true;
				while(mustGoOn && prof < 2) {
					prof++;
					Sprite sprite1;

					int previousPosition = spriteClasses.indexOf(previous.getClass());
					do {
						chosen = random.nextInt(spriteClasses.size());
					} while(!compMatrix	[previousPosition][chosen]);

					sprite1 = (Sprite) spriteClasses.get(chosen).getConstructor(new Class[]{}).
							newInstance(new Object[]{});

					sprite1.build();

					sprite1.setFather(previous);
					previous.addChildren(sprite1);

					previous = sprite1;

					mustGoOn = random.nextBoolean();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Print game as VGDL description
	 */
	public void createGameOutput() {
		String result = "BasicGame\n";
		result += "\tSpriteSet\n";
		String previous = "\t\t";
		for(int i = 0; i< sprites.size() ;i++) {
			if(sprites.get(i).getFather() == null)
				result += previous + sprites.get(i).getSprite() + "\n";
		}

		result += "\tLevelMapping\n";
		for(int i = 0; i< sprites.size(); i++) {
			result += previous + sprites.get(i).getLevelMapping() + " > " + sprites.get(i).getName() + "\n";
		}

		result += "\tInteractionSet\n";
		for(int i = 0; i < interactions.size(); i++) {
			result += previous + interactions.get(i).getInteractionString() + "\n";
		}

		result += "\tTerminationSet\n";
		for(int i = 0; i < terminations.size(); i++)
			result += previous + terminations.get(i).getTerminationString() + "\n";	
		
		result += "\tMap\n";
		//for(int i = 0; i < terminations.size(); i++)
		result += stringMap + "\n";	

		System.out.println(result);
		saveGame(result);
	}

	public static void addMissile(Missile missile) {
		missiles.add(missile);
	}

	public static void addResource(Resource resource) {
		resources.add(resource);		
	}

	public static void setAvatar(Sprite avatar) {
		Parser.avatar = avatar;
	}

	public static ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public static void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}

	public static Sprite getAvatar() {
		return avatar;
	}

	public static ArrayList<Resource> getResources() {
		return resources;
	}

	public static Sprite getWall() {
		return wall;
	}

	public static int getAndIncreaseCurrentID() {
		return currentID++;
	}

	public static HashMap<String,Sprite> getProducers() {
		return producers;
	}
}
