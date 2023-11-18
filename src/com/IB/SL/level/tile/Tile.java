package com.IB.SL.level.tile;

import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;

import com.IB.SL.level.tile.overlays.*;
import com.IB.SL.level.tile.tiles.*;

public class Tile {
	
	public enum stepSound {
		Organic, Hard, Squishy, Water;
	}
	
	public Sprite sprite;
	
	
	
	public static Tile bitBrick = new bitBrick(Sprite.bitBrick);
	public static Tile bitMetal = new bitMetal(Sprite.bitMetal);
	public static Tile Bluefog = new Bluefog(Sprite.Bluefog);
	
	public static Tile BookshelfBottom = new BookshelfBottom(Sprite.BookshelfBottom);
	public static Tile BookshelfTop = new BookshelfTop(Sprite.BookshelfTop);
	public static Tile BrickCeiling = new BrickCeiling(Sprite.BrickCeiling);
	public static Tile BrickWall = new BrickWall(Sprite.BrickWall);
	public static Tile CaveCeiling = new CaveCeiling(Sprite.CaveCeiling);
	public static Tile CaveWall = new CaveWall(Sprite.CaveWall);
	
	public static Tile CobbleStone = new Terrain(Sprite.CobbleStone, stepSound.Hard);
	public static Tile CobblestoneCeiling = new CobblestoneCeiling(Sprite.CobblestoneCeiling);
	
	public static Tile CobbleStoneWall = new CobbleStoneWall(Sprite.CobbleStoneWall);
	public static Tile ColorFlowers = new ColorFlowers(Sprite.ColorFlowers);
	public static Tile CrackedBrick = new CrackedBrick(Sprite.CrackedBrick);
	public static Tile DarkStone = new DarkStone(Sprite.DarkStone);
	
	public static Tile DeepLava = new DeepLava(Sprite.DeepLava);
	public static Tile DeepWater = new DeepWater(Sprite.DeepWater);
	public static Tile Dirt = new Dirt(Sprite.Dirt);
	public static Tile DirtCeiling = new DirtCeiling(Sprite.DirtCeiling);
	
	public static Tile DirtWall = new DirtWall(Sprite.DirtWall);
	public static Tile DresserBottom = new DresserBottom(Sprite.DresserBottom);
	public static Tile DresserTop = new DresserTop(Sprite.DresserTop);
	
	public static Tile Grass = new Terrain(Sprite.Grass, stepSound.Organic);
	
	
	public static Tile HellBrick = new HellBrick(Sprite.HellBrick);
	public static Tile HellBrickCeiling = new HellBrickCeiling(Sprite.HellBrickCeiling);
	public static Tile HellbrickWall = new HellbrickWall(Sprite.HellbrickWall);
	public static Tile HellCaveCeiling = new HellCaveCeiling(Sprite.HellCaveCeiling);
	public static Tile HellCaveWall = new HellCaveWall(Sprite.HellCaveWall);
	public static Tile Hellsand = new Hellsand(Sprite.Hellsand);
	public static Tile HellSandCeiling = new HellSandCeiling(Sprite.HellSandCeiling);
	public static Tile HellSandWall = new HellSandWall(Sprite.HellSandWall);
	public static Tile Hellstone = new Hellstone(Sprite.Hellstone);
	public static Tile Ice = new Ice(Sprite.Ice);
	public static Tile IceBrick = new IceBrick(Sprite.IceBrick);
	public static Tile IceBrickCeiling = new IceBrickCeiling(Sprite.IceBrickCeiling);
	public static Tile IceBrickWall = new IceBrickWall(Sprite.IceBrickWall);
	public static Tile IceCaveCeiling = new IceCaveCeiling(Sprite.IceCaveCeiling);
	public static Tile IceCaveWall = new IceCaveWall(Sprite.IceCaveWall);
	public static Tile IceSand = new IceSand(Sprite.IceSand);
	public static Tile IceSandCeiling = new IceSandCeiling(Sprite.IceSandCeiling);
	public static Tile IceSandWall = new IceSandWall(Sprite.IceSandWall);
	public static Tile Lava = new Lava(Sprite.Lava);
	public static Tile Metal = new Metal(Sprite.Metal);
	public static Tile MetalCeiling = new MetalCeiling(Sprite.MetalCeiling);
	public static Tile MetalWall = new MetalWall(Sprite.MetalWall);
	public static Tile MossCeiling = new MossCeiling(Sprite.MossCeiling);
	public static Tile MossWall = new MossWall(Sprite.MossWall);
	public static Tile ObsidianCeiling = new ObsidianCeiling(Sprite.ObsidianCeiling);
	public static Tile ObsidianWall = new ObsidianWall(Sprite.ObsidianWall);
	
	public static Tile PathDirt = new PathDirt(Sprite.PathDirt);
	
