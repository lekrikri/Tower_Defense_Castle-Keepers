package scenes;

import static helpz.Constants.Tiles.*;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import helpz.LoadSave;
import main.Game;
import objects.PathPoint;
import objects.Tile;

public class Editing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private Tile selectedTile;
	private int mouseX, mouseY;
	private int lastTileX, lastTileY, lastTileId;
	private boolean drawSelect;
	private PathPoint start, end;

	public Editing(Game game) {
		super(game);
		loadDefaultLevel();
	}

	private void loadDefaultLevel() {
		lvl = LoadSave.GetLevelData();
		ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints();
		start = points.get(0);
		end = points.get(1);
	}

	public void update() {
		updateTick();
	}

	@Override
	public void render(Graphics g) {

		drawLevel(g);
		drawSelectedTile(g);

	}


	private void drawLevel(Graphics g) {
		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				if (isAnimation(id)) {
					g.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
				} else
					g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
	}

	private void drawSelectedTile(Graphics g) {
		if (selectedTile != null && drawSelect) {
			g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
		}
	}

	public void saveLevel() {

		LoadSave.SaveLevel(lvl, start, end);
		game.getPlaying().setLevel(lvl);

	}

	public void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
		drawSelect = true;
	}

	private void changeTile(int x, int y) {
		if (selectedTile != null) {
			int tileX = x / 32;
			int tileY = y / 32;

			if (selectedTile.getId() >= 0) {
				if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
					return;

				lastTileX = tileX;
				lastTileY = tileY;
				lastTileId = selectedTile.getId();

				lvl[tileY][tileX] = selectedTile.getId();
			} else {
				int id = lvl[tileY][tileX];
				if (game.getTileManager().getTile(id).getTileType() == ROAD_TILE) {
					if (selectedTile.getId() == -1)
						start = new PathPoint(tileX, tileY);
					else
						end = new PathPoint(tileX, tileY);
				}
			}
		}
	}

	@Override
	public void mouseClicked(int x, int y) {

	}

	@Override
	public void mouseMoved(int x, int y) {
	}

	@Override
	public void mousePressed(int x, int y) {
	}

	@Override
	public void mouseReleased(int x, int y) {

	}

	@Override
	public void mouseDragged(int x, int y) {
		}


	public void keyPressed(KeyEvent e) {
}
}
