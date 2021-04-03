package de.dome.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.dome.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "yourGame";
		cfg.width = 1920;
		cfg.height = 1080;
		cfg.fullscreen = false;
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
