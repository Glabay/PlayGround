package com.IB.SL.util;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.IB.SL.Game;
import com.IB.SL.entity.inventory.Quest;
import com.IB.SL.entity.mob.Player;
import com.IB.SL.level.Level;
import com.IB.SL.level.TileCoord;

public class Commands {

	static String newline = System.getProperty("line.separator");
	static String HelpText = "Note: Commands Are NOT CaSe SeNsItIvE" + newline + "Help: (Shows this screen) " + newline
			+ "Speed: (Sets Player Speed)" + newline + "Exp: (Adds/Subs Player EXP)" + newline
			+ "Time: (Sets Time), (Works Best At Total Night/Day)" + newline
			+ "TP: (Multi-player: TPs to another player)";

	/*
	 * public void updateCommandMode(Player player, boolean admin) {
	 * if(player.commandModeOn && admin){ player.commandModeOn = false;
	 * Game.switchState(Game.getGame().gameState.PAUSE); ArrayList<String> cmds =
	 * new ArrayList<String>();
	 * 
	 * cmds.add("help"); cmds.add("tp"); cmds.add("speed"); cmds.add("exp");
	 * cmds.add("time");
	 * 
	 * String Command = (String) JOptionPane.showInputDialog(Game.frame,
	 * "Enter Command (Or Type Help For A List Of Commands): ", "Command Mode"
	 * ,JOptionPane.PLAIN_MESSAGE, null, null, "" ); if (Command != null &&
	 * Command.length() > 0) { if (cmds.contains(Command.toLowerCase())) { try {
	 * switch (Command.toLowerCase()) { case "help":
	 * JOptionPane.showMessageDialog(Game.frame, HelpText, "Help",
	 * JOptionPane.INFORMATION_MESSAGE); break; case "tp":
	 * Game.getGame().getLevel().MPTeleport((String)
	 * JOptionPane.showInputDialog(Game.frame, "Type Name Here:",
	 * "Player Tele Command" , JOptionPane.PLAIN_MESSAGE, null, null, "")); break;
	 * case "speed": String WalkingSpeedString = (String)
	 * JOptionPane.showInputDialog(Game.frame,
	 * "Input Walking Speed, NUMBERS ONLY!:", "Administrative Command Mode",
	 * JOptionPane.PLAIN_MESSAGE, null, null, ""); if ((WalkingSpeedString == null)
	 * || (WalkingSpeedString.length() <= 0)) { JOptionPane.showMessageDialog
	 * (Game.frame, "Invalid Walking Speed Command", "Administrative Command Mode",
	 * JOptionPane.ERROR_MESSAGE); }else { try { double WalkingSpeedInt =
	 * Double.parseDouble(WalkingSpeedString);
	 * System.out.println("Set Speed Equal To " + WalkingSpeedInt); player.speed =
	 * WalkingSpeedInt;
	 * 
	 * }catch (NumberFormatException e) { JOptionPane.showMessageDialog (Game.frame,
	 * "Invalid Walking Speed Command", "Administrative Command Mode",
	 * JOptionPane.ERROR_MESSAGE); }
	 * 
	 * } break; case "exp": try { Double Exp = Double.parseDouble((String)
	 * JOptionPane.showInputDialog(Game.frame, "Input Ammount Of Exp To Add:",
	 * "Administrative Command Mode", JOptionPane.PLAIN_MESSAGE, null, null, ""));
	 * if (Exp == null) { Exp = 0.0; JOptionPane.showMessageDialog (Game.frame,
	 * "Invalid Exp Ammount Command", "Administrative Command Mode",
	 * JOptionPane.ERROR_MESSAGE); } player.ExpC += Exp;
	 * System.out.println("Added: " + Exp + "To Current EXP"); Exp = 0.0; } catch
	 * (Exception e) { e.printStackTrace(); } break; case "time": try { Integer time
	 * = Integer.parseInt((String) JOptionPane.showInputDialog(Game.frame,
	 * "Input Time To Set:", "Administrative Command Mode",
	 * JOptionPane.PLAIN_MESSAGE, null, null, "")); if (time == null) { time = 0;
	 * JOptionPane.showMessageDialog (Game.frame, "Invalid Time Ammount",
	 * "Administrative Command Mode", JOptionPane.ERROR_MESSAGE); } Level.brightness
	 * = time; if(time > 0) { Level.daytime = 2600; } if (time <= 0) {
	 * Level.nighttime = 2500; } System.out.println("Set Time To: " + time); time =
	 * 0; } catch (Exception e) { e.printStackTrace(); } break; case "":
	 * System.out.println("... Finished CMD Lap"); } Command = "";
	 * player.commandModeOn = true; updateCommandMode(player, true);
	 * 
	 * } catch (Exception e) { System.err.println("Improper CMD, try again!");
	 * JOptionPane.showMessageDialog (Game.frame, "Improper CMD Arg", "Oh noes!",
	 * JOptionPane.ERROR_MESSAGE); player.commandModeOn = true;
	 * updateCommandMode(player, true); } } else { JOptionPane.showMessageDialog
	 * (Game.frame, "Invalid Command", "Command Mode", JOptionPane.ERROR_MESSAGE);
	 * player.commandModeOn = true; updateCommandMode(player, true); } } } }
	 */

