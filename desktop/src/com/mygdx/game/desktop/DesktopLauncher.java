package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.main.UnexpectedMagic;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Unexpected Magic";
		config.addIcon("images/Unexpected_Magic_Logo2-sqr.png", Files.FileType.Internal);
		new LwjglApplication(new UnexpectedMagic(), config);

	}
}
