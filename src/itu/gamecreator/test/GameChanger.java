package itu.gamecreator.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import ontology.Types;
import ontology.effects.Effect;
import tools.IO;
import tools.Pair;
import core.Node;
import core.VGDLFactory;
import core.VGDLRegistry;
import core.content.InteractionContent;
import core.content.MappingContent;
import core.content.SpriteContent;
import core.content.TerminationContent;
import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.interaction.Interaction;
import itu.gamecreator.sprite.Conveyor;
import itu.gamecreator.sprite.Door;
import itu.gamecreator.sprite.Flicker;
import itu.gamecreator.sprite.Immovable;
import itu.gamecreator.sprite.OrientedFlicker;
import itu.gamecreator.sprite.Passive;
import itu.gamecreator.sprite.Spreader;
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
import itu.gamecreator.termination.Termination;

public class GameChanger {
	public static void main(String[] args) {
		GameChanger gc = new GameChanger();

		gc.readGameOutput("output/frogs.txt");
		gc.IterateGame(100);
	}
	
	
	public int currentSet;
	
	
	ArrayList<SpriteContent> spriteContent = new ArrayList<SpriteContent>();
	ArrayList<TerminationContent> terminationContent = new ArrayList<TerminationContent>();
	ArrayList<InteractionContent> interactionContent = new ArrayList<InteractionContent>();
	ArrayList<MappingContent> mappingContent = new ArrayList<MappingContent>();
	
