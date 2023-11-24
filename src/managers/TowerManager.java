package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpz.LoadSave;
import objects.CriticalTower;
import objects.MultipleTower;
import objects.PoisonTower;
import objects.FrostTower;
import objects.Tower;
import scenes.Playing;

public class TowerManager {

	private Playing playing;
	private BufferedImage[] towerImgs;
	private ArrayList<Tower> towers = new ArrayList<>();
	private int towerAmount = 0;

	public TowerManager(Playing playing) {
		this.playing = playing;
		loadTowerImgs();
	}

	private void loadTowerImgs() {
		BufferedImage tileset = LoadSave.getSprites();

		towerImgs = new BufferedImage[4];
		for (int i = 0; i < 4; i++)
			towerImgs[i] = tileset.getSubimage((4 + i) * 32, 32 * 9, 32, 32);

	}

	public void addTower(Tower selectedTower, int xPos, int yPos) {
		Tower newTower;

		if (selectedTower instanceof MultipleTower) {
			newTower = new MultipleTower();
		} else if (selectedTower instanceof CriticalTower) {
			newTower = new CriticalTower();
		} else if (selectedTower instanceof PoisonTower) {
			newTower = new PoisonTower();
		} else {
			newTower = new FrostTower();
	}

    newTower.setX(xPos);
    newTower.setY(yPos);
    towers.add(newTower);

    System.out.println("Added tower: " + newTower.getName() + " at (" + xPos + ", " + yPos + ")");
}


	public void removeTower(Tower displayedTower) {
		for (int i = 0; i < towers.size(); i++)
			if (towers.get(i).getId() == displayedTower.getId())
				towers.remove(i);
	}


	public void update() {
		for (Tower t : towers) {
			t.update();
			attackEnemyIfClose(t);
		}
	}

	private void attackEnemyIfClose(Tower t) {
		for (Enemy e : playing.getEnemyManager().getEnemies()) {
			if (e.isAlive())
				if (isEnemyInRange(t, e)) {
					if (t.isCooldownOver()) {
						playing.shootEnemy(t, e);
						t.resetCooldown();
					}
				} else {
					// we do nothing
				}
		}

	}

	private boolean isEnemyInRange(Tower t, Enemy e) {
		int range = helpz.Utilz.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
		return range < t.getRange();
	}

	public void draw(Graphics g) {
		for (Tower t : towers)
			g.drawImage(towerImgs[t.getType()], t.getX(), t.getY(), null);
	}

	public Tower getTowerAt(int x, int y) {
		for (Tower t : towers)
			if (t.getX() == x)
				if (t.getY() == y)
					return t;
		return null;
	}

	public BufferedImage[] getTowerImgs() {
		return towerImgs;
	}

	public void reset() {
		towers.clear();
		towerAmount = 0;
	}

}
