import Obstacles.Obstacle;

import java.util.Random;

public class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    protected boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("# You are in [ " + this.getName() + " ]");
        System.out.println("# Be careful. [ " + obsNumber + " x " + this.getObstacle().getName() + " ] live here!");

        boolean isContinue = true;
        while (isContinue) {
            System.out.print("-> <F>ight or <R>un: ");
            String selectCase = scanner.nextLine();
            selectCase = selectCase.toUpperCase();
            switch (selectCase) {
                case "F" -> {
                    System.out.println("# Fight proccess");
                    if (combat(obsNumber)) {
                        // System.out.println("# You've killed all the enemies!");
                        isContinue = false;
                        return true;
                    }
                    if (this.getPlayer().getHealth() < 0) {
                        System.out.println("# You've died in the combat. RIP");
                        return false;
                    }
                    isContinue = false;
                }
                case "R" -> {
                    System.out.println("# Run proccess");
                    isContinue = false;
                }
                default -> {
                    System.out.println();
                }
            }
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 0; i < obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
            playerStats();
            obstacleStats();
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("-> <F>ight or <R>un: ");
                String selectCombat = scanner.nextLine().toUpperCase();
                if (selectCombat.equals("F")) {
                    System.out.println("# You hit the " + this.getObstacle().getName());
                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println();
                        System.out.println("# " + this.getObstacle().getName() + " hits you!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0)
                            obstacleDamage = 0;
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                }
            }
            if (this.getPlayer().getHealth() <= 0) {
                return false;
            }
            if (this.getObstacle().getHealth() <= 0 && (obsNumber - i == 1)) {
                System.out.println("# You've killed all the enemies!");
            } else if (this.getObstacle().getHealth() <= 0) {
                System.out.println("# You've killed " + (i + 1) + " " + this.getObstacle().getName() + " " + (obsNumber - i - 1) + " more left");
            }
        }
        return false;
    }

    public void afterHit() {
        System.out.println("# Your health: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + "'s health: " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats() {
        System.out.println("# Character Stats:");
        System.out.println("--------------------------------");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Armor: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Block: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Money: " + this.getPlayer().getMoney());
    }

    public void obstacleStats() {
        System.out.println("# " + this.getObstacle().getName() + "Stats:");
        System.out.println("--------------------------------");
        System.out.println("Health: " + this.getObstacle().getHealth());
        System.out.println("Damage: " + this.getObstacle().getDamage());
        System.out.println("Award: " + this.getObstacle().getAward());
    }

    public int randomObstacleNumber() {
        Random random = new Random();
        return random.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
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
