package itu.gamecreator.termination;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;

import java.util.ArrayList;
import java.util.Random;

public class Timeout extends Termination {

	public Timeout() {
		super("Timeout");
	}

	public void build() {
		Random random = Parser.random;
		
		addOption(TermType.LIMIT, "" + (1000+random.nextInt(1000)));
	}
}
