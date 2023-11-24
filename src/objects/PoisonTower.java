package objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import enemies.Enemy;

public class PoisonTower extends Tower {

	private Timer timer;

    public PoisonTower() {
        super(2, "Poison", 2, 4, 75, 10, 50, 3);
    }
    @Override
    public void attack(Enemy enemy) {
        int damagePerSecond = 20;

        if (timer != null && timer.isRunning()) {
            return;
        }

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (enemy.isAlive()) {
                    enemy.hurt(damagePerSecond);
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

}