	public void updateCommandMode(String cmd, Player player) {
		ArrayList<String> cmds = new ArrayList<String>();
		int args = 0;
		String Command = "", Modifier = "", Modifier2 = "";

		for (int i = 0; i < cmd.toCharArray().length; i++) {
			if (cmd.toCharArray()[i] == ',') {
				args++;
			}
		}

		System.out.println("ARUMENTS: " + args);

		try {
			if (cmd.contains(" ")) {
				if (args == 0) {
					Command = cmd.substring(0, cmd.indexOf(" "));
					Modifier = cmd.substring(cmd.indexOf(" ") + 1, cmd.length());
				} else if (args == 1) {
					Command = cmd.substring(0, cmd.indexOf(" "));
					Modifier = cmd.substring(cmd.indexOf(" ") + 1, cmd.indexOf(","));
					Modifier2 = cmd.substring(cmd.indexOf(",", Modifier.length() - 1) + 1, cmd.length());

					System.out.println("MODIFIER: " + Modifier + " MOD 2: " + Modifier2);
				}
			} else {
				Command = cmd;
			}

		} catch (Exception e) {
			if (Modifier == null) {
				Modifier = "";
			}
			if (Modifier2 == null) {
				Modifier2 = "";
			}
		}

		cmds.add("help");
		cmds.add("tp");
		cmds.add("tpp");
		cmds.add("speed");
		cmds.add("exp");
		cmds.add("time");
		cmds.add("money");
		cmds.add("avg");
		cmds.add("dir");
		cmds.add("dbg");
		cmds.add("tcl");
		cmds.add("weather");
		cmds.add("nospawns");
		cmds.add("gs3");
		cmds.add("stage");
		cmds.add("cq");
		cmds.add("load");

		if (Command != null && Command.length() > 0) {
			if (cmds.contains(Command.toLowerCase())) {
				try {
					switch (Command.toLowerCase()) {
					case "help":
						JOptionPane.showMessageDialog(Game.frame, HelpText, "Help", JOptionPane.INFORMATION_MESSAGE);
						break;
					case "tpp":
						Game.getGame().getLevel().MPTeleport(Modifier);
						break;
					case "nospawns":
						if (Game.cmdln_args.containsKey("-nospawns")) {
							boolean g = Game.cmdln_args.get("-nospawns");
							Game.cmdln_args.put("-nospawns", !Game.cmdln_args.get("-nospawns"));
						} else {
							Game.cmdln_args.put("-nospawns", false);
						}
						break;
					case "tp":
						if (Modifier.equals("$")) {
							Modifier = "" + (int) Game.getGame().getPlayer().x / 16;
						}
						if (Modifier2.equals("$")) {
							Modifier2 = "" + (int) Game.getGame().getPlayer().y / 16;
						}
						Game.getGame().getPlayer()
								.setPosition(new TileCoord(Integer.parseInt(Modifier), Integer.parseInt(Modifier2)));

						break;
					case "cq":
						Quest q = player.quests.constructQuest(Modifier);
						if (q != null) {
							Game.getGame().getPlayer().addQuest(q);
						} else {
							System.out.println("Invalid path or something");
						}
						break;
					case "load":
						int id = Integer.parseInt(Modifier);
						Game.getGame().getPlayer().setPosition(0, 0, id, false);
						break;
					case "speed": {
						try {
							double WalkingSpeedInt = Double.parseDouble(Modifier);
							System.out.println("Set Speed Equal To " + WalkingSpeedInt);
							player.speed = WalkingSpeedInt;

						} catch (NumberFormatException e) {
						}

					}
						break;

					case "weather":
						if (Modifier.equalsIgnoreCase("rain")) {
							Game.getGame().getLevel().isRaining = !Game.getGame().getLevel().isRaining;
						} else if (Modifier.equalsIgnoreCase("clear")) {
							Game.getGame().getLevel().isRaining = false;
						}
						break;

					case "tcl":
						player.noclip = !player.noclip;
						break;

					case "stage":
						Game.getGame().getPlayer().quests.setStage(Modifier, Integer.parseInt(Modifier2));
						break;

					case "gs3":
						Game.getGame().switchState(Game.getGame().gameState.INGAME_A);
						break;

					case "dir":
						File f = new File(SaveGame.createSaveFolder());
						if (Modifier.equals("")) {
							f = new File(SaveGame.createSaveFolder());
						} else if (Modifier.equals("$logs")) {
							f = new File(SaveGame.createSaveFolder() + "/logs/");
						}
						Desktop.getDesktop().open(f);
						break;

					case "dbg":

						if (!Game.getGame().gameState.equals(Game.getGame().gameState.INGAME_A)) {
							Game.switchState(Game.getGame().gameState.INGAME_A);
						} else {
							Game.switchState(Game.getGame().gameState.INGAME);
						}

						break;

					case "avg": {

						Game.showAVG = !Game.showAVG;
						String fileName;
						if (Modifier.equals("$log-start")) {
							boolean path = new File(SaveGame.createSaveFolder() + "/logs/").mkdir();
							Game.recAVG_FPS = true;
						}

						if (Modifier.equals("$log-stop")) {
							if (Modifier2.equals("")) {
								fileName = "FPS_LOG_" + System.currentTimeMillis();
							} else {
								fileName = Modifier2;
							}
							Game.recAVG_FPS = false;
							Date date = new Date();
							try (Writer writer = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(SaveGame.createSaveFolder() + "/logs/" + fileName + ".txt"),
									"utf-8"))) {
								writer.write("AVERAGE FPS: " + Game.fpsAVG + ", SAMPLE SIZE: " + Game.fpsIndex);
								writer.write(
										System.getProperty("line.separator") + System.getProperty("line.separator"));
								writer.write("TEST CONDUCTED ON: " + date);
								writer.close();
							}
							Game.fpsIndex = 0;
							Game.fpsTotal = 0;
							Game.fpsAVG = 0;
						}
					}
						break;
					case "exp":
						try {
							player.ExpC += Double.parseDouble(Modifier);
							System.out.println("Added: " + Modifier + "To Current EXP");
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "money":
						try {
							player.money += Double.parseDouble(Modifier);
							System.out.println(Modifier + " Gold Added");
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "time":
						try {
							int time = Integer.parseInt(Modifier);
							Level.brightness = time;
							if (time > 0) {
								Level.daytime = 2600;
							}
							if (time <= 0) {
								Level.nighttime = 2500;
							}
							System.out.println("Set Time To: " + time);
							time = 0;
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "cl":
						Game.getGame().getPlayer().setPosition(0, 0, Integer.parseInt(Modifier), true);
						break;
					case "":
						System.out.println("... Finished CMD Lap");
						break;
					}
					Command = "";

				} catch (Exception e) {
					System.err.println("Improper CMD, try again!");
					e.printStackTrace();
				}
			} else {
			}
		}

	}
}