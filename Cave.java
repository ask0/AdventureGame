import Obstacles.Obstacle;
import Obstacles.Zombie;

public class Cave extends BattleLoc {
    public Cave(Player player) {
        super(player, "Cave", new Zombie(), "Food", 2);
    }
}
