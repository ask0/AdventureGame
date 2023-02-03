import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("# Welcome to the AdventureGame");
        System.out.print("-> Enter your name: ");
        String name = scanner.nextLine();
        Player player = new Player(name);
        player.selectChar();

        Location location = null;
        boolean isQuit = false;
        while (!isQuit) {
            boolean isContinue = true;
            while (isContinue) {
                if (player.getInventory().isFood() && player.getInventory().isFirewood() && player.getInventory().isWater()) {
                    System.out.println("# You've killed all the enemies in the game. Try the Quarry(6)");
                    isContinue = false;

                }
                System.out.println("#################################################################");
                player.printInfo();
                System.out.println("#################################################################");
                System.out.println("# Locations");
                System.out.println("1- Safe House");
                System.out.println("2- Tool Store");
                System.out.println("3- Cave");
                System.out.println("4- Forest");
                System.out.println("5- River");
                System.out.println("6- Quarry");
                System.out.println("0- Quit Game");
                System.out.print("-> Chose where you want to go: ");

                int selectLoc = scanner.nextInt();
                switch (selectLoc) {
                    case 0 -> {
                        isQuit = true;
                        isContinue = false;
                        System.out.println("# Game Over");
                    }
                    case 1 -> {
                        location = new SafeHouse(player);
                        isContinue = false;
                    }
                    case 2 -> {
                        location = new ToolStore(player);
                        isContinue = false;
                    }
                    case 3 -> {
                        location = new Cave(player);
                        isContinue = false;
                    }
                    case 4 -> {
                        location = new Forest(player);
                        isContinue = false;
                    }
                    case 5 -> {
                        location = new River(player);
                        isContinue = false;
                    }
                    case 6 -> {
                        location = new Quarry(player);
                        isContinue = false;
                    }
                    default -> System.out.println("!! Wrong input. Try again. ");
                }

            }

            if (isQuit)
                break;
            if (!location.onLocation()) {
                System.out.println("# Game over!");
                break;
            }

        }
    }
}
