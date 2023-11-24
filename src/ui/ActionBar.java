package ui;

import static main.GameStates.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import objects.CriticalTower;
import objects.FrostTower;
import objects.MultipleTower;
import objects.PoisonTower;
import objects.Tower;
import scenes.Playing;

public class ActionBar {

	private Playing playing;
	private MyButtonNotVisible bMenu, bPause, bRestart;


	private MyButton[] towerButtons;
	private Tower selectedTower;
	private Tower displayedTower;
	private MyButton sellTower;
	private BufferedImage backgroundImage;

	private DecimalFormat formatter;

	private int gold = 125;
	private boolean showTowerCost;
	private int towerCostType;
	protected int x, y, width, height;
	private int lives = 5;

	public ActionBar(int x, int y, int width, int height, Playing playing) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.playing = playing;
		formatter = new DecimalFormat("0.0");

		initButtons();
		loadBackgroundImage();
	}

	public void resetEverything() {
		lives = 5;
		towerCostType = 0;
		showTowerCost = false;
		gold = 125;
		selectedTower = null;
		displayedTower = null;
	}

	private void initButtons() {

		bMenu = new MyButtonNotVisible("Menu", 672, 0, 140, 52);
		bPause = new MyButtonNotVisible("Pause", 672, 585, 140, 52);
		bRestart = new MyButtonNotVisible("Restart", 672, 535, 140, 52);
		towerButtons = new MyButton[4];

		int w = 60;
		int h = 60;
		int xStart = 710;
		int yStart = 100;
		int xOffset = (int) (w * 1.1f);

		for (int i = 0; i < towerButtons.length; i++)
			towerButtons[i] = new MyButton("", xStart, yStart + xOffset * i, w, h, i);


		sellTower = new MyButton("Sell", 700, 370, 80, 25);

	}

	public void removeOneLife() {
		lives--;
		if (lives <= 0)
			SetGameState(GAME_OVER);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bPause.draw(g);
		bRestart.draw(g);

		for (MyButton b : towerButtons) {
			g.setColor(Color.gray);
			g.fillRect(b.x, b.y, b.width, b.height);
			g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null);
			drawButtonFeedback(g, b);
		}
	}

	private void restartGame() {
    playing.resetEverything(); // Réinitialisez tout dans la scène Playing
    SetGameState(PLAYING); // Changez l'état du jeu à "en cours de lecture"
}

	private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("src/res/sidebar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void draw(Graphics g) {

		if (backgroundImage != null) {
            g.drawImage(backgroundImage, x, y, width, height, null);
        }

		// Buttons
		drawButtons(g);

		// DisplayedTower
		drawDisplayedTower(g);

		// Wave info
		drawWaveInfo(g);

		// Gold info
		drawGoldAmount(g);

		// Draw Tower Cost
		if (showTowerCost)
			drawTowerCost(g);

		// Game paused text
		if (playing.isGamePaused()) {
			g.setColor(Color.black);
			g.drawString("Game is Paused!", 300, 300);
		}

		// Lives
		g.setColor(Color.black);
		g.drawString("Lives: " + lives, 700, 490);

	}

	private void drawTowerCost(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(690, 410, 100, 50);
		g.setColor(Color.black);
		g.drawRect(690, 410, 100, 50);

		g.drawString(getTowerCostName(), 695, 430);
		g.drawString(getTowerCostCost() + "g", 695, 450);

		// Show this if player lacks gold for the selected tower.
		if (isTowerCostMoreThanCurrentGold()) {
			g.setColor(Color.RED);
			g.drawString("Can't Afford", 688, 480);

		}

	}

	private boolean isTowerCostMoreThanCurrentGold() {
		return getTowerCostCost() > gold;
	}

	private String getTowerCostName() {
		if (displayedTower != null) {
			return displayedTower.getName();
		}
		return "No Tower Selected";
	}


	private int getTowerCostCost() {
		if (displayedTower != null) {
			return displayedTower.getCost();
		}
		return 0;
	}

	private void drawGoldAmount(Graphics g) {
		g.drawString("" + gold, 740, 515);

	}

	private void drawWaveInfo(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		// drawWaveTimerInfo(g);
		drawEnemiesLeftInfo(g);
		drawWavesLeftInfo(g);

	}

	private void drawWavesLeftInfo(Graphics g) {
		int current = playing.getWaveManager().getWaveIndex();
		int size = playing.getWaveManager().getWaves().size();
		g.drawString("Wave " + (current + 1) + " / " + size, 10, 610);

	}

	private void drawEnemiesLeftInfo(Graphics g) {
		int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
		g.drawString("Enemies Left: " + remaining, 10, 630);
	}

	// private void drawWaveTimerInfo(Graphics g) {
	// 	if (playing.getWaveManager().isWaveTimerStarted()) {

	// 		float timeLeft = playing.getWaveManager().getTimeLeft();
	// 		String formattedText = formatter.format(timeLeft);
	// 		g.drawString("Time Left: " + formattedText, 10, 100);
	// 	}
	// }

	private void drawDisplayedTower(Graphics g) {
		if (displayedTower != null) {
			g.setColor(Color.gray);
			g.fillRect(410, 645, 220, 85);
			g.setColor(Color.black);
			g.drawRect(410, 645, 220, 85);
			g.drawRect(420, 650, 50, 50);
			//g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getType()], 300, 600, 50, 50,
					//null);
			g.setFont(new Font("LucidaSans", Font.BOLD, 15));
			g.drawString("" + displayedTower.getType(), 480, 660);
			g.drawString("ID: " + displayedTower.getId(), 480, 675);
			drawDisplayedTowerBorder(g);
			drawDisplayedTowerRange(g);

			// Sell button
			sellTower.draw(g);
			drawButtonFeedback(g, sellTower);

			if (sellTower.isMouseOver()) {
				g.setColor(Color.red);
				g.drawString("Sell for: " + getSellAmount(displayedTower) + "g", 690, 420);
			}
		}
	}

	protected void drawButtonFeedback(Graphics g, MyButton b) {
		// MouseOver
		if (b.isMouseOver())
			g.setColor(Color.white);
		else
			g.setColor(Color.BLACK);

		// Border
		g.drawRect(b.x, b.y, b.width, b.height);

		// MousePressed
		if (b.isMousePressed()) {
			g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
			g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
		}
	}


	private int getSellAmount(Tower displayedTower) {
		return displayedTower.getCost() / 2;
	}

	private void drawDisplayedTowerRange(Graphics g) {
		g.setColor(Color.white);
		g.drawOval(displayedTower.getX() + 16 - (int) (displayedTower.getRange() * 2) / 2, displayedTower.getY() + 16 - (int) (displayedTower.getRange() * 2) / 2, (int) displayedTower.getRange() * 2,
		(int) displayedTower.getRange() * 2);

	}

	private void drawDisplayedTowerBorder(Graphics g) {

		g.setColor(Color.CYAN);
		g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);

	}

	public void displayTower(Tower t) {
		displayedTower = t;
	}

	private void sellTowerClicked() {
		playing.removeTower(displayedTower);
		gold += displayedTower.getCost() / 2;

		displayedTower = null;

	}


	private void togglePause() {
		playing.setGamePaused(!playing.isGamePaused());

		// if (playing.isGamePaused())
		// 	bPause.setText("Unpause");
		// else
		// 	bPause.setText("Pause");

	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else if (bRestart.getBounds().contains(x, y)) {
    restartGame();
	}
		else if (bPause.getBounds().contains(x, y))
			togglePause();
		else {
			if (displayedTower != null) {
				if (sellTower.getBounds().contains(x, y)) {
					sellTowerClicked();
					return;
				}
			}

			for (MyButton b : towerButtons) {
				if (b.getBounds().contains(x, y)) {

					selectedTower = createTowerOfType(b.getId());
					System.out.println("Created Tower: " + selectedTower);
					System.out.println("Tower Button Clicked: ID = " + b.getId());
					System.out.printf("%s, %d\n", selectedTower.getName(), selectedTower.getCost());
					if(isGoldEnoughForTower(b.getId())){
					System.out.println("Tower bought");
					playing.setSelectedTower(selectedTower);
					} else {
					System.out.println("Not enough gold for the selected tower.");
					return;
					}
		
					return;
				}
			}
		}

	}
	
	private Tower createTowerOfType(int towerType) {
    switch (towerType) {
        case 0:
            return new MultipleTower();
        case 1:
            return new CriticalTower();
        case 2:
            return new PoisonTower();
        case 3:
            return new FrostTower();
        default:
		System.out.println("Unknown tower type: " + towerType);
            return null;
    }
}

	private boolean isGoldEnoughForTower(int towerType) {
		return gold > selectedTower.getCost();
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bPause.setMouseOver(false);
		showTowerCost = false;
		sellTower.setMouseOver(false);

		for (MyButton b : towerButtons)
			b.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else if (bPause.getBounds().contains(x, y))
			bPause.setMouseOver(true);
		else {

			if (displayedTower != null) {
				if (sellTower.getBounds().contains(x, y)) {
					sellTower.setMouseOver(true);
					return;
				}
			}

			for (MyButton b : towerButtons)
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					showTowerCost = true;
					towerCostType = b.getId();
					displayedTower = createTowerOfType(b.getId());
					return;
				}
		}
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else if (bPause.getBounds().contains(x, y))
			bPause.setMousePressed(true);
		else {

			if (displayedTower != null) {
				if (sellTower.getBounds().contains(x, y)) {
					sellTower.setMousePressed(true);
					return;
				}
			}

			for (MyButton b : towerButtons)
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
		}

	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bPause.resetBooleans();
		for (MyButton b : towerButtons)
			b.resetBooleans();
		sellTower.resetBooleans();

	}

	public void payForTower(int towerType) {
		this.gold -= selectedTower.getCost();

	}

	public void addGold(int getReward) {
		this.gold += getReward;
	}

	public int getLives() {
		return lives;
	}

}
