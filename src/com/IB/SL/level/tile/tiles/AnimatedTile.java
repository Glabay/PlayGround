package com.IB.SL.level.tile.tiles;

import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;
import com.IB.SL.level.tile.Tile;

public class AnimatedTile extends Tile	{	

	public AnimatedTile(Sprite sprite) {
		super(sprite);
	}

	public static void update() {
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite);
	}

	
}
	