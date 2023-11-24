package scenes;

import static main.GameStates.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;
import ui.MyButtonNotVisible;

public class Settings extends GameScene implements SceneMethods {

	private MyButtonNotVisible bMenu;
	private BufferedImage SettingsImage;

	public Settings(Game game) {
		super(game);
		initButtons();
		loadSettingsImage();

	}

	private void initButtons() {
		bMenu = new MyButtonNotVisible("Menu", 623, 35, 123, 62);
	}

	private void loadSettingsImage() {
        try {
            SettingsImage = ImageIO.read(new File("src/res/howToPlay.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void render(Graphics g) {
		g.drawImage(SettingsImage, 0, 0, 812, 640, null);
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);

	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);

	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bMenu.resetBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
