import Armors.Armor;
import Armors.HeavyArmor;
import Armors.LightArmor;
import Armors.MediumArmor;
import Weapons.Gun;
import Weapons.Rifle;
import Weapons.Sword;
import Weapons.Weapon;

public class ToolStore extends NormalLoc {
    private Weapon[] weapons = {new Gun(), new Sword(), new Rifle()};
    private Armor[] armors = {new LightArmor(), new MediumArmor(), new HeavyArmor()};

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        boolean quit = false;
        while (!quit) {
            System.out.println("#################################################################");
            System.out.println("# Welcome to the Tool Store");
            System.out.println("1- Weapons");
            System.out.println("2- Armors");
            System.out.println("3- Quit Tool Store");
            System.out.print("-> Your choice: ");
            int selectCase = scanner.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("-> Wrong input. Try again");
                selectCase = scanner.nextInt();
            }
            switch (selectCase) {
                case 1 -> {
                    printWeapon();
                    buyWeapon();
                }
                case 2 -> {
                    printArmor();
                    selectArmor();
                }
                case 3 -> {
                    quit = true;
                    System.out.println("# You've quit from the store!");
                }
            }
        }

        return true;
    }

    // Print weapons
    private void printWeapon() {
        System.out.println("#######################    Weapons    ###########################");
        for (Weapon weapon : weapons) {
            System.out.println("# ID: " + weapon.getId() + "\tWeapon: " + weapon.getName() + "\tDamage: " + weapon.getDamage() + "\tMoney: " + weapon.getMoney());
        }
        System.out.println("#################################################################");
    }


    // Buy weapon
    private void buyWeapon() {
        boolean isContinue = true;
        while (isContinue) {
            System.out.print("-> Your choice [ Go back <-1> ]: ");
            int selectWeapon = scanner.nextInt();
            if (selectWeapon == -1) {
                isContinue = false;
                continue;
            }
            while (selectWeapon < 1 || selectWeapon > weapons.length) {
                System.out.print("-> Wrong input. Try again: ");
                selectWeapon = scanner.nextInt();
            }

            Weapon selectedWeapon = null;
            switch (selectWeapon) {
                case 1 -> {
                    selectedWeapon = new Gun();
                }
                case 2 -> {
                    selectedWeapon = new Sword();
                }
                case 3 -> {
                    selectedWeapon = new Rifle();
                }
            }
            if (selectedWeapon.getMoney() > this.getPlayer().getMoney()) {
                System.out.println("# Not enough money!");
            } else {
                System.out.println("# Purchased [" + selectedWeapon.getName() + "]");
                int balance = this.getPlayer().getMoney() - selectedWeapon.getMoney();
                this.getPlayer().setMoney(balance);
                System.out.println("# Your balance: " + this.getPlayer().getMoney());
                System.out.println("# Your last weapon: " + this.getPlayer().getInventory().getWeapon().getName());
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
                System.out.println("# Your current weapon: " + this.getPlayer().getInventory().getWeapon().getName());
            }
        }

    }

    private void printArmor() {
        System.out.println("########################    Armors    ###########################");
        for (Armor armor : armors) {
            System.out.println("# ID: " + armor.getId() + "\tWeapon: " + armor.getName() + "\tBlock: " + armor.getBlock() + "\tMoney: " + armor.getMoney());
        }
        System.out.println("#################################################################");
    }

    private void selectArmor() {
        boolean isContinue = true;
        while (isContinue) {
            System.out.print("-> Your choice [ Go back <-1> ]: ");
            int selectArmor = scanner.nextInt();
            if (selectArmor == -1) {
                isContinue = false;
                continue;
            }
            while (selectArmor < 1 || selectArmor > armors.length) {
                System.out.print("-> Wrong input. Try again: ");
                selectArmor = scanner.nextInt();
            }

            Armor selectedArmor = null;
            switch (selectArmor) {
                case 1 -> {
                    selectedArmor = new LightArmor();
                }
                case 2 -> {
                    selectedArmor = new MediumArmor();
                }
                case 3 -> {
                    selectedArmor = new HeavyArmor();
                }
            }
            if (selectedArmor.getMoney() > this.getPlayer().getMoney()) {
                System.out.println("# Not enough money!");
            } else {
                System.out.println("# Purchased [" + selectedArmor.getName() + "]");
                int balance = this.getPlayer().getMoney() - selectedArmor.getMoney();
                this.getPlayer().setMoney(balance);
                System.out.println("# Your balance: " + this.getPlayer().getMoney());
                System.out.println("# Your last armor: " + this.getPlayer().getInventory().getArmor().getName());
                this.getPlayer().getInventory().setArmor(selectedArmor);
                System.out.println("# Your current armor: " + this.getPlayer().getInventory().getArmor().getName());
            }
        }
    }


    public void menu() {

    }

    public void buy() {

    }
}
