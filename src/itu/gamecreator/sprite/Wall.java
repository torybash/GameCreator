package itu.gamecreator.sprite;

import itu.gamecreator.creator.Sprite;

public class Wall extends Sprite {

	public Wall() {
		super("wall", "Wall", null);
		setLevelMapping('w');
	}
	
	@Override
	public void build() {
	}

}
