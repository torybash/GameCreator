package itu.gamecreator.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import itu.gamecreator.creator.Parser;
import itu.gamecreator.creator.Sprite;
import itu.gamecreator.sprite.Types;
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
import itu.gamecreator.sprite.npc.RandomNPC;
import core.ArcadeMachine;
import core.VGDLFactory;
import core.content.SpriteContent;

public class Test {
	public static void main(String[] args) {
		Parser p = new Parser("Rules/Rules.txt", "Rules/terminalsymbols.txt");
		for(int i = 0; i < 100; i++) {
			p.createGame();
			System.out.println("\n");
		}
	}
	
	
}