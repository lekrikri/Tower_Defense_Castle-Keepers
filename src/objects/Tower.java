package objects;

import enemies.Enemy;
import managers.ProjectileManager;

public abstract class Tower {
    private String name;
    private int type;
    private int damage;
    private int cost;
    private int speed;
    private float range;
    private float cooldown;
    private int projectiles;
    private int x, y, id, cdTick;

    public Tower(int id, String name, int type, int damage, int cost, int speed, float cooldown, int projectiles) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.cost = cost;
        this.speed = speed;
        this.cooldown = cooldown;
        this.projectiles = projectiles;
        this.range = 200;
    }

    public static final int MULTIPLE = 0;
    public static final int CRITICAL = 1;
    public static final int POISON = 2;
    public static final int FROST = 3;

    public abstract void attack(Enemy enemy);

    public void update() {
        cdTick++;
    }

    public boolean isCooldownOver() {
        return cdTick >= cooldown;
    }

    public void resetCooldown() {
        cdTick = 0;
    }

    // ---------- GETTERS AND SETTERS --------- //

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getRange() {
        return this.range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public float getCooldown() {
        return this.cooldown;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public int getProjectiles() {
        return this.projectiles;
    }

    public void setProjectiles(int projectiles) {
        this.projectiles = projectiles;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCdTick() {
        return this.cdTick;
    }

    public void setCdTick(int cdTick) {
        this.cdTick = cdTick;
    }
}
