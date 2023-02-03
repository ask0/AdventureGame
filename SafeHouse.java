public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        System.out.println("# You are in the Safe House");
        if (this.getPlayer().getHealth() < this.getPlayer().getDefaultHealth()) {
            this.getPlayer().setHealth(this.getPlayer().getHealth() + 2);
            if (this.getPlayer().getHealth() >= this.getPlayer().getDefaultHealth()) {
                this.getPlayer().setHealth(this.getPlayer().getDefaultHealth());
                System.out.println("# Your health is max! ");
                System.out.println("# Current health: " + this.getPlayer().getHealth());
            } else {
                System.out.println("# Your health increased by 2");
                System.out.println("# Current health: " + this.getPlayer().getHealth());
            }
        } else if (this.getPlayer().getHealth() >= this.getPlayer().getDefaultHealth()) {
            System.out.println("# Your health is already maxed! ");
            System.out.println("# Current health: " + this.getPlayer().getHealth());
        }
        return true;
    }
}
