package itu.gamecreator.termination;

import itu.gamecreator.creator.Parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public abstract class Termination {
	private String name;
	private HashMap<String,String> options;
	
	public Termination(String name) {
		this.name = name;
		options = new HashMap<String,String>();
	}

	public void build() {
		Random random = Parser.random;
		
		options.put(TermType.LIMIT, "" + random.nextInt(10));
		options.put(TermType.WIN, (random.nextBoolean() ? "True" : "False"));
	}
	
	public void setWinnable(boolean winnable) {
		options.put(TermType.WIN, (winnable ? "True" : "False"));
	}
	
	public void addOption(String key, String value) {
		options.put(key,value);
	}
	
	public String getTerminationString() {
		String result = name;
		Iterator<String> it = options.keySet().iterator();
		while(it.hasNext()) {
			String key = (String)it.next();
			result += " " + key + "=" + options.get(key);
		}
		return result;
	}
}
