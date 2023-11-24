package objects;

import enemies.Enemy;

public class FrostTower extends Tower {
    
    public FrostTower() {
        super(3, "Frost", 3, 1, 75, 10, 40, 3);

    }

        @Override
        public void attack(Enemy enemy) {
            enemy.slow();
        }

}
