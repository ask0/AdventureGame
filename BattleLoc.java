import Obstacles.Obstacle;

public class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
    }

    @Override
    protected boolean onLocation() {
        System.out.println("# You are in [ " + this.getName() + " ]");
        System.out.println("# Be careful. [ " + this.getObstacle().getName() + " ] live here!");
        return true;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
