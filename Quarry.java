import Obstacles.Obstacle;
import Obstacles.Snake;
import Obstacles.Vampire;

public class Quarry extends BattleLoc {
    public Quarry(Player player) {
        super(player, "Quarry", new Snake(), "Money, Weapon, Armor", 5);
    }

}
