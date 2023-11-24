package scenes;

import static main.GameStates.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import inputs.BackgroundMusic;
import inputs.PlayingMusic;
import main.Game;
import ui.MyButtonNotVisible;

public class Menu extends GameScene implements SceneMethods {

	private BufferedImage menuImage;
	private MyButtonNotVisible bPlaying, bSettings, bQuit;

	private BackgroundMusic backgroundMusic;
	private PlayingMusic playingMusic;

	public Menu(Game game) {
		super(game);
		initButtons();
		importMenuImg();

		backgroundMusic = new BackgroundMusic();
        backgroundMusic.play("src/res/ZeldaMainThemeSong.wav");

		playingMusic = new PlayingMusic();
	}
    
	

	public void importMenuImg() {
    try {
        menuImage = ImageIO.read(new File("src/res/menu-bg.png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
		}

private void initButtons() {

			bPlaying = new MyButtonNotVisible("Play", 160, 500, 200, 95);
			bSettings = new MyButtonNotVisible("Settings", 610, 25, 180, 40);
			bQuit = new MyButtonNotVisible("Quit", 452, 500, 200, 95);
		}

	@Override
	public void render(Graphics g) {
		g.drawImage(menuImage, 0, 0, 812, 640, null);
		drawButtons(g);

	}

	private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
	}

	@Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            backgroundMusic.stop();
			playingMusic.play("src/res/FightSong.wav");
            SetGameState(PLAYING);
        } else if (bSettings.getBounds().contains(x, y)) {
            SetGameState(SETTINGS);
        } else if (bQuit.getBounds().contains(x, y)) {
			backgroundMusic.stop();
            System.exit(0);
        }
    }

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bPlaying.getBounds().contains(x, y))
			bPlaying.setMouseOver(true);
		else if (bSettings.getBounds().contains(x, y))
			bSettings.setMouseOver(true);
		else if (bQuit.getBounds().contains(x, y))
			bQuit.setMouseOver(true);

	}

	@Override
	public void mousePressed(int x, int y) {

		if (bPlaying.getBounds().contains(x, y)){
			backgroundMusic.stop();
			bPlaying.setMousePressed(true);
			
		}else if (bSettings.getBounds().contains(x, y))
			bSettings.setMousePressed(true);
		else if (bQuit.getBounds().contains(x, y))
			backgroundMusic.stop();
			bQuit.setMousePressed(true);

	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

}