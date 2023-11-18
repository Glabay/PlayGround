package com.IB.SL.util;

import java.io.File;
import java.io.Serializable;

import com.IB.SL.Game;
import com.IB.SL.level.TileCoord;

public class LoadProperties implements Serializable {

	private static final long serialVersionUID = -3797769650538590454L;
	
	public transient static String root = "/SquareLegacy";
	public transient static String SystemUsername = System.getProperty("user.name");
	public transient static String basePath = null;

	public boolean multienabled = false;
	public boolean autoSave = true;
	public int psX = 52, psY = 72;

	public void savePrefs(Game game) {
		autoSave = game.autoSave;
		psX = game.playerRespawn.x();
		psY = game.playerRespawn.y();

		SaveGame.save(this, SaveGame.prefsFileName);
	}

	public void loadPrefs(Game game) {
		LoadProperties temp = SaveGame.loadPrefs();

		game.autoSave = temp.autoSave;
		autoSave = game.autoSave;
		game.playerRespawn = new TileCoord(temp.psX / 16, temp.psY / 16);
		psX = game.playerRespawn.x();
		psY = game.playerRespawn.y();
	}

	public static void createDataFolder() {
		if (basePath == null) {
			String home = System.getProperty("user.home");
			String OS = System.getProperty("os.name").toLowerCase();

			if (OS.contains("win")) {
				home = System.getenv("appdata");
			} else if (OS.contains("mac")) {
				home += "~/Applications/";
			} else if (OS.contains("nix") || OS.contains("aix")) {
				home += "~/.";
			}

			File dir = new File(home);
			dir = new File(dir, root);

			if (!dir.exists()) {
				dir.mkdir();
				System.out.println("Creating directory");

			}

			basePath = dir.getAbsolutePath();
		}
	}

}
