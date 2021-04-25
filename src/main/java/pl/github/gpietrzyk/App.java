package pl.github.gpietrzyk;

import pl.github.gpietrzyk.service.UserService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        do {
            UserService userService = new UserService();
            System.out.println("-------------- Menu --------------");
            System.out.println("1. Wyświetl wszystkich użytkowników");
            System.out.println("2. Wyświetl użytkownika");
            System.out.println("3. Stwórz użytkownika");
            System.out.println("4. Zaktualizuj użytkownika");
            System.out.println("5. Usuń użytkownika");
            System.out.println("6. Usuń wszystkich użytkowników");
            System.out.println("7. Zakończ");
            System.out.println("-------------- Wybierz działanie --------------");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    userService.printAllUsers();
                    break;
                case 2:
                    userService.printUserById();
                    break;
                case 3:
                    userService.createUser();
                    break;
                case 4:
                    userService.updateUser();
                    break;
                case 5:
                    userService.deleteUserById();
                    break;
                case 6:
                    userService.deleteAll();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Błędnie wporwadzona opcja!");
            }
        } while (!exit);
    }
}
