package scenes;

import static main.GameStates.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;
import ui.MyButtonNotVisible;

public class GameOver extends GameScene implements SceneMethods {

	private MyButtonNotVisible bReplay;
	private BufferedImage gameOverImage;

	public GameOver(Game game) {
		super(game);
		initButtons();
		loadGameOverImage();
	}

	private void initButtons() {

		bReplay = new MyButtonNotVisible("Replay", 300, 500 , 210, 100);

	}

	private void loadGameOverImage() {
        try {
            gameOverImage = ImageIO.read(new File("src/res/gameover-bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void render(Graphics g) {
		g.drawImage(gameOverImage, 0, 0, 812, 640, null);
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bReplay.draw(g);
	}

	private void replayGame() {
		// reset everything
		resetAll();

		// change state to playing
		SetGameState(PLAYING);

	}

	private void resetAll() {
		game.getPlaying().resetEverything();
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bReplay.getBounds().contains(x, y))
			replayGame();
	}

	@Override
	public void mouseMoved(int x, int y) {
		bReplay.setMouseOver(false);
		if (bReplay.getBounds().contains(x, y))
			bReplay.setMouseOver(true);
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bReplay.getBounds().contains(x, y))
			bReplay.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		bReplay.resetBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {
	}

}
