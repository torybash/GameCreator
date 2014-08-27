package itu.gamecreator.test;

import itu.gamecreator.creator.Parser;

public class GameChanger {
	public static void main(String[] args) {
		Parser p = new Parser("Rules/Rules.txt", "Rules/terminalsymbols.txt");
		for(int i = 0; i < 10000; i++) {
			p.createGame();
			System.out.println("\n");
		}
	}
}
