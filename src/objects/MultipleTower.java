package objects;

import enemies.Enemy;
import managers.ProjectileManager;


public class MultipleTower extends Tower {

    private ProjectileManager projectile;

    public MultipleTower() {

        super(0, "Multiple", 0, 1, 50, 10, 10, 0);
    }

    @Override
    public void attack(Enemy enemy) {
        // TODO Auto-generated method stub
    }

}
