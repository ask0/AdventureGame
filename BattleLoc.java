import Armors.HeavyArmor;
import Armors.LightArmor;
import Armors.MediumArmor;
import Obstacles.Obstacle;
import Weapons.Gun;
import Weapons.Rifle;
import Weapons.Sword;

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
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        if (this.getName().equals("Cave") && this.getPlayer().getInventory().isFood()) {
            System.out.println("# You already killed all enemies in the " + this.getName());
        } else if (this.getName().equals("Forest") && this.getPlayer().getInventory().isFirewood()) {
            System.out.println("# You already killed all enemies in the " + this.getName());
        } else if (this.getName().equals("River") && this.getPlayer().getInventory().isWater()) {
            System.out.println("# You already killed all enemies in the " + this.getName());
        } else {
            System.out.println("# You are in [ " + this.getName() + " ]");
            System.out.println("# Be careful. [ " + obsNumber + " x " + this.getObstacle().getName() + " ] live here!");

            boolean isContinue = true;
            while (isContinue) {
                System.out.print("-> <F>ight or <R>un: ");
                String selectCase = Location.scanner.nextLine();
                selectCase = selectCase.toUpperCase();
                switch (selectCase) {
                    case "F" -> {
                        System.out.println("#################################################################");
                        System.out.println("# Fight starts..");
                        if (combat(obsNumber)) {
                            // System.out.println("# You've killed all the enemies!");
                            isContinue = false;
                            return true;
                        }
                        if (this.getPlayer().getHealth() <= 0) {
                            System.out.println("# You've died in the combat. RIP");
                            return false;
                        }
                        isContinue = false;
                    }
                    case "R" -> {
                        System.out.println("#################################################################");
                        System.out.println("# You run from the " + this.getObstacle().getName());
                        isContinue = false;
                    }
                    default -> {
                        System.out.println();
                    }
                }
            }
        }
        return true;
    }


    public boolean nextBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public void snakeDamageForEach() {
        Random random = new Random();
        int randomDamage = random.nextInt(4) + 3;
        getObstacle().setDamage(randomDamage);
    }

    public boolean combat(int obsNumber) {
        boolean whoseTurn = nextBoolean();
        if (whoseTurn) {
            System.out.println("# You start to the fight.");
        } else System.out.println("# " + this.getObstacle().getName() + " starts to the fight.");
        for (int i = 0; i < obsNumber; i++) {
            if (getObstacle().getName().equals("Snake")) {
                snakeDamageForEach();
            }
            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
            playerStats();
            obstacleStats();
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("-> <F>ight or <R>un: ");
                String selectCombat = Location.scanner.nextLine().toUpperCase();
                if (selectCombat.equals("F")) {
                    if (whoseTurn) { // if true, player starts to fight
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
                    } else {
                        System.out.println("# " + this.getObstacle().getName() + " hits you!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0)
                            obstacleDamage = 0;
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println();
                            System.out.println("# You hit the " + this.getObstacle().getName());
                            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        }
                    }
                } else {
                    return false;
                }
            }
            if (this.getPlayer().getHealth() <= 0) {
                return false;
            }
            if (this.getObstacle().getHealth() <= 0 && (obsNumber - i == 1)) {
                System.out.println("#################################################################");
                System.out.println("# You've killed all the enemies!");
                System.out.println("# You win " + this.getObstacle().getAward() + " coins.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("# Current money: " + this.getPlayer().getMoney());

                // Awards
                if (this.getObstacle().getName().equals("Zombie")) {
                    this.getPlayer().getInventory().setFood(true);
                } else if (this.getObstacle().getName().equals("Vampire")) {
                    this.getPlayer().getInventory().setFirewood(true);
                } else if (this.getObstacle().getName().equals("Bear")) {
                    this.getPlayer().getInventory().setWater(true);
                } else if (this.getObstacle().getName().equals("Snake")) {
                    snakeRewards();
                }
            } else if (this.getObstacle().getHealth() <= 0) {
                System.out.println("#################################################################");
                System.out.println("# You've killed " + (i + 1) + " " + this.getObstacle().getName() + " " + (obsNumber - i - 1) + " more left");
                System.out.println("# You win " + this.getObstacle().getAward() + " coins.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("# Current money: " + this.getPlayer().getMoney());
                System.out.println("#################################################################");
            }
        }
        return true;
    }

    public void snakeRewards() {
        Random random = new Random();
        int tryChance = random.nextInt(100) + 1;
        int tryChance2 = random.nextInt(100) + 1;
        if (tryChance <= 15) { // Chance to win gun
            if (tryChance2 <= 20) { // Win rifle
                if (this.getPlayer().getInventory().getWeapon().getDamage() < new Rifle().getDamage()) {
                    this.getPlayer().getInventory().setWeapon(new Rifle());
                    System.out.println("# You win Rifle");
                } else System.out.println("# You win Rifle but you already have better weapon..");
            } else if (tryChance2 <= 50) { // Win sword
                if (this.getPlayer().getInventory().getWeapon().getDamage() < new Sword().getDamage()) {
                    this.getPlayer().getInventory().setWeapon(new Sword());
                    System.out.println("# You win Sword");
                } else System.out.println("# You win Sword but you already have better weapon..");
            } else { // Win gun
                if (this.getPlayer().getInventory().getWeapon().getDamage() < new Gun().getDamage()) {
                    this.getPlayer().getInventory().setWeapon(new Gun());
                    System.out.println("# You win Gun");
                } else System.out.println("# You win Gun but you already have better weapon..");
            }
        } else if (tryChance <= 30) { // Chance to win armor
            if (tryChance2 <= 20) { // Win heavy armor
                if (this.getPlayer().getInventory().getArmor().getBlock() < new HeavyArmor().getBlock()) {
                    this.getPlayer().getInventory().setArmor(new HeavyArmor());
                    System.out.println("# You win Heavy Armor");
                } else System.out.println("# You win Heavy Armor you already have better armor..");

            } else if (tryChance2 <= 50) { // Win medium armor
                if (this.getPlayer().getInventory().getArmor().getBlock() < new MediumArmor().getBlock()) {
                    this.getPlayer().getInventory().setArmor(new MediumArmor());
                    System.out.println("# You win Medium Armor");
                } else System.out.println("# You win Medium Armor you already have better armor..");

            } else { // Win light armor
                if (this.getPlayer().getInventory().getArmor().getBlock() < new LightArmor().getBlock()) {
                    this.getPlayer().getInventory().setArmor(new LightArmor());
                    System.out.println("# You win Light Armor");
                } else System.out.println("# You win Light Armor you already have better armor..");
            }
        } else if (tryChance <= 55) { // Chance to win money
            if (tryChance2 <= 20) { // Win 10 money
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                System.out.println("# You win 10 money");
            } else if (tryChance2 <= 50) { // Win 5 money
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                System.out.println("# You win 5 money");
            } else { // Win 1 money
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                System.out.println("# You win 1 money");
            }
        } else {
            System.out.println("# You didn't get any item. Try again later..");
        }
    }


    public void afterHit() {
        System.out.println("# Your health: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + "'s health: " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats() {
        System.out.println("--------------------------------");
        System.out.println("# Character Stats:");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Armor: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Block: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Money: " + this.getPlayer().getMoney());
    }

    public void obstacleStats() {
        System.out.println("--------------------------------");
        System.out.println("# " + this.getObstacle().getName() + "Stats:");
        System.out.println("Health: " + this.getObstacle().getHealth());
        System.out.println("Damage: " + this.getObstacle().getDamage());
        if (this.getObstacle().getAward() == 0) {
            System.out.println("Award: Chance to win item!");
        } else {
            System.out.println("Award: " + this.getObstacle().getAward());
        }
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