	public static Tile Sand = new Sand(Sprite.Sand);
	public static Tile SandBrick = new SandBrick(Sprite.SandBrick);
	public static Tile SandBrickCeiling = new SandBrickCeiling(Sprite.SandBrickCeiling);
	public static Tile SandBrickWall = new SandBrickWall(Sprite.SandBrickWall);
	public static Tile SandCeiling = new SandCeiling(Sprite.SandCeiling);
	public static Tile SandWall = new SandWall(Sprite.SandWall);
	public static Tile Snow = new Snow(Sprite.Snow);
	public static Tile StoneBrick = new StoneBrick(Sprite.StoneBrick);
	public static Tile StoneBrickCeiling = new StoneBrickCeiling(Sprite.StoneBrickCeiling);
	public static Tile StoneBrickWall = new StoneBrickWall(Sprite.StoneBrickWall);
	public static Tile Swamp = new Swamp(Sprite.Swamp);
	public static Tile VoidTile = new com.IB.SL.level.VoidTile(Sprite.VoidTile);
	//public static Tile Water = new Water();
	public static Tile Wood = new Wood(Sprite.Wood);
	public static Tile WoodCeiling = new WoodCeiling(Sprite.WoodCeiling);
	public static Tile WoodWall = new WoodWall(Sprite.WoodWall);
	public static Tile swirly = new Swirly(Sprite.Swirly);
	public static Tile DoorTile = new DoorTile(Sprite.Door);

	
	/**
	 * Overlay
	 */
	public static Tile TorchTile = new TorchTile(Sprite.Torch);
	public static Tile Cactus = new Cactus(Sprite.Cactus);
	public static Tile FlowerCactus = new FlowerCactus(Sprite.FlowerCactus);
	public static Tile ColoredFlowers = new ColoredFlowers(Sprite.ColoredFlowers);
	public static Tile YellowFlowers = new YellowFlowers(Sprite.YellowFlowers);
	public static Tile Pebble = new Pebble(Sprite.Pebble);
	public static Tile BlueMushroom = new BlueMushroom(Sprite.BlueMushroom);
	public static Tile BlueMushroomDirt = new com.IB.SL.level.tile.overlays.BlueMushroomDirt(Sprite.BlueMushroomDirt);
	public static Tile RedMushroom = new RedMushroom(Sprite.RedMushroom);
	public static Tile RedMushroomDirt = new com.IB.SL.level.tile.overlays.RedMushroom(Sprite.RedMushroomDirt);
	public static Tile GreenMushroom = new GreenMushroom(Sprite.GreenMushroom);
	public static Tile GreenMushroomDirt = new com.IB.SL.level.tile.overlays.GreenMushroom(Sprite.GreenMushroomDirt);
	public static Tile DirtPatch = new DirtPatch(Sprite.DirtPatch);
	public static Tile DarkCastle = new DarkCastle(Sprite.DarkCastle);
	public static Tile Cave = new Cave(Sprite.Cave);
	public static Tile Portal = new Portal(Sprite.Portal);
	public static Tile PathVertical = new PathVertical(Sprite.PathVertical);
	public static Tile PathHorizontal = new PathHorizontal(Sprite.PathHorizontal);
	public static Tile PathCross = new PathCross(Sprite.PathCross);
	public static Tile PathCornerTR = new PathCornerTR(Sprite.PathCornerTR);
	public static Tile PathCornerTL = new PathCornerTR(Sprite.PathCornerTL);
	public static Tile PathCornerBL = new PathCornerBL(Sprite.PathCornerBL);
	public static Tile PathCornerBR = new PathCornerBR(Sprite.PathCornerBR);
	public static Tile PathEndLeft = new PathEndLeft(Sprite.PathEndLeft);
	public static Tile PathEndRight = new PathEndRight(Sprite.PathEndRight);
	public static Tile PathEndTop = new PathEndTop(Sprite.PathEndTop);
	public static Tile PathEndBottom = new PathEndBottom(Sprite.PathEndBottom);
	public static Tile BrokenSword = new BrokenSword(Sprite.BrokenSword);
	public static Tile RedBed = new RedBed(Sprite.RedBed);
	public static Tile BlueBed = new BlueBed(Sprite.BlueBed);
	public static Tile GreenBed = new GreenBed(Sprite.GreenBed);
	public static Tile OrangeBed = new OrangeBed(Sprite.OrangeBed);
	public static Tile TopChair = new TopChair(Sprite.TopChair);
	public static Tile BottomChair = new BottomChair(Sprite.BottomChair);
	public static Tile LeftChair = new LeftChair(Sprite.LeftChair);
	public static Tile RightChair = new RightChair(Sprite.RightChair);
	public static Tile Castle = new Castle(Sprite.Castle);
	public static Tile Village = new Village(Sprite.Village);
	public static Tile Table = new Table(Sprite.Table);
	public static Tile bone = new Bone(Sprite.bone);
	public static Tile crossbone = new Crossbone(Sprite.crossBone);
	public static Tile skull = new Skull(Sprite.skull);
	public static Tile Anvil = new Anvil(Sprite.Anvil);
	public static Tile Stove = new Stove(Sprite.Stove);
	public static Tile OvVoidTile = new OvVoidTile(Sprite.VoidTile);
	public static Tile Counter = new Counter(Sprite.CounterA);
	public static Tile CounterB = new Counter(Sprite.CounterB);

	//public static final int col_grass = 0xff00ff00;
	//public static final int col_gmcsDOWN = 0xff00ff00;
	//public static final int col_gmcsLEFT = 0xff00ff00;
	//public static final int col_gmcsUP = 0xff00ff00;
	//public static final int col_cobblestone = 0xff00ff00;
	//public static final int col_flower = 0xff00ff00;
	//public static final int col_water = 0xff00ff00;
	public static Tile Water = new Water(Sprite.water);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}


	public void render(int x, int y, Screen screen) {
	}
	
	public boolean solid() {
		return false;
	}
	
	public stepSound StepSound() {
		return null;
	}

	public boolean exit() {
		return false;
	}

	public boolean solidtwo() {
		return false;
	}

	public boolean liquid() {
		return false;
	}
	
	public boolean illuminator() {
		return false;
	}
	
	public boolean antiSpawn() {
		return false;
	}

}
