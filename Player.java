import Characters.*;
import Weapons.Weapon;

import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int money;
    private String name;
    private Scanner scanner = new Scanner(System.in);
    private Inventory inventory;
    private int defaultHealth;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();

    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getDamage() {
        return damage;
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health <= 0)
            health = 0;
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }


    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }

    public void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("#####################    Characters    ##########################");
        for (GameChar gameChar : charList) {
            System.out.println("# ID: " + gameChar.getId() + "\tCharacter: " + gameChar.getName() + "\tDamage: " + gameChar.getDamage() + "\tHealth: " + gameChar.getHealth() + "\tMoney: " + gameChar.getMoney());
        }
        System.out.println("#################################################################");
        boolean isContinue = true;
        while (isContinue) {
            System.out.print("-> Choose your character: ");
            int selectChar = scanner.nextInt();
            switch (selectChar) {
                case 1 -> {
                    initPlayer(new Samurai());
                    isContinue = false;
                }
                case 2 -> {
                    initPlayer(new Archer());
                    isContinue = false;
                }
                case 3 -> {
                    initPlayer(new Knight());
                    isContinue = false;
                }
                default -> {
                    System.out.println("!! Wrong input. Try again!");
                }
            }
        }
        // Chosen character
        System.out.println("# Character selected!");
        System.out.println("# Character: " + this.getName() + " Damage: " + this.getDamage() + " Health: " + this.getHealth() + " Money: " + this.getMoney());

    }

    public void printInfo() {
        System.out.println("# Stats | Character: " + this.getName() + "\tDamage: " + this.getDamage() + "\tHealth: " + this.getHealth() + "\tMoney: " + this.getMoney());
        System.out.println("# Stats | Weapon: " + this.getInventory().getWeapon().getName() + "\tDamage: " + this.getInventory().getWeapon().getDamage() + "\tMoney: " + this.getInventory().getWeapon().getMoney());
        System.out.println("# Stats | Armor: " + this.getInventory().getArmor().getName() + "\tBlock: " + this.getInventory().getArmor().getBlock() + "\tMoney: " + this.getInventory().getArmor().getMoney());
        System.out.println("# Total Stats: " + "\tDamage: " + this.getTotalDamage() + "\tBlock: " + this.getInventory().getArmor().getBlock() + "\tHealth: " + this.getHealth() + "\tMoney: " + this.getMoney());
    }

    public void initPlayer(GameChar gameChar) {
        this.setName(gameChar.getName());
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setDefaultHealth(gameChar.getHealth());
    }

}
