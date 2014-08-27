package itu.gamecreator.interaction;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public abstract class Interaction {
	private String interaction;
	private HashMap<String, String> options;
	private Sprite sprite1;
	private Sprite sprite2;
	
	public Interaction(String interaction) {
		options = new HashMap<String,String>();
		this.interaction = interaction;
	}
	
	public String getInteraction() {
		return interaction;
	}
	
	public HashMap<String, String> getOptions() {
		return options;
	}
	
	public void addOption(String key, String value) {
		options.put(key, value);
	}
	
	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}
	
	public void setSprite2(Sprite sprite2) {
		this.sprite2 = sprite2;
	}
	
	public void setSprite1(Sprite sprite1) {
		this.sprite1 = sprite1;
	}
	
	public boolean build() {
		Random random = Parser.random;
		if(random.nextBoolean())
			options.put(IntType.SCORE_CHANGE, "" + (random.nextInt(10) - 5));
		
		return true;
	}
	
	public String getInteractionString() {
		String result = sprite1.getName() + " " + sprite2.getName() + " > " +
				interaction;
		Iterator<String> it = options.keySet().iterator();
		while(it.hasNext()) {
			String key = (String)it.next();
			result += " " + key + "=" + options.get(key);
		}
		return result;
	}
	
}
