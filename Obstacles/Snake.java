package Obstacles;

import java.util.Random;

public class Snake extends Obstacle {

    public Snake() {
        super(4, "Snake", 0, 12, 0);
        this.setDamage(randomDamage());
    }

    public int randomDamage() {
        Random random = new Random();
        return (random.nextInt(4) + 3);
    }

}
