// Congratulations for finishing the game.
// Gives you a rank based on how long it took
// you to beat the game.

// Under two minutes = Speed Demon
// Under three minutes = Adventurer
// Under four minutes = Beginner
// Four minutes or greater = Bumbling Idiot

package com.neet.DiamondHunter.GameState;

import java.awt.Color;
import java.awt.Graphics2D;

import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.Data;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.JukeBox;
import com.neet.DiamondHunter.Manager.Keys;

public class GameOverState extends GameState {
	
	private Color color;
	
	private int rank;
	private long ticks;
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		color = new Color(164, 198, 222);
		ticks = Data.getTime();
		if(ticks < 3600) rank = 1;
		else if(ticks < 5400) rank = 2;
		else if(ticks < 7200) rank = 3;
		else rank = 4;
	}
	
	public void update() {}
	
	public void draw(Graphics2D g) {
		
		g.setColor(color);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
		
		Content.drawString(g, "finish time", 20, 20);
		
		int minutes = (int) (ticks / 1800);
		int seconds = (int) ((ticks / 30) % 60);
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 44, 32);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 44, 32);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 44, 32);
			else Content.drawString(g, minutes + ":" + seconds, 44, 22);
		}
		
		Content.drawString(g, "rank", 48, 45);
		if(rank == 1) Content.drawString(g, "God of Speed", 20, 58);
		else if(rank == 2) Content.drawString(g, "adventurer", 24, 58);
		else if(rank == 3) Content.drawString(g, "apprentice", 32, 58);
		else if(rank == 4) Content.drawString(g, "too slow", 8, 58);
		
                Content.drawString(g, "Your Three", 15, 75);
                Content.drawString(g, "Wishes May", 15, 95);
		Content.drawString(g, "Be Granted", 15, 115);
		
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) {
			gsm.setState(GameStateManager.MENU);
			JukeBox.play("collect");
		}
	}
	
}