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
                System.out.println("#################################################################");
                player.printInfo();

                System.out.println("# Locations");
                System.out.println("1- Safe House");
                System.out.println("2- Tool Store");

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