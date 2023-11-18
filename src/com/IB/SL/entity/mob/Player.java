package com.IB.SL.entity.mob;

import java.awt.Desktop;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.Clip;

import com.IB.SL.Game;
import com.IB.SL.Game.gameState;
import com.IB.SL.entity.Entity;
import com.IB.SL.entity.abilities.Ability;
import com.IB.SL.entity.abilities.BlinkSpell;
import com.IB.SL.entity.abilities.Equilibrium;
import com.IB.SL.entity.abilities.EquippedAbilities;
import com.IB.SL.entity.abilities.Geartrap;
import com.IB.SL.entity.abilities.GoldenOrb;
import com.IB.SL.entity.abilities.HealingSpell;
import com.IB.SL.entity.abilities.HeatSeekingBolt;
import com.IB.SL.entity.abilities.Kunai;
import com.IB.SL.entity.abilities.RadialBlast;
import com.IB.SL.entity.abilities.SearingBolt;
import com.IB.SL.entity.abilities.SummonFamiliar;
import com.IB.SL.entity.inventory.ActiveEffects;
import com.IB.SL.entity.inventory.ActiveQuests;
import com.IB.SL.entity.inventory.EquipableItem;
import com.IB.SL.entity.inventory.Equipment;
import com.IB.SL.entity.inventory.Inventory;
import com.IB.SL.entity.inventory.Quest;
import com.IB.SL.entity.inventory.item.Item;
import com.IB.SL.entity.inventory.item.equipables.Weapon;
import com.IB.SL.entity.inventory.item.equipables.staves.wand_ArcaneTwig;
import com.IB.SL.entity.inventory.quests.qMain;
import com.IB.SL.entity.mob.peaceful.Horse;
import com.IB.SL.entity.projectile.Projectile;
import com.IB.SL.entity.projectile.WizardProjectile;
import com.IB.SL.graphics.AnimatedSprite;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;
import com.IB.SL.graphics.SpriteSheet;
import com.IB.SL.graphics.font8x8;
import com.IB.SL.graphics.UI.GUI;
import com.IB.SL.input.Keyboard;
import com.IB.SL.input.Mouse;
import com.IB.SL.level.Level;
import com.IB.SL.level.Node;
import com.IB.SL.level.RayCast;
import com.IB.SL.level.TileCoord;
import com.IB.SL.level.interactables.LootBag;
import com.IB.SL.level.interactables.Shop;
import com.IB.SL.level.tile.Tile;
import com.IB.SL.level.worlds.CaveHaven;
import com.IB.SL.level.worlds.Dungeon01;
import com.IB.SL.level.worlds.Dungeon02;
import com.IB.SL.level.worlds.Dungeon03;
import com.IB.SL.level.worlds.Dungeon04;
import com.IB.SL.level.worlds.IceHaven;
import com.IB.SL.level.worlds.MainLevel;
import com.IB.SL.level.worlds.Maps;
import com.IB.SL.level.worlds.RedTownHaven;
import com.IB.SL.level.worlds.SandHaven;
import com.IB.SL.level.worlds.SouthTownHaven;
import com.IB.SL.level.worlds.SpawnHaven;
import com.IB.SL.level.worlds.TutorialWorld;
import com.IB.SL.level.worlds.VoidBossRoom;
import com.IB.SL.util.Commands;
import com.IB.SL.util.LoadProperties;
import com.IB.SL.util.SaveGame;
import com.IB.SL.util.Sound;
import com.IB.SL.util.Vector2i;

public class Player extends Mob implements Serializable{

	/**
	 * 
	 */
	private transient  static final long serialVersionUID = -8911018741301426797L;
	private transient  int manaregen = 0;
	public transient  Keyboard input;
	public transient  Sprite sprite;
	private transient Clip tileSound;
	public transient  Tile tile;
	public transient int direction = 0;
	public int ExpStor;
	public double expModif = 1;
	public String[] invString;
	public String[] equipsString;
	transient double xOff = 0;
	transient double yOff = 0;
	public transient Shop nearShop = null;
	public boolean isStunned = false;

	//private transient transient Inventory inventory;
	//public transient Level level;
	transient int walkingPacketTime = 0;
	public transient  AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 16, 16, 3);
	public transient  AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 16, 16, 3);
	public transient  AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 16, 16, 3);
	public transient  AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 16, 16, 3);
	
	private transient  AnimatedSprite player_upstill = new AnimatedSprite(SpriteSheet.player_upstill, 16, 16, 1);
	private transient  AnimatedSprite player_downstill = new AnimatedSprite(SpriteSheet.player_downstill, 16, 16, 1);
	private transient  AnimatedSprite player_leftstill = new AnimatedSprite(SpriteSheet.player_leftstill, 16, 16, 1);
	private transient  AnimatedSprite player_rightstill = new AnimatedSprite(SpriteSheet.player_rightstill, 16, 16, 1);
	
	public transient  AnimatedSprite animSprite = down;
	

	
	public transient static java.util.Random random1 = new Random();
	public transient static int random = random1.nextInt(8 + 4);
	public transient  int fireRate = 1;
