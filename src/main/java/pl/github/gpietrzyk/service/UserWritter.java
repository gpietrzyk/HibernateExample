package pl.github.gpietrzyk.service;

import pl.github.gpietrzyk.model.User;

import java.util.Scanner;

public class UserWritter {
    private static Scanner scanner = new Scanner(System.in);

    public static boolean enterPersonalData(User user) {
        try {
            System.out.println("Podaj imie uzytkownika");
            user.setFirstName(scanner.nextLine());

            System.out.println("Podaj nazwisko uzytkownika");
            user.setLastName(scanner.nextLine());

            System.out.println("Podaj wiek uzytkownika");
            user.setAge(scanner.nextInt());
            scanner.nextLine();
        } catch (Exception exception) {
            System.out.println("Błądnie wprowadzony wiek");
            return false;
        }
        return true;
    }

    public static int enterId() {
        int id = 0;
        try {
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Błednie wprowadzone ID!");
        }
        return id;
    }


    public static int enterAge() {
        int upAge;
        try {
            upAge = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            upAge = -1;
            System.out.println("Blad");
        }
        return upAge;
    }

}
