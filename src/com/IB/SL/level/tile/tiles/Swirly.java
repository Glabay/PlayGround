package com.IB.SL.level.tile.tiles;

import com.IB.SL.Game;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;
import com.IB.SL.level.tile.Tile;

public class Swirly extends Tile {

	public Swirly(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << Game.TILE_BIT_SHIFT, y << Game.TILE_BIT_SHIFT, swirly);
		screen.blendTiles(x << Game.TILE_BIT_SHIFT, y << Game.TILE_BIT_SHIFT, swirly, swirly);
	}
	
		public boolean solid() {
			return false;
	}
		public boolean solidtwo() {
			return false;
		}
		
		public boolean exit() {
			return true;
		}
		
	}
