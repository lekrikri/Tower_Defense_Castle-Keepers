package managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import enemies.Enemy;
import helpz.LoadSave;
import objects.MultipleTower;
import objects.CriticalTower;
import objects.PoisonTower;
import objects.FrostTower;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;


public class ProjectileManager {

	private Playing playing;
	private MultipleTower multiple;
	private CriticalTower critical;
	private PoisonTower poison;
	private FrostTower frost;
	private ArrayList<Explosion> explosions = new ArrayList<>();
	private BufferedImage[] proj_imgs, explo_imgs;
	private int proj_id = 0;
	public ArrayList<Projectile> projectiles;

	

	// Temp variables
	private boolean callTrue;
	private long lastCall;

	public ProjectileManager(Playing playing) {
		this.playing = playing;
		this.multiple = new MultipleTower();
		this.critical = new CriticalTower();
		this.poison = new PoisonTower();
		this.frost = new FrostTower();
		projectiles = new ArrayList<>();
		
		importImgs();

	}


	private void importImgs() {
		BufferedImage sprites = LoadSave.getSprites();
		proj_imgs = new BufferedImage[4];

		for (int i = 0; i < 4; i++)
			proj_imgs[i] = sprites.getSubimage(32 * 8, (4 + i) * 32, 32, 32);
		importExplosion(sprites);
	}

	private void importExplosion(BufferedImage sprites) {
		explo_imgs = new BufferedImage[7];

		for (int i = 0; i < 7; i++)
			explo_imgs[i] = sprites.getSubimage( i * 32, 32 * 8, 32, 32);

	}

	public void newProjectile(Tower t, Enemy e) {
		int type = t.getType();

		int xDist = (int) (t.getX() - e.getX());
		int yDist = (int) (t.getY() - e.getY());
		int totDist = Math.abs(xDist) + Math.abs(yDist);

		float xPer = (float) Math.abs(xDist) / totDist;

		float xSpeed = xPer * objects.Projectile.GetSpeed(type);
		float ySpeed = objects.Projectile.GetSpeed(type) - xSpeed;

		if (t.getX() > e.getX())
			xSpeed *= -1;
		if (t.getY() > e.getY())
			ySpeed *= -1;

		float rotate = 0;

		if (type == 2) {
			float arcValue = (float) Math.atan(yDist / (float) xDist);
			rotate = (float) Math.toDegrees(arcValue);

			if (xDist < 0)
				rotate += 180;
		}

		for (Projectile p : projectiles)
			if (!p.isActive())
				if (p.getProjectileType() == type) {
					p.reuse(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDamage(), rotate);
					return;
				}

		projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDamage(), rotate, proj_id++, type));

	}

	public void update() {
		for (Projectile p : projectiles)
			if (p.isActive()) {
				p.move();
				if (isProjHittingEnemy(p)) {
					p.setActive(false);
					if (p.getProjectileType() == 1) {
						explosions.add(new Explosion(p.getPos()));
						explodeOnEnemies(p);
					}
				} else if (isProjOutsideBounds(p)) {
					p.setActive(false);
				}
			}

		for (Explosion e : explosions)
			if (e.getIndex() < 7)
				e.update();

	}

	private void explodeOnEnemies(Projectile p) {
		for (Enemy e : playing.getEnemyManager().getEnemies()) {
			if (e.isAlive()) {
				float radius = 40.0f;

				float xDist = Math.abs(p.getPos().x - e.getX());
				float yDist = Math.abs(p.getPos().y - e.getY());

				float realDist = (float) Math.hypot(xDist, yDist);

				if (realDist <= radius)
					e.hurt(p.getDmg());
			}

		}

	}

	private boolean isProjHittingEnemy(Projectile p) {
		for (Enemy e : playing.getEnemyManager().getEnemies()) {
			if (e.isAlive())
				if (e.getBounds().contains(p.getPos())) {
					e.hurt(p.getDmg());
					switch (p.getProjectileType()) {
					case 0: // MULTIPLE TOWER SPECIAL ATTACK
						multiple.attack(e);
						break;
					case 1: // CRITICAL TOWER SPECIAL ATTACK
						critical.attack(e);
						break;
					case 2: // POISON TOWER SPECIAL ATTACK
						poison.attack(e);
						break;
						
					case 3: // FROST TOWER SPECIAL ATTACK
						frost.attack(e);
					
						default:
							break;
					}

				}
		}
		return false;
	}

	private boolean isProjOutsideBounds(Projectile p) {
		if (p.getPos().x >= 0)
			if (p.getPos().x <= 640)
				if (p.getPos().y >= 0)
					if (p.getPos().y <= 800)
						return false;
		return true;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		for (Projectile p : projectiles)
			if (p.isActive()) {
				if (p.getProjectileType() == 4) {
					g2d.translate(p.getPos().x, p.getPos().y);
					g2d.rotate(Math.toRadians(p.getRotation()));
					g2d.drawImage(proj_imgs[p.getProjectileType()], -16, -16, null);
					g2d.rotate(-Math.toRadians(p.getRotation()));
					g2d.translate(-p.getPos().x, -p.getPos().y);
				} else {
					g2d.drawImage(proj_imgs[p.getProjectileType()], (int) p.getPos().x - 16, (int) p.getPos().y - 16, null);
				}
			}

		drawExplosions(g2d);

	}

	private void drawExplosions(Graphics2D g2d) {
		for (Explosion e : explosions)
			if (e.getIndex() < 7)
				g2d.drawImage(explo_imgs[e.getIndex()], (int) e.getPos().x - 16, (int) e.getPos().y - 16, null);
	}

	public class Explosion {

		private Point2D.Float pos;
		private int exploTick, exploIndex;

		public Explosion(Point2D.Float pos) {
			this.pos = pos;
		}

		public void update() {
			exploTick++;
			if (exploTick >= 6) {
				exploTick = 0;
				exploIndex++;
			}
		}

		public int getIndex() {
			return exploIndex;
		}

		public Point2D.Float getPos() {
			return pos;
		}
	}

	public void reset() {
		projectiles.clear();
		explosions.clear();

		proj_id = 0;
	}

}