	public void readGameOutput(String gamedesc_file) {
		
    	for(int i = 0; i < auxiliar.length; i++)
    		spriteClasses.add(auxiliar[i]);
    	for(int i = 0; i < possibleAvatarClass.length; i++)
    		spriteClasses.add(possibleAvatarClass[i]);
    	for(int i = 0; i < avatarSubClass.length; i++)
    		spriteClasses.add(avatarSubClass[i]);
		
		
		String[] desc_lines = new IO().readFile(gamedesc_file);
		
        if(desc_lines != null)
        {        	
            Node rootNode = indentTreeParser(desc_lines);

            //Parse here game and arguments of the first line
            //game = VGDLFactory.GetInstance().createGame((GameContent) rootNode.content);

            //Parse here blocks of VGDL.
            for(Node n : rootNode.children)
            {
                if(n.content.identifier.equals("SpriteSet"))
                {
                    parseSpriteSet(n.children, 0, null);
                    System.out.println(sprites);
                }else if(n.content.identifier.equals("InteractionSet"))
                {
                    parseInteractionSet(n.children);
                }else if(n.content.identifier.equals("LevelMapping"))
                {
                    parseLevelMapping(n.children);
                }else if(n.content.identifier.equals("TerminationSet"))
                {
                    parseTerminationSet(n.children);
                }
            }
        }
        
        
        
        
        
//        for (SpriteContent sr : spriteContent) {
//			System.out.println(sr.identifier);
//			System.out.println(sr.referenceClass);
//			for (String key : sr.parameters.keySet()) {
//				String val = sr.parameters.get(key);
//				System.out.println("key: " + key + ", val: " + val);
//			}
//		}
        
	}
	
	
	public void IterateGame(int number){
		ArrayList<SpriteContent> sprites = (ArrayList<SpriteContent>) spriteContent.clone();
		ArrayList<TerminationContent> terms = (ArrayList<TerminationContent>) terminationContent.clone();
		ArrayList<InteractionContent> interacts = (ArrayList<InteractionContent>) interactionContent.clone();
		ArrayList<MappingContent> mappings = (ArrayList<MappingContent>) mappingContent.clone();
		
		
      for (SpriteContent sr : sprites) {
			for (String key : sr.parameters.keySet()) {
				String val = sr.parameters.get(key);
				
				double d = 0;
				try  
				  {  
				    d = Double.parseDouble(val);  
				  }  
				  catch(NumberFormatException nfe)  
				  {  
				    continue;
				  }  
				 sr.parameters.put(key, "0.1");
			}
		}
		
      
      createGameOutput(new ArrayList[]{sprites, terms, interacts, mappings});
      
	}
	
	
	public void createGameOutput(ArrayList[] content) {
		ArrayList<SpriteContent> sprites = content[0];
		ArrayList<TerminationContent> terms = content[1];
		ArrayList<InteractionContent> interacts = content[2];
		ArrayList<MappingContent> mappings = content[3];
		
		String result = "BasicGame\n";
		result += "\tSpriteSet\n";
		String previous = "\t\t";
		
		int subElement = 0;
		for(int i = 0; i< sprites.size() ;i++) {
			result += previous + getSpriteText(sprites.get(i), subElement) + "\n";
			if (sprites.get(i).is_definition) subElement += 1;
			else if (!sprites.get(i).is_definition && subElement == 1) subElement = 0;
		}

//		result += "\tLevelMapping\n";
//		for(int i = 0; i< sprites.size(); i++) {
//			result += previous + sprites.get(i).getLevelMapping() + " > " + sprites.get(i).getName() + "\n";
//		}
//
//		result += "\tInteractionSet\n";
//		for(int i = 0; i < interactions.size(); i++) {
//			result += previous + interactions.get(i).getInteractionString() + "\n";
//		}
//
//		result += "\tTerminationSet\n";
//		for(int i = 0; i < terminations.size(); i++)
//			result += previous + terminations.get(i).getTerminationString() + "\n";	

		System.out.println(result);
//		saveGame(result);
	}
	
	
	
	
	public String getSpriteText(SpriteContent sr, int tabs) {
		String result = "";
		for (int i = 0; i < tabs; i++) {
			result += "\t";
		}
		result += sr.identifier + " > " + sr.referenceClass;
		Iterator<String> it = sr.parameters.keySet().iterator();
		while(it.hasNext()) {
			String key = (String)it.next();
			result += " " + key + "=" + sr.parameters.get(key);
		}
		return result;
	}
	

	
	ArrayList<Class> spriteClasses = new ArrayList<Class>();
	private final Class[] auxiliar = {Conveyor.class, Door.class, Flicker.class,
			Immovable.class, OrientedFlicker.class, Passive.class, ErraticMissile.class,
			Missile.class, RandomMissile.class, Walker.class,
			WalkerJumper.class,AlternateChaser.class, Chaser.class,
			Fleeing.class, RandomAltChaser.class, RandomInertial.class, RandomNPC.class,
			Bomber.class, Portal.class, SpawnPoint.class, SpriteProducer.class, Resource.class, Spreader.class};
	private final Class [] possibleAvatarClass = {AimedAvatar.class, AimedFlakAvatar.class, FlakAvatar.class,
			HorizontalAvatar.class, InertialAvatar.class, MarioAvatar.class, MovingAvatar.class,
			NoisyRotatingFlippingAvatar.class, OrientedAvatar.class,
			RotatingFlippingAvatar.class,ShootAvatar.class, VerticalAvatar.class};
	private final Class[] avatarSubClass = {RotatingAvatar.class};
	
	
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
    private void parseSpriteSet(ArrayList<Node> elements, int depth, Sprite spriteFather)
    {

    	
    	for(Node el : elements){
    		SpriteContent sc = (SpriteContent) el.content;
    		String refClass = sc.referenceClass;
    		
    		System.out.println(refClass + ", " + depth);
    		
    		Class spriteClass = null;
    		
    		for (Class c: spriteClasses) {
    			System.out.println(refClass + " - " + c.getName().split("\\.")[c.getName().split("\\.").length-1]);
				if (c.getName().split("\\.")[c.getName().split("\\.").length-1].equals(refClass)){
					spriteClass = c;
					break;
				}
			}
    		
    		Sprite sp = null;
    		if (spriteClass != null){
	    		try {
	    			sp = (Sprite)spriteClass.newInstance();
	    			sp.setFather(spriteFather);
					sprites.add(sp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		
    		
    		if (el.children.size() > 0){
    			int newDepth = depth + 1;
    			parseSpriteSet(el.children, newDepth, sp);
    		}
    		
    		
//    		SpriteContent sc = (SpriteContent) el.content;
//    		
//    		if (el.children.size() > 0) sc.is_definition = true;
//    		else sc.is_definition = false; 
//    		
//    		spriteContent.add(sc);
//    		
//    		
//    		
//    		for (Node child : el.children) {
//        		SpriteContent scchild = (SpriteContent) child.content;
//        		
//        		
//        		
//        		if (child.children.size() > 0) scchild.is_definition = true; 
//        		else scchild.is_definition = false; 
//        		
//        		spriteContent.add(scchild);
//        		
//        		
//        		for (Node grandChild : child.children) {
//            		SpriteContent scgchild = (SpriteContent) grandChild.content;
//            		
//            		
//            		
//            		scgchild.is_definition = false;
//            		
//            		spriteContent.add(scgchild);
//            		
//    			}
//			}
    	}
    	
    	
//        //We need these 2 here:
//        spriteOrderTmp.add(VGDLRegistry.GetInstance().getRegisteredSpriteValue("wall"));
//        spriteOrderTmp.add(VGDLRegistry.GetInstance().getRegisteredSpriteValue("avatar"));
//
//        _parseSprites(elements, null, new HashMap<String, String>(), new ArrayList<String>());
//
//        //Set the order of sprites.
//        game.initSprites(spriteOrderTmp, singletonTmp, constructors);
    }

    /**
     * Recursive method to parse the tree of sprites.
     * @param elements set of sibling nodes
     * @param parentclass String that identifies the class of the parent node. If null, no class defined yet.
     * @param parentargs Map with the arguments of the parent, that are inherited to all its children.
     * @param parenttypes List of types the parent of elements belong to.
     */


    /**
     * Parses the interaction set.
     * @param elements all interactions defined for the game.
     */
    private void parseInteractionSet(ArrayList<Node> elements)
    {
        for(Node n : elements)
        {
            InteractionContent ic = (InteractionContent)n.content;
            interactionContent.add(ic);
        }
    }

    /**
     * Parses the level mapping.
     * @param elements all mapping units.
     */
    private void parseLevelMapping(ArrayList<Node> elements)
    {
        for(Node n : elements)
        {
            MappingContent mc = (MappingContent)n.content;
           mappingContent.add(mc);
        }

    }
	
    /**
     * Parses the termination set.
     * @param elements all terminations defined for the game.
     */
    private void parseTerminationSet(ArrayList<Node> elements)
    {
        for(Node n : elements)
        {
            TerminationContent tc = (TerminationContent)n.content;
            
            terminationContent.add(tc);
        }

    }

	
	
    /**
     * Builds the tree structure that defines the game.
     * @param lines array with the lines read from the game description file.
     * @return the root of the final game tree
     */
    private Node indentTreeParser(String[] lines)
    {
        //By default, let's make tab as four spaces
        String tabTemplate = "    ";
        Node last = null;

        for(String line : lines)
        {
            line.replaceAll("\t",tabTemplate);
            line.replace('(',' ');
            line.replace(')',' ');
            line.replace(',',' ');

            // remove comments starting with "#"
            if(line.contains("#"))
                line = line.split("#")[0];

            // handle whitespace and indentation
            String content = line.trim();

            if(content.length() > 0)
            {            	
                updateSet(content); //Identify the set we are in.
                char firstChar = content.charAt(0);
                //figure out the indent of the line.
                int indent = line.indexOf(firstChar);
                last = new Node(content, indent, last, currentSet);
            }
        }

        return last.getRoot();
    }
    
    /**
     * Updates the set we are in (game-def, spriteset, interactionset, levelmapping, terminationset)
     * @param line line to read
     */
    private void updateSet(String line)
    {
        if(line.equalsIgnoreCase("SpriteSet"))
            currentSet = Types.VGDL_SPRITE_SET;
        if(line.equalsIgnoreCase("InteractionSet"))
            currentSet = Types.VGDL_INTERACTION_SET;
        if(line.equalsIgnoreCase("LevelMapping"))
            currentSet = Types.VGDL_LEVEL_MAPPING;
        if(line.equalsIgnoreCase("TerminationSet"))
            currentSet = Types.VGDL_TERMINATION_SET;
    }
}
