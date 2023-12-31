package com.IB.SL.util;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.IB.SL.Game;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.font;
import com.IB.SL.graphics.font8x8;
import com.IB.SL.graphics.UI.CheckBounds;
import com.IB.SL.input.Keyboard;
import com.IB.SL.input.Mouse;

public class TextBox implements KeyListener{
	
private font8x8 font;
private font font16;

public String text = "";
public String finalText = "";

public int x, y, width, height, xOffset;
public CheckBounds checkBounds;
public boolean focused = false;
public Keyboard key;
public int maxLength = -1;
private boolean small;
public String caret = "l";
public String textWithCaret = text;
public String renderString = "";
int color;
public int caretPos = 0;
public boolean useCmds = false;
String history = "";
public String desc = "";
public int spacing;
public ArrayList<String> acceptable = new ArrayList<String>();

private class MyDispatcher implements KeyEventDispatcher {
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
        	setText(e);
        } else if (e.getID() == KeyEvent.KEY_TYPED) {
        }
        return false;
    }
}

	public TextBox(int x, int y, int width, int height, Keyboard key, boolean small) {
		basicInitialization(x, y, width, height, key, small);
	}
	
	public TextBox(int x, int y, int width, int height, Keyboard key, int maxLength, boolean small) {
		this.maxLength = maxLength;
		basicInitialization(x, y, width, height, key, small);
	}
	
	public void reset(boolean addHist) {
		if (addHist) {
			addHistory();
		}
		focused = false;
		finalText = "";
		text = "";
		caretPos = 0;
		
	}
	
	public void addHistory() {
		text = finalText;
		history += "\n" + finalText;
		System.out.println("History:" + history);
	}
	
	public void basicInitialization(int x, int y, int width, int height, Keyboard key, boolean small) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		checkBounds = new CheckBounds();
		font = new font8x8();
		font16 = new font();
		this.key = key;
		this.small = small;
		
		if (small) {
			this.spacing = font.spacing;
		} else {
			this.spacing = font16.spacing;
		}
		
		acceptable.add("a"); 
		acceptable.add("b"); 
		acceptable.add("c"); 
		acceptable.add("d"); 
		acceptable.add("e"); 
	    acceptable.add("f"); 
		acceptable.add("g"); 
		acceptable.add("h"); 
		acceptable.add("i"); 
		acceptable.add("j"); 
		acceptable.add("k"); 
		acceptable.add("l"); 
		acceptable.add("m"); 
		acceptable.add("n"); 
		acceptable.add("o"); 
		acceptable.add("p"); 
		acceptable.add("q"); 
		acceptable.add("r"); 
		acceptable.add("s"); 
		acceptable.add("t"); 
		acceptable.add("u"); 
		acceptable.add("v"); 
		acceptable.add("w"); 
		acceptable.add("x"); 
		acceptable.add("y"); 
		acceptable.add("z"); 
	
		acceptable.add("1"); 
		acceptable.add("2"); 
		acceptable.add("3"); 
		acceptable.add("4"); 
		acceptable.add("5"); 
		acceptable.add("6"); 
		acceptable.add("7"); 
		acceptable.add("8"); 
		acceptable.add("9"); 
		acceptable.add("0"); 
		acceptable.add("-"); 
		acceptable.add("$"); 
		acceptable.add(" "); 
		
		   KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	       manager.addKeyEventDispatcher(new MyDispatcher());		
	}
	
	public void delCharacter(boolean delete) {
		String postCaret = "";
		String beforeCaret = "";
		String newText = text;
		if (text.length() > 0) {
			
		
		if (delete) {
			text = "";
			caretPos = 0;
		} else {
			
			
			beforeCaret = text.substring(0, caretPos);
			
			if (caretPos != text.length()) {
				System.out.println("POST CARET IS NOT EQUAL TO TEXT LENGTH");
				postCaret = text.substring(caretPos, text.length());
				try {					
				newText = beforeCaret.substring(0, beforeCaret.length() - 1) + postCaret;
				} catch (StringIndexOutOfBoundsException e) {
					System.err.println("Textbox StringIndexOutOfBoundsExe");
				}
			} else {
				newText = beforeCaret.substring(0, beforeCaret.length() - 1);
			}
			
		//	System.out.println("BEFORE: " + beforeCaret.substring(0, beforeCaret.length() - 1) + " AFTER: " + postCaret);
			
			if (text.length() - 1 >= 0 && text != "") {
					text = newText;
					
					if (caretPos > 0) {
						caretPos -= 1;
					}
			}
		}
	}
	}
	
	public void addCharacter(char c) {
		
		String beforeCaret = text.substring(0, caretPos);
		String postCaret = text.substring(caretPos, text.length());
		
		if (text.length() + 1 < maxLength || maxLength == -1) {
			for (int i = 0; i < acceptable.size(); i++) {
				if (acceptable.get(i).equalsIgnoreCase("" + c)) {
					text = beforeCaret + c + postCaret;
					caretPos += 1;
					//caretPos = text.length();
					break;
				}
			}
			
		}
}
	
	
	 public String getText(boolean use) {
		 if (use) {
			 finalText = text;
		 }
		 
		 return finalText;
	 }
	
	public void setText(KeyEvent e) {
				if (focused) {
					if (key.enter) {
					finalText = text;
					focused = false;
					runCmd(text);
					xOffset = 0;
	
	        	} else {
	        		addCharacter(e.getKeyChar());
	        	}
		  }
	}
	
	public void setText(String text) {
		this.text = text;
		this.finalText = text;
		caretPos = text.length();
	}
	
	public void runCmd(String text) {
		focused = true;
	}
				
	int time = 0;
	public void updateCaret() {
		if (focused) {
			
		time++;
		
		if (time < 40) {
			caret = "l";
		} else if (time > 40 && time < 80) {
			caret = ":";
		} else if (time > 80) {
			time = 0;
		}
		} else {
			caret = "";
		}
		
		try {
		textWithCaret = text.substring(0, caretPos) + caret + text.substring(caretPos, text.length());
		} catch (Exception e) {
			//text = "";
		}
	}
	
	int pressTime = 0;
	boolean groove = false;
	public void update() {
		if (desc != "Command:" && maxLength == -1) {
			xOffset = 0;
		}
				
		
		if (caretPos >= (this.width + 16) / 16) {
			this.xOffset = (caretPos - (this.width + 16) / 16) * (-14);
		} else {
			xOffset = 0;
		}
		
		//System.out.println("CaretPos: " + caretPos + " Text Length: " + text.length());
		
		if (checkBounds.checkBounds(x, y, width, height, true, true)) {
				color = 0xffffffff;
			if(Mouse.getButton() == 1 && focused == false) {
				focused = true;
			Sound.Play(Sound.Click, false);
			Mouse.setMouseB(-1);
			}
		} else {
			if (!focused) {
			color = 0xffC8C8C8;
			}
			if (Mouse.getButton() == 1 && focused == true) {
				focused = false;
			}
		}
		
			pressTime++;			
		
		if (groove == false) {
			if (key.backspace) {
				delCharacter(false);
				groove = true;
				pressTime = 0;
			}
			if (key.arrow_Left) {
				if (caretPos > 0) {
				caretPos -= 1;
				time = 40;
				groove = true;
				pressTime = 0;
				}
			}
			if (key.arrow_Right) {
				if (caretPos < text.length()) {
				caretPos += 1;
				time = 40;
				groove = true;
				pressTime = 0;
				}
			}
		}

		if (pressTime > 5) {
			groove = false;
			pressTime = 0;
		}
		
		//System.out.println(font16.settings.get("tb_mode"));
		
		try {
			
		if (focused) {
		if (xOffset < 0) {
			if (caretPos < text.length()) {				
				renderString = textWithCaret.substring(xOffset / -14, caretPos + 1);
				} else {
				renderString = textWithCaret.substring(xOffset / -14, caretPos) + caret;
				}
			} else if (text.length() >= 18) {
				renderString = textWithCaret.substring(0, (int)((width / 14) - 1));
			} else { 
				renderString = textWithCaret;
			}
		}
		
		} catch (Exception e) {
			System.err.println(e);
			renderString = "!STRING ERROR!";
		}
		
		updateCaret();
		
	}
	
	public void render(Screen screen) {
		screen.drawFillRect(x, y, width, height, color, false);
		screen.drawRect(x, y, width, height, 0xff000000, false);
				if (small) {
					if (focused || text.length() > 0) {
						font.render(x - 4 + xOffset, y + (height / 2) - 8, -2, renderString, screen, false, false);
					} else {
						font.render(x - 4, y + (height / 2) - 8, -2, desc, screen, false, false);
					}
					
				} else {
					if (focused || text.length() > 0) {
					font16.render(x - 8, y + (height / 2) - 8, -2, renderString, screen, false, false);
					} else {
						font16.render(x - 8, y + (height / 2) - 8, -2, desc, screen, false, false);
					}
				}
				
				
				
			//	font.render(x - 4, y + (height / 2) - 8, -2, history, screen, false, false);
				

	}


	

	public void keyPressed(KeyEvent keyEvent) {
	}

	public void keyReleased(KeyEvent keyEvent) {
	}

	public void keyTyped(KeyEvent keyEvent) {
	}

	

}