//	public transient  int abilityCooldown = 1;

	public transient Commands cmd;
	private transient double time = 0;
	public transient boolean sprinting = false;
	//private transient double sprint = 300;
	//private transient double sprintch1= 300;
	//private transient double sprintch2 = 900;
	public transient static int unlockTime = 0;
	public transient boolean commandModeOn = false;
	public transient static boolean unlockWarning = false;
	private transient boolean cmdReleased = true;
	private transient boolean shooting = false;
	private transient int standingint = 0;
	private transient int standint2 = 100;
	private transient boolean wasup = false;
	private transient boolean wasdown = true;
	private transient boolean wasright = false;
	private transient boolean wasleft = false;
	private transient boolean still = false;
	public transient boolean swimming = false;
	public transient int healthregen = 0;
	public transient int staminaregen = 0;
	public transient int staminaDegen = 0;
	public transient int combatTimer = 0;
	private transient int manaTimer;
	public int ExpC = 0;
	double DexpNeeded = 1000;
	double expNeeded;
	private transient boolean inventoryReleased;
	public transient boolean inventoryEnabled;
	public transient GUI gui;
	public  transient EquippedAbilities abilities;
	public transient LoadProperties loadProp;
	private  int tileX;
	private  int tileY;
	public  boolean setInvisible;
	private  int healthRegenRate;
	private  int manaRegenRate;
	private  int staminaRegenRate;
	public int skillPoints = 1;
	double xa = 0;
	double ya = 0;
	public Horse horse_following = null;
	public boolean noclip = false;
	transient private List<Node> path = null;
	transient double Pathtime = 0;
	private transient int suspControls;
	private transient int suspShooting;
	private transient boolean loadedProp = false;
	public transient boolean inChest = false;
	public transient boolean inPointMenu = false;
	public transient boolean canShoot = false;
	public transient Mob ridingOn = null;
	public transient boolean dead = false;
	transient RayCast raycastDIR;
	private transient  int dirInt = 0;
	public int currentLevelId = 0;
	public transient LootBag lootbag = new LootBag();
	
	//TODO: Generate UUID and send instead of USErname
	
	
	public Player(Keyboard input) {
		this.name = Game.getGame().PersonNameGetter;
		this.input = input;
		sprite = Sprite.playerback;
	}
	
	public Player(int x, int y, Keyboard input, String username) { 
		this.x = (int)x;
		this.y = (int)y;
		this.name = username;
		this.input = input; 
		init();
	}
	
	public void init() {
		
		this.speed = 1;
		 this.Lvl = 1;
		this.xBound = 8;
		this.yBound = 8;
		this.xOffset = 0;
		this.yOffset = 0;
		this.money = 30;
		this.hostility = hostility.PLAYER;

		this.maxhealth = 20;
		this.maxmana = 20;
		this.maxstamina = 20;
		
		this.inventory = new Inventory(16);
		this.equipment = new Equipment(7, this.inventory);
		this.quests = new ActiveQuests(7, this.inventory);
		this.effects = new ActiveEffects(7, this, this.inventory);
		
		this.abilities = new EquippedAbilities(10);
		cmd = new Commands();
		gui = new GUI();
		loadProp = new LoadProperties();
		
		calcStat(false);
		this.mobhealth = maxhealth;
		this.mana = maxmana;
		this.stamina = maxstamina;
		if (quests.lastItemInList(quests.quests) == -1) {
			quests.add(new qMain());
		}

		System.out.println("ADDING NEW PLAYER: " + this.x + "," + this.y);
	}
	
	
	
	
	//TODO: Check out where it's called??
	public boolean loadPlayer() {
		try { 
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public void calcStat(boolean item) {
			this.stat_VIT = (this.stat_base_VIT + this.stat_item_VIT);
			this.maxhealth = ((stat_VIT * 3) + 20) + this.stat_item_Health;
			this.healthRegenRate = ((int)(60 - (stat_VIT/10)));
			
			this.stat_WIS = (this.stat_base_WIS + this.stat_item_WIS);
			this.maxmana = ((stat_WIS * 3) + 20) + this.stat_item_Mana;
			this.manaRegenRate =  ((int)(80 - (stat_WIS/10)));
			
			this.stat_EDR = (this.stat_base_EDR + this.stat_item_EDR);
			this.maxstamina = ((stat_EDR * 3) + 20) + this.stat_item_Stam;
			this.staminaRegenRate = ((int)(65 - (stat_EDR/10)));
			
			this.stat_ATC = (this.stat_base_ATC + this.stat_item_ATC);
			
			this.stat_MAT = (this.stat_base_MAT + this.stat_item_MAT);
			
			this.stat_DEF = (this.stat_base_DEF + this.stat_item_DEF);
			
			this.stat_MDF = (this.stat_base_MDF + this.stat_item_MDF);

			this.stat_AGI = (this.stat_base_AGI + this.stat_item_AGI);

			if (maxhealth < mobhealth) {
				mobhealth = maxhealth;
			}
			
			if (maxmana < mana) {
				mana = maxmana;
			}
			
			if (maxstamina < stamina) {
				stamina = maxstamina;
			}
			
			if(!item) {
			this.mobhealth = maxhealth;
			this.mana = maxmana;
			this.stamina = maxstamina;
			
			for(int i = 0; i < equipment.items.length; i++) {
				if (equipment.items[i] != null) {
					equipment.items[i].RNGGen();
					equipment.items[i].dequipEvent();
					equipment.items[i].equipEvent();
				}
			}
	}
	}
	
	public boolean addQuest(Quest q) {
		boolean added = false;
			if (quests.add(q)) {
				added = true;
			}
			return added;
	}

	
	public boolean addItem(Item item) {
		boolean added = false;
			if (inventory.add(item)) {
				added = true;
				Sound.Play(Sound.InvAdd, false);

			} else {
				added = false;
			}
		return added;
		}

	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void addAbility(Ability ability) {
		System.out.println("Added " + ability);
		//abilities.removeAbility(ability);
		abilities.add(ability);
		
	}
	
	public EquippedAbilities getAbilities() {
		return abilities;
	}
	
	public void onPlayerDeath() {
		Level.minimap_enabled = false;
	}
	
	//TODO: Complete exp calc
	public int maxExp() {
		int before = Lvl;
		int alreadyHas = lvlCounter(before - 1);
		int required = lvlCounter(before);
		int maximum = required - alreadyHas;
		if (Lvl == 1) {
			maximum += 4;
		}
		return maximum;
	}
	
	public int currentExp() {
		int before = Lvl;
		int alreadyHas = lvlCounter(before - 1);
		int current = ExpC - alreadyHas;
		if (Lvl == 1) {
			current = ExpC;
		}
		return current;
	}
	
	
	 public int lvlCounter(int lvl) {
	  int CurrentLevel = lvl;
	  int levelcounter = CurrentLevel;
	  int xpcounter = 4;
	   
	  for (;levelcounter != 0; levelcounter--) {
	   xpcounter += (((levelcounter + 1) * (levelcounter + 1) * (levelcounter + 1))/2);
	  }
	  return xpcounter;
	 }
	 
	public void levelUp(int exp) {
		Lvl++;
		/*this.maxhealth = this.Lvl * 2;
		this.mobhealth = maxhealth;*/
		if (Game.getGame().ExpStor != exp) {
				Sound.Play(Sound.lavel_up, false);
				skillPoints += 3 + 10/(1 +Math.pow(Math.E,-0.05 * ((this.stat_MAT / 10) + 4)));
			}
	}
	

	
	public void invokeSave(Player p) {
		this.currentLevelId = Game.currentLevelId;
		inventory.saveItems(this.inventory.items);
		equipment.saveItems(this.equipment.items);
		quests.saveQuests(quests.quests);
		for (int i = 0; i < level.entities.size(); i++) {
			System.out.println("[===] " + level.entities.get(i).getClass());
		}
		System.out.println("====>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>===     |      " + level.entities + " ||| + " + level.entities.size());
		Entity[] es = level.entities.toArray(new Entity[level.entities.size()]);
		level.saveMobs(es);
		for(int i = 0; i < es.length; i++) {
			System.out.println("  |==|  " + es[i]);
		}
		es = null;
		SaveGame.save(p);
	}
	
	public void invokeLoad(Player p) {
		try {
		loadProp.loadPrefs(Game.getGame());;
		Player temp = SaveGame.load();

		System.out.println("-----------------------STEP1---------------------------");
		p.riding = false;
		p.ridingOn = null;
		p.speed = 1;
		
		p.currentLevelId = Game.currentLevelId;

		p.stat_base_ATC = temp.stat_base_ATC;
		p.stat_base_DEF = temp.stat_base_DEF;
		p.stat_base_VIT = temp.stat_base_VIT;
		p.stat_base_WIS = temp.stat_base_WIS;
		p.stat_base_EDR = temp.stat_base_EDR;
		p.stat_base_MAT = temp.stat_base_MAT;
		p.stat_base_MDF = temp.stat_base_MDF;
		p.stat_base_AGI = temp.stat_base_AGI;
		p.skillPoints = temp.skillPoints;
		p.Lvl = temp.Lvl;
		p.ExpC = temp.ExpC;
		p.direction = temp.direction;
		p.kills = temp.kills;
		p.money = temp.money;
		setPosition(temp.x, temp.y, temp.currentLevelId, false);
		calcStat(false);

		p.mobhealth = temp.mobhealth;
		p.mana = temp.mana;
		p.stamina = temp.stamina;
		
		System.out.println("-----------------------STEP2---------------------------");
		this.inventory.loadItems(this);
		System.out.println("-----------------------STEP3---------------------------");
		this.equipment.loadItems(this);
		System.out.println("-----------------------STEP4----------------------------");
		this.quests.loadQuests(this);
		System.out.println("-----------------------STEP5----------------------------");
		temp = null;
		
		if (getUsername().equalsIgnoreCase("Nate")) {
			//	inventory.add(new testItem_EQ001(EquipableItem.slot_WEAPON));
				/*inventory.add(new wand_Pulsefire(EquipableItem.slot_WEAPON));
				inventory.add(new wand_VoidCrook(EquipableItem.slot_WEAPON));
				inventory.add(new wand_FlareScepter(EquipableItem.slot_WEAPON));
				inventory.add(new wand_StygianScepter(EquipableItem.slot_WEAPON)); // fires 5 in a cone
				inventory.add(new wand_ContradictionWand(EquipableItem.slot_WEAPON)); // fires 10 in a circle*/
				this.money = 5000;
			}
		
		
		
		if (Game.runTut) {
			setPosition(73, 38, Maps.tutWorldId, true);
		}
		
		} catch (Exception e) {
			
		}
	}
	
	public void reset(Player p) {
		p.name = Game.PersonNameGetter;
		
		for (int i = 0; i < inventory.items.length; i++) {
		this.inventory.removeByIndex(i);
		}
		for (int j = 0; j < equipment.items.length; j++) {
			if (this.equipment.items[j] != null) {
				
			this.equipment.items[j].dequipEvent();
		this.equipment.items[j] = null;
			}
		}
		this.effects.removeAll();
		
		p.riding = false;
		p.ridingOn = null;
		p.speed = 1;
		
		p.currentLevelId = Maps.spawnHavenId;
		p.mobhealth = 20;
		p.maxhealth = 20;
		p.mana = 20;
		p.maxmana = 20;
		p.stamina = 20;
		p.maxstamina = 20;
		p.stat_base_ATC = 0;
		p.stat_base_DEF = 0;
		p.stat_base_VIT = 0;
		p.stat_base_WIS = 0;
		p.stat_base_EDR = 0;
		p.stat_base_MAT = 0;
		p.stat_base_MDF = 0;
		p.stat_base_AGI = 0;
		p.skillPoints = 1;
		p.Lvl = 1;
		p.ExpC = 0;
		p.kills = 0;
		p.money = 15;
		setPosition(52, 78, Maps.spawnHavenId, true);
		calcStat(false);
		Game.getGame().getPlayer().equipment.Equip(new wand_ArcaneTwig(EquipableItem.slot_WEAPON));
	}
	
	boolean addedAbility = false;
	public void update() {
		
		if (quests.active != null) {
			quests.active.update();
		} else if (quests.quests[0] != null){
			quests.active = quests.quests[0];
		}
		
		if (!addedAbility) {
			Game.getGame().getPlayer().abilities.clear();
			Game.getGame().getPlayer().abilities.add(new HealingSpell(0, 0, 0, this));
			Game.getGame().getPlayer().abilities.add(new BlinkSpell(0, 0, 0));
			Game.getGame().getPlayer().abilities.add(new Equilibrium(0, 0, 0, this));
			Game.getGame().getPlayer().abilities.add(new GoldenOrb(0, 0, 0, this));
			Game.getGame().getPlayer().abilities.add(new RadialBlast(0, 0, 0, this));
			Game.getGame().getPlayer().abilities.add(new SearingBolt(0, 0, 0, this));
			Game.getGame().getPlayer().abilities.add(new SummonFamiliar(0, 0, 0));
			Game.getGame().getPlayer().abilities.add(new HeatSeekingBolt(0, 0, 0));
			Game.getGame().getPlayer().abilities.add(new Geartrap(0, 0, 0));
			Game.getGame().getPlayer().abilities.add(new Kunai(0, 0, 0));

			addedAbility = true;
		}
		
		try {
			
		if (input.save){
			invokeSave(this);
			input.save = false;
		} else if (input.load){
			if (input.Sprint) {
				Game.getGame().getLevel().loadMobs(Game.currentLevelId);
			} else {
				invokeLoad(this);
			}
			input.load = false;
		}
		
		} catch (Exception e) {
			
		}
		//AFK
		
        raycastDIR = level.RayCast(new Vector2i(x, y), dirInt, (int)3);
		if (loadedProp == false) {
			loadPlayer();
			loadedProp = true;	
		}

		
		Pathtime++;
		//System.out.println(1 + (this.stat_ATC / 3));
			inventory.Update();
			abilities.Update(gui, this);
			effects.update();
			tileX = (int) x >> Game.TILE_BIT_SHIFT;
			tileY = (int) y >> Game.TILE_BIT_SHIFT;
			try {
				if (!this.riding) {
		if (level.getOverlayTile(tileX, tileY).exit()) {
			level.checkExits(this, level, tileX, tileY);
		}
				}
			} catch (Exception e) {
				
			}
		if (this.mobhealth <= 0) {
			Game.switchState(Game.getGame().gameState.DEATH);
		}
		
		
		if (Game.getGame().gameState == gameState.INGAME_A) {
		this.mana = maxmana;
		this.mobhealth = maxhealth;
		this.stamina = maxstamina;
		}
		int levelcounter = Lvl;
		int expCounter = 4;
			
		for (;levelcounter != 0; levelcounter--) {
			expCounter += (((levelcounter + 1) * (levelcounter + 1) * (levelcounter + 1))/2);
		}
		int expNeeded = expCounter;
//		expNeeded = (Math.round((Math.pow((nextLvl), 3) / 2)) * 2.5);
//		if (Lvl == 0) expNeeded = 1000;
		//expNeeded = Math.round((Math.pow((nextLvl), 3) / 2)); // A[n] = (((A[n-1] +1)^3)/2) + A[n-2]
//		expNeeded = A[n] = (((A[n-1] +1)^3)/2) + A[n-2];
//		System.out.println(expNeeded);
//		System.out.println(ExpC);
		if (ExpC >= (expNeeded)) {
			if (this == level.getClientPlayer()) {
			if (ExpC == Game.getGame().ExpStor) skillPoints = Lvl + 1;
			levelUp(ExpC);
			}
		}
		
		
		time++;
		staminaregen++;
		staminaDegen++;
		healthregen++;
		
	/*	if (wizardshotsfired >= 15 && this.mana > 10) {
			this.mana -=2;
			wizardshotsfired = 0;
		}*/
		if (this.mana < this.maxmana) {
		manaTimer++;
		}
		if (manaTimer > 165) {
		manaregen++;
		manaregen = 0;
		manaTimer = 0;
		regenMana();

		}
		if (incombat) {
			combatTimer++;
			//System.out.println("Now In Combat!");
		}
		
	if (combatTimer > 555) {
		incombat = false;
		combatTimer = 0;
		regenHealth();
	}
	
		if (standingint > standint2 && wasup && still) {
			still = true;
			animSprite = player_upstill;
		}
		if (standingint > standint2 && wasdown && still) {
			animSprite = player_downstill;
			still = true;
		}
		if (standingint > standint2 && wasleft && still) {
			animSprite = player_leftstill;
			still = true;
		}
		if (standingint > standint2 && wasright && still) {
			animSprite = player_rightstill;
			still = true;
		}
		if (walking == false) {
			standingint++;
		} else if (walking) {
			standingint = 0;
		}
		
			if (walking && !riding && !raycastDIR.hasCollided()) {
					animSprite.update();					
			} else animSprite.setFrame(0);
		
	//	if (abilityCooldown > 0) abilityCooldown--;
		if (fireRate > 0) fireRate--;
		
		if (!sprinting && stamina < maxstamina) {
			if (staminaregen % staminaRegenRate == 0) {
				stamina += (Math.pow(this.stat_EDR, 0.45) + 1);
				staminaregen = 0;
			}
		}
		
		if (!moving) {
			xOff = 0;
			yOff = 0;
		}
		
		if (swimming) {
			//speed = 0.5;
		}
		
		if (swimming && sprinting) {
			//speed = 1;
		}
		
		 xa = 0;
		 ya = 0;
		
		double speed = this.speed;
		
		if (input != null) {
			
			
			
		if (input.Sprint && !swimming && stamina > 0 && walking)  { // 300
			speed = 1.5;
			sprinting = true;
			if (staminaDegen % 30 == 0) {
			stamina--;
			staminaDegen = 0;
			}
		}
		

		if (!input.Sprint || stamina <= 0) {
			sprinting = false;
		}
		
		
	//	if (inventoryOn == false) {
		if (!inventoryEnabled && !inChest && !inPointMenu) {
			if (this == level.getClientPlayer()) {
			if (input.up) {
				if (riding) {
					yOff = 3;
				} else {
			animSprite = up;
			direction = 1;
			dirInt = 5;
			wasup = true;
			wasdown = false;
			wasright = false;
			wasleft = false;
			yOff = 0;
				}
			ya-= speed;
		} else if (input.down) {
				if (riding) {
					yOff = -3;
				} else {
			animSprite = down;
			direction = 0;
			wasup = false;
			wasdown = true;
			wasright = false;
			wasleft = false;
			yOff = 0;
			dirInt = 8;
				}
			ya+= speed;
		}
		if (input.left) {
			if (ridingOn != null) {
				ridingOn.dir = DIRECTION.LEFT;
				animSprite = player_leftstill;
				xOff = 2;
			} else {
				animSprite = left;				
				xOff = 0;
			}
			xa-= speed;
			wasup = false;
			wasdown = false;
			wasright = false;
			wasleft = true;
			direction = 2;
			if (!input.up && !input.down) {
				dirInt = 3;				
			} else {
				dirInt = 0;
			}
		} else if (input.right) {
			if (ridingOn != null) {
				ridingOn.dir = DIRECTION.RIGHT;
				animSprite = player_rightstill;
				xOff = -2;
			} else {
				animSprite = right;				
				xOff = 0;
			}
			xa+= speed;
			wasup = false;
			wasdown = false;
			wasright = true;
			wasleft = false;
			direction = 3;
			if (!input.up && !input.down) {
				dirInt = 0;
			} else {
				dirInt = 3;				
			}
		} 
			}
		}
		
		if (!noclip) {			
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
			
	    	
		} else {
			walking = false;
		
		}
		} else {
			x+= xa;
			y+= ya;
		}
		
		
		//}
		}
		
		if (inventoryEnabled || inChest || inPointMenu) {
			walking = false;
		}
		
		clear();
	
		if (walking) {
			still = false;
		}
		
		if (level != null && !raycastDIR.hasCollided()) {
			StepSounds();
			updateWalkingSounds(level);
		}
		/*if (Game.getGame().gameState == gameState.INGAME_A) {
			cmd.updateCommandMode(this, true);
		}*/
		
			regenHealth();
					if (canShoot && !inventoryEnabled && !inChest && !inPointMenu) {
						
			if (this == level.getClientPlayer()) {
				updateShooting();
				updateAbility();
				// updatePierceSpell();
			}
		}
								//	updateTeleportSpell();
				
			//Game.Health =  this.mobhealth;
			//Game.Mana =  this.mana;
						
			if (shooting && wasup) {
				animSprite = up;
				still = false;
			}
			if (shooting && wasdown) {
				animSprite = down;
				still = false;
			}
			if (shooting && wasright) {
				animSprite = right;
				still = false;
			}
			if (shooting && wasleft) {
				animSprite = left;
				still = false;
			}
				
			if (standingint > standint2 && Mouse.getButton() != 1) {
				still = true;
				shooting = false;
			}
			
		//command mode TOGGLE
			if (input != null) {
		if(input.commandMode && !commandModeOn && cmdReleased){
			commandModeOn = true;
			cmdReleased = false;
		}
		
		if(!input.commandMode) cmdReleased = true;
		
		if(input.commandMode && commandModeOn && cmdReleased){
			commandModeOn = false;
			cmdReleased = false;
				
			}
		
		
		if (unlockWarning) {
			unlockTime++;
		}
		
		if (unlockTime > 300) {
			unlockWarning = false;
			unlockTime = 0;
		}
		
		if(input.inventory || input.inventory_Equipment) {
				if (input.inventory_Equipment) {
					this.inventory.tab = this.inventory.tab.EQUIPMENT;
				} else {
					this.inventory.tab = this.inventory.tab.ITEMS;
				}
				if (!inventoryEnabled && inventoryReleased){
			
			inventoryEnabled = true;
			inventoryReleased = false;
			this.inventory.x = inventory.nativeX;
			this.inventory.y = inventory.nativeY;
			if(inPointMenu) {
				inPointMenu = false;
				inventoryEnabled = false;
			}
				
				Sound.Play(Sound.InvOpen, false);
				}
			}
		
		if(!input.inventory && !input.inventory_Equipment) inventoryReleased = true;
		
		if((input.inventory || input.inventory_Equipment)&& inventoryEnabled && inventoryReleased){
			Sound.Play(Sound.InvClose, false);
			inventoryEnabled = false;
			inventoryReleased = false;
			
		}
		
		
			}
		
		} 
		
		
	public int suspendControls(int i) {
		suspControls += i;
		return suspControls;
	}
	
	public int suspendShooting(int i) {
		suspShooting += i;
		return suspShooting;
	}
	
	public boolean controlsSus() {
		if (suspControls > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean shootingSus() {
		if (suspShooting > 0) {
			return true;
		} else {
			return false;
		}
	}
	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}
	
	private void updateSecondary() {
		Weapon w = (Weapon) equipment.getItem(EquipableItem.slot_SHEILD);
		if (w != null) {
			w.secondary(w.getProjectile(), w, this);
		}
	}
	
	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0 && isStunned == false) {
			Weapon w = (Weapon) equipment.getItem(EquipableItem.slot_WEAPON);
			if (w != null) {
				w.attack(w.getProjectile(), w, this);
				updateSecondary();
				fireRate = equipment.getItem(EquipableItem.slot_WEAPON).FIRE_RATE;
				if (Game.getGame().gameState == gameState.INGAME_A) {
					fireRate = 4;
				}
			} else {
				if (Game.getGame().gameState.equals(Game.getGame().gameState.INGAME_A)) {
					if (input.ctrl) {
					this.shoot(x, y, Weapon.angle(), this, 7777777);
					} else if (input.Sprint) {
					this.shoot(x, y, Weapon.angle(), this, 7777777);
					} else {
					this.shoot(x, y, Weapon.angle(), this, 3);
					}
				} else {
					this.shoot(x, y, Weapon.angle(), this, 3);
				}
				fireRate = WizardProjectile.FIRE_RATE;
			}

			shooting = true;
			incombat = true;
			standingint = 0;
		}
	}

	public boolean checkMana(int amount) {
		if (this.mana >= amount) {
			return true;
		}
		return false;
	}
	
	public boolean checkStamina(int amount) {
		if (this.stamina >= amount) {
			return true;
		}
		return false;
	}
	
	public boolean useMana(int amount) {
		if (checkMana(amount)) {
			this.mana -= amount;	
			return true;
		} else {
			return false;
		}
	}
	
	public boolean useStamina(int amount) {
		if (checkStamina(amount)) {
			this.stamina -= amount;	
			return true;
		} else {
			return false;
		}
	}
	
	public void abilitySwitched(Ability ab) {
		//if (Game.getGame().gameState != gameState.INGAME_A) {
		//	abilityCooldown = ab.FIRE_RATE;			
		//} else {
		//	abilityCooldown = 5;
		//}
	}
	
	private void updateAbility() {
		Ability ab = abilities.selected;
		if (ab != null) {
			if (abilities.Cooldowns.get(ab) >= ab.FIRE_RATE) {
				if (Mouse.getButton() == 3) {
				if (checkMana(ab.manaCost) && checkStamina(ab.staminaCost)) {
					if (ab.use(ab.getProjectile(), ab, this)) {
						useMana(ab.manaCost);
						useStamina(ab.staminaCost);

						abilities.Cooldowns.replace(ab, 0);
						//abilities.Cooldowns.put(ab, value) = ab.FIRE_RATE;
						shooting = true;
						standingint = 0;
						incombat = false;
						if (Game.getGame().gameState == gameState.INGAME_A) {
							abilities.Cooldowns.replace(ab, ab.FIRE_RATE - 3);
						}
					}
				}
				}
			}
		}
	}
	
	
		/*private void updatePierceSpell() {
			if (abilities.selected instanceof SearingBolt && Mouse.getButton() == 3 && fireRatePierce <= 0 && this.mana >= 5)  {
				this.mana  -=5;
					
				double dx = Mouse.getX() - Game.getWindowWidth() / 2 + 25;
				double dy = Mouse.getY() - Game.getWindowHeight() / 2;
				double dir = Math.atan2(dy, dx);
					PierceSpell(x, y, dir, this);
				if (Game.getGame().gameState != gameState.INGAME_A) {
					fireRatePierce = SearingBolt.FIRE_RATE;
				} else {
					fireRatePierce = 4;
				}
				Sound.Play(Sound.Spell2,  false);
				shooting = true;
				standingint = 0;
				incombat = true;
			}
		}*/
		
		
		
		@SuppressWarnings({"static-access" })
		private void StepSounds() {
		} 

	@SuppressWarnings("static-access")
	private void updateWalkingSounds(Level level) {
		//String tileString = Game.getGame().getLevel().returnTileFromValue(tile, (int)x / 16, (int)y / 16);
		Tile t = level.getTile((int)x / 16, (int)y / 16);
		//System.out.println(t);
		if (t.StepSound() != null) {
			
		if (t.StepSound().equals(Tile.stepSound.Hard)) {
			tileSound = Sound.WalkingCobblestone;
			if (sprinting) tileSound = Sound.WalkingCobblestoneFast;
			
		} else if (t.StepSound().equals(Tile.stepSound.Organic)) {
			tileSound = Sound.WalkingGrass;
			
		} else if (t.StepSound().equals(Tile.stepSound.Squishy)) {
			tileSound = Sound.walkingSand;
			
		} else if (t.StepSound().equals(Tile.stepSound.Water)) {
			tileSound = Sound.walkingWater;	
			swimming = true;
		} else {
			swimming = false;
			tileSound = null;
		}
		if (tileSound != null) {
	if (walking == true && time % 35 == 0 && sprinting == false) {
		Sound.Play(tileSound, false);
		
	}
		if (walking == true && time % 35 == 0 && sprinting == true) {
				Sound.Play(tileSound, false);
				animSprite.update();
				animSprite.update();
				animSprite.update();
				animSprite.update();
				animSprite.update();
				animSprite.update();
		}
		}
	}
	}	
	private void regenHealth() {
		if (this.mobhealth < this.maxhealth && (healthregen % healthRegenRate) == 0 && incombat == false) {
				healthregen = 0;
				this.mobhealth += (Math.pow(this.stat_VIT, 0.45) + 1);
				System.out.println("Player Health: " + this.mobhealth);
			}
		}
	
	private void regenMana() {
		if (this.mana < this.maxmana && (manaregen % manaRegenRate) == 0) {
				manaregen = 0;
				this.mana += (Math.pow(this.stat_WIS, 0.45) + 1);
				System.out.println("Player Mana: " + this.mana);
			}
		}	
		
	
	public void setPosition(TileCoord tileCoord) {
		this.setX(tileCoord.x());
		this.setY(tileCoord.y());
	}
	
	public void setPosition(double x, double y, int LvlId, boolean tileMult) {
		//Entity[] es = level.entities.toArray(new Entity[level.entities.size()]);
		//level.saveMobs(es);
		if (tileMult) {
			x *= TileCoord.TILE_SIZE;
			y *= TileCoord.TILE_SIZE;
		}

		this.currentLevelId = Game.currentLevelId;
		
		System.out.println("Loaded ID: " + LvlId + ", ID: " + currentLevelId);
		
		switch (LvlId) {
		case 0:
			Game.getGame().setLevel(new SpawnHaven(Maps.SpawnHaven));
			Sound.switchMusic(Sound.Windwalker, 1f);
			break;
		case 1:
			Game.getGame().setLevel(new MainLevel(Maps.main));
			Sound.switchMusic(Sound.Windwalker, 1f);
			SpriteSheet.minimapDYN = new SpriteSheet(Maps.main, 1024);
			break;
		case 2:
			Game.getGame().setLevel(new TutorialWorld(Maps.Tutorial_World));
			SpriteSheet.minimapDYN = new SpriteSheet(Maps.Tutorial_World, 104);
			break;
		case 3:
			Game.getGame().setLevel(new Dungeon01(Maps.dungeon01));
			SpriteSheet.minimapDYN = new SpriteSheet(Maps.dungeon01, 256);
			Sound.switchMusic(Sound.Boss, 0.8f);
			break;
		case 4:
			Game.getGame().setLevel(new Dungeon02(Maps.dungeon02));
			SpriteSheet.minimapDYN = new SpriteSheet(Maps.dungeon02, 256);
			Sound.switchMusic(Sound.VoidDungeon, 0.8f);
			break;
		case 5:
			Game.getGame().setLevel(new VoidBossRoom(Maps.VoidBossRoom));
			Sound.switchMusic(Sound.Boss, 0.8f);
			break;
		case 6:
			Game.getGame().setLevel(new Dungeon03(Maps.dungeon03));
			SpriteSheet.minimapDYN = new SpriteSheet(Maps.dungeon03, 356);
			Sound.switchMusic(Sound.TheMightyWillCrumble, 0.7f);
			break;
		case 7:
			Game.getGame().setLevel(new Dungeon04(Maps.dungeon04));
			SpriteSheet.minimapDYN = new SpriteSheet(Maps.dungeon04, 356);
			Sound.switchMusic(Sound.OasisSands, 0.7f);
			break;
		case 8:
			Game.getGame().setLevel(new CaveHaven(Maps.CaveHaven));
			Sound.switchMusic(Sound.Windwalker, 1f);
			break;
		case 9:
			Game.getGame().setLevel(new IceHaven(Maps.IceHaven));
			Sound.switchMusic(Sound.Windwalker, 1f);
			break;
		case 10:
			Game.getGame().setLevel(new RedTownHaven(Maps.RedTownHaven));
			Sound.switchMusic(Sound.Windwalker, 1f);
			break;
		case 11:
			Game.getGame().setLevel(new SandHaven(Maps.SandHaven));
			Sound.switchMusic(Sound.Windwalker, 1f);
			break;
		case 12:
			Game.getGame().setLevel(new SouthTownHaven(Maps.SouthTownHaven));
			Sound.switchMusic(Sound.Windwalker, 1f);
			break;
			
		}
		Game.getGame().getLevel().add(this);
		this.x = (x);
		this.y = (y);
		Game.getGame().getLevel().loadMobs(LvlId);
	}
	
	/*public void setAbility(Ability setAbility, Sprite displaySprite) {
		if (abilities.selected != null) {
		abilitySprite = displaySprite;
		if (Ability.setActivated != setAbility.getActiveCode()) {
			Sound.Play(Sound.Click, false);
			abilities.disableAll();
		Ability.setActivated = setAbility.getActiveCode();
		}
	}	}*/
	
	/*public void setLevel(Level level) {
		game = new Game();
			game.getPlayer().remove();
			Game.level = level;
			//level.add(new Player(277, 477, input));
		}*/

	public String getUsername() {
		return this.name;
	}
	
private transient Sprite arrow = Sprite.QuestArrow;
	public void render(Screen screen) {
	sprite = animSprite.getSprite();
/*	screen.renderSprite(25, 25, arrow, false);
	screen.renderSprite(25, 50, Sprite.rotate(arrow, Math.PI), false);
	screen.renderSprite(50, 50, Sprite.rotate(arrow, Math.PI / 2), false);
	screen.renderSprite(50, 25, Sprite.rotate(arrow, Math.PI * 2), false);*/


	if (abilities.selected != null) {
	this.abilities.selected.renderAoE(screen);
	}
	
	if (this.equipment.getItem(EquipableItem.slot_WEAPON) != null) {
		if ((canShoot && !inventoryEnabled && !inChest && !inPointMenu && (Game.getGame().gameState == Game.getGame().gameState.INGAME || Game.getGame().gameState == Game.getGame().gameState.INGAME_A))) {
	this.equipment.getItem(EquipableItem.slot_WEAPON).renderAoE(screen);
	this.equipment.getItem(EquipableItem.slot_WEAPON).playAnim(screen, this.fireRate);
		}
	}
	
	
	if(!setInvisible) {
		if (swimming && this.ridingOn == null) {
			screen.renderMobSpriteSwimming((int) (x - 8 + xOff), (int) (y - 15 + yOff),  sprite);
		} else {
			screen.renderMobSpriteUniversal((int) (x - 8 + xOff), (int) (y - 15 + yOff),  sprite);			
		}
	} else {
			//screen.renderLight((int) x - 308, (int) y - 315, 300, 0, 0, 0);
			screen.renderLight((int) x - 208, (int) y - 215, 200, 0, 5, 0);
	}
	//screen.renderLight((int) x - 20, (int) y - 20, 20, this, 20, 20, 20);
	if (Game.getGame().gameState == gameState.INGAME_A) {
	screen.drawRect((int)x - 8, (int)y - 15, 16, 16, 0x0093FF, true);
	try {		
		Game.getGame().getScreen().drawVectors(Game.getGame().getLevel().BresenhamLine((int)x, (int)y, raycastDIR.rayVector.x, raycastDIR.rayVector.y), 0xffFF3AFB, true);				
	} catch (NullPointerException e) {
	}
	//USE FOR FRIENDLY MOBS LATER ON: 0xff00FF21
	}
	
	}
	
