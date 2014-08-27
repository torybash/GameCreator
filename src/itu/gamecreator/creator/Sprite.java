package itu.gamecreator.creator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import tools.Pair;

public abstract class Sprite {
	private String name;
	private String spriteClass;
	private HashMap<String,String> options;
	private ArrayList<Sprite> children;
	private Sprite father;
	private char levelMapping;
	
	public Sprite(String name, String spriteClass, Sprite father) {
		this.name = name;
		this.spriteClass = spriteClass;
		this.father = father;
		options = new HashMap<String, String>();
		children = new ArrayList<>();
	}
	
	public void addOption(String name, String value) {
		options.put(name, value);
	}
	
	public void removeOption(String name) {
		options.remove(name);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSpriteClass() {
		return spriteClass;
	}
	
	public void setSpriteClass(String spriteClass) {
		this.spriteClass = spriteClass;
	}
	
	public Sprite getFather() {
		return father;
	}
	
	public void setFather(Sprite father) {
		this.father = father;
	}
	
	public HashMap<String, String> getOptions() {
		return options;
	}
	
	public ArrayList<Sprite> getChildren() {
		return children;
	}
	
	public void addChildren(Sprite sprite) {
		children.add(sprite);
	}
	
	public void removeChildren(Sprite sprite) {
		children.remove(sprite);
	}
	
	public void build() {
		Parser.addSprite(this);
	}
	
	public void setLevelMapping(char levelMapping) {
		this.levelMapping = levelMapping;
	}
	
	public char getLevelMapping() {
		return levelMapping;
	}
	
	
	public String getSprite() {
		String result = name + " > " + spriteClass;
		Iterator<String> it = options.keySet().iterator();
		while(it.hasNext()) {
			String key = (String)it.next();
			result += " " + key + "=" + options.get(key);
		}
		for(int i = 0; i < children.size(); i++) {
			result += "\n" + children.get(i).getSprite(3);
		}
		return result;
	}
	
	public String getSprite(int prof) {
		String result = "";
		
		for(int i = 0; i < prof; i++)
			result+= "\t";
		
		result += name + " > " + spriteClass;
		Iterator<String> it = options.keySet().iterator();
		while(it.hasNext()) {
			String key = (String)it.next();
			result += " " + key + "=" + options.get(key);
		}
		for(int i = 0; i < children.size(); i++) {
			result += "\n" + children.get(i).getSprite(prof+1);
		}
		return result;
	}
}
