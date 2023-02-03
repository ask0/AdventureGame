import Obstacles.Snake;

public class Quarry extends BattleLoc {
    public Quarry(Player player) {
        super(player, "Quarry", new Snake(), "Money, Weapon, Armor", 5);
    }

}