public void renderGUI(Screen screen) {
	if (this.inventoryEnabled && level.minimap_enabled ){
		if (gui.checkBounds(254, 0, 45, 45)) {
			if (Mouse.getButton() == 1) {				
			System.out.println("TRUE");
			level.map_hidden =!  level.map_hidden;
			Mouse.setMouseB(-1);
			}
		}
	}
	
	if (level.map_hidden && this.inventoryEnabled) {
	screen.renderSheet(254, 0, SpriteSheet.minimap_hidden, false);
	}
	
	//screen.renderAlphaSprite(Sprite.resize(sprite.Anvil, 10), 25, 25);

	if (this.quests.active != null) {
	if (this.quests.active.destination.x() != -1000 * 16 && this.quests.active.destination.y() != -1000 * 16) {
		
	int dx = this.quests.active.destination.x() - 8;
	int dy = this.quests.active.destination.y() - 8;
	double xdx = x - dx;
	double ydy = y - dy;

	double r = (x - dx) / (Math.sqrt((xdx) * (xdx) +(ydy) * (ydy)));
	double r1 = Math.acos(r);
	double angle = 0;
	
	if (ydy < 0 && xdx < 0) {
		angle = r1 * -1 + Math.PI / 2;
	} else if (ydy < 0 && xdx  > 0){
		angle = r1 * -1 + Math.PI / 2;
	} else {
		angle = r1 + Math.PI / 2;
	}
	
	if (!this.inventoryEnabled && !this.inPointMenu) {
	screen.renderSprite(1, 148, new Sprite(19, 19, 0xffFFFFFF), false);
	screen.renderSprite(20, 155, new Sprite(quests.active.displayname.length() * 6 + 3, 12, 0xffFFFFFF), false);
	screen.renderSprite(2, 149, new Sprite(17, 17, 0), false);
	screen.renderSprite(2, 149, (Sprite.rotate(arrow, angle)), false);
	screen.renderSprite(20, 156, new Sprite(quests.active.displayname.length() * 6 + 2, 10, 0), false);
	gui.font8x8.render(14, 157, -2, 0xffFFFFFF, quests.active.displayname, screen, false, true);
	}
	} else {
		if (!this.inventoryEnabled && !this.inPointMenu) {
		screen.renderSprite(2, 155, new Sprite(quests.active.displayname.length() * 6 + 4, 12, 0xffFFFFFF), false);
		screen.renderSprite(3, 156, new Sprite(quests.active.displayname.length() * 6 + 2, 10, 0), false);
		gui.font8x8.render(-3, 157, -2, 0xffFFFFFF, quests.active.displayname, screen, false, true);
		}
	}
	}
	
	if (inventoryEnabled) { 
	gui.font8x8.render(116, 19, -3, 0xffFFFFFF, "Our Patreon", screen, false, true);
	if (this.gui.checkBounds(123, 20, 54, 8)) {
		gui.font8x8.render(116, 19, -3, 0xff00FF00, "Our Patreon", screen, false, true);

		if (Mouse.getButton() == 1) {
		  try {
			  Desktop.getDesktop().browse(new URL("https://www.patreon.com/user?u=4808803").toURI());
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		Mouse.setMouseB(-1);
		}
	} else {
		
	}
	}
	
	if (level.map_hidden && inventoryEnabled) { 
	screen.renderSprite(238, -2, Sprite.skull, false);
	if (this.gui.checkBounds(238, -2, 16, 16)) {
		if (Mouse.getButton() == 1) {
		  try {
		        Desktop.getDesktop().browse(new URL("https://www.youtube.com/watch?v=e-V9CIkA7tM").toURI());
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		Mouse.setMouseB(-1);
		}
	} else {
	}
	}
	

	
	if (ridingOn != null) {
		gui.font8x8.render(90, 15, -2, 0xff000000, "Sprint to Dismount", screen, false, false);
		gui.font8x8.render(91, 15, -2, 0xffFFFFFF, "Sprint to Dismount", screen, false, false);

	}

	try {
	if (abilities.selected == null) {
		abilities.checkAbility(screen, 0, false);
	}
	} catch (NullPointerException e) {
		
	}
	
	
	
	if (Game.getGame().gameState != gameState.PAUSE) {
		abilities.render(screen);
	}
	
	if (Game.getGame().gameState == gameState.INGAME || Game.getGame().gameState == gameState.INGAME_A) {
		if (inventoryEnabled) {
			gui.renderInventory(screen, this);
		}
		gui.renderAbilities(screen, this);
		gui.renderPoints(screen, this);
	}
	
		String text = (int)Game.getGame().getPlayer().getX() / 16 + "," + (int)Game.getGame().getPlayer().getY() / 16;
		//screen.renderSprite(1064/ Game.scale, 530 / Game.scale, gui.renderHealthExperiment(screen, this, 20), false);
		
		if (!level.minimap_enabled) {
			Game.getGame().font8x8.render((int)305 - text.length() * 8, 3, -3, text, screen, false, false);
			Game.getGame().font8x8.render((int)305 - text.length() * 8 + 1, 3, -3, 0xffFFFFFF, text, screen, false, false);
		} else if (!level.map_hidden){
			//screen.renderSprite(275 - text.length() * 8, 1, new Sprite(50, 12, 0xff262626), false);
			Game.getGame().font8x8.render((int)270 - text.length() * 8, 3, -3, text, screen, false, false);
			Game.getGame().font8x8.render((int)270 - text.length() * 8 + 1, 3, -3, 0xffFFFFFF, text, screen, false, false);
		} else {
			if (!this.inventoryEnabled) {
			Game.getGame().font8x8.render((int)305 - text.length() * 8, 3, -3, text, screen, false, false);	
			Game.getGame().font8x8.render((int)305 - text.length() * 8 + 1, 3, -3, 0xffFFFFFF, text, screen, false, false);
			} else {
				Game.getGame().font8x8.render((int)308 - text.length() * 8, 11, -3, text, screen, false, false);
				Game.getGame().font8x8.render((int)308 - text.length() * 8 + 1, 11, -3, 0xffFFFFFF, text, screen, false, false);
			}
		}

		
		if (Game.getGame().gameState == gameState.INGAME) {
		if (!gui.displayM && !gui.displayS) {
			gui.yOffH = 156;
		} else if (!gui.displayS && gui.displayM) {
			gui.yOffH = 143;
		} else if (gui.displayS && !gui.displayM) {
			gui.yOffH = 143;
		} else if (gui.displayS && gui.displayM){
			gui.yOffH = 130;
		}
		
		
		if (!gui.displayS) {
			gui.yOffM = 156;
		} else {
			gui.yOffM = 143;
		}
		
		
		
		  if (stamina < maxstamina) {
				gui.displayTimeS = 0;
				gui.displayS = true;
			} else if (gui.displayTimeS < 151){
				gui.displayS = false;
				gui.displayTimeS++;
			}
			if (gui.displayS) {
		screen.renderSprite(223, gui.yOffS, gui.renderBar(60, gui.staminabar, maxstamina, stamina), false); // 156
			}
			
		  if (incombat || mana < maxmana) {
				gui.displayTimeM = 0;
			} else if (gui.displayTimeM < 151){
				gui.displayTimeM++;
				gui.displayM = false;
			}
			if (gui.displayTimeM <= 150) {
				gui.displayM = true;
		screen.renderSprite(223, gui.yOffM, gui.renderBar(60, gui.manabar, maxmana, mana), false); // 143
			}
		
		
		if (incombat || mobhealth < maxhealth) {
			gui.displayTime = 0;
		} else if (gui.displayTime < 151){
			gui.displayTime++;
			gui.displayH = false;
		}
		if (gui.displayTime <= 150) {
			gui.displayH = true;
		screen.renderSprite(223, gui.yOffH, gui.renderBar(60, gui.healthbar, maxhealth, mobhealth), false); // 130
		}
	//	screen.renderSprite(0, 143, gui.expBar.getSprite(), false);
	}
	
	if(inPointMenu && (Game.getGame().gameState == Game.getGame().gameState.INGAME || Game.getGame().gameState == Game.getGame().gameState.INGAME_A)) {
		gui.renderPointMenu(screen, this);
		inventoryEnabled = false;
	}
}

	public String getUUID() {
		return UID;
	}
	
	public void setUID(String UUID) {
		UID = UUID;
	}

}
















