package pl.github.gpietrzyk.service;

import pl.github.gpietrzyk.DAO.RoleDAO;
import pl.github.gpietrzyk.DAO.TeamDAO;
import pl.github.gpietrzyk.DAO.UserDAO;
import pl.github.gpietrzyk.model.Role;
import pl.github.gpietrzyk.model.Team;
import pl.github.gpietrzyk.model.User;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserService {
    private UserDAO userDAO;
    private TeamDAO teamDAO;
    private RoleDAO roleDAO;
    private Scanner scanner;
    private User user;

    public UserService() {
        this.userDAO = new UserDAO();
        this.teamDAO = new TeamDAO();
        this.roleDAO = new RoleDAO();
        this.scanner = new Scanner(System.in);
        this.user = new User();
    }

    public void printAllUsers() {
        if (userDAO.getAllUsers().size() > 0) {
            userDAO.getAllUsers().
                    forEach(us -> System.out.println(us.printUser()));
        } else
            System.out.println("Lista użytkowników jest pusta!");
    }

    public void printUserById() {
        System.out.println("Podaj id uzytkownika, ktorego wyswietlic: ");
        int id = UserWritter.enterId();
        if (checkId(id)) {
            System.out.println(userDAO.getUserById(id).printUser());
        } else
            System.out.println("Uzytkownik o podanym ID nie istnieje!");
    }

    private boolean checkId(Integer id) {
        return getIds().stream().anyMatch(ids -> ids == id);
    }

    public void createUser() {
        if (UserWritter.enterPersonalData(user) && chooseTeam() && chooseRole()) {
            userDAO.saveUser(user);
            System.out.println("Utworzono użytkownika!");
        }
    }

    public void updateUser() {
        boolean exit = false;
        System.out.println("Podaj id uzytkownika, ktorego chcesz edytowac: ");
        int id = UserWritter.enterId();
        if (!checkId(id)) {
            System.out.println("Uzytkownik o podanym ID nie istnieje!");
            return;
        }
        user = userDAO.getUserById(id);

        while (true) {
            System.out.println("----------------------");
            System.out.println("Wybrany użytkownik: ");
            System.out.println(user.printUser());
            System.out.println();

            System.out.println("Co chcesz zaktualizowac?");
            System.out.println("1. Imie\n2. Naziwsko\n3. Wiek\n4. Druzyne\n5. Aktualizuj\n6. Rezygnuj");
            System.out.println("Podaj wybraną opcje: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Wpisz nowe imie:");
                    user.setFirstName(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Wpisz nowe nazwisko:");
                    user.setLastName(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Wpisz nowy wiek");
                    int age = UserWritter.enterAge();
                    if (age != -1) {
                        user.setAge(age);
                    }
                    break;
                case 4:
                    System.out.println("Wybierz druzyne");
                    chooseTeam();
                    break;
                case 5:
                    userDAO.updateUser(user);
                    System.out.println("Zaktualizowana użytkownika o ID: " + id);
                    return;
                case 6:
                    return;
            }
        }
    }

    public void deleteUserById() {
        System.out.println("Podaj ID uzytkownika, ktorego chcesz usunac: ");
        int id = UserWritter.enterId();
        if (checkId(id)) {
            userDAO.deleteEmployee(id);
            System.out.println("Usunięto użytkownika o ID: " + id);
        } else System.out.println("Uzytkownik o podanym ID nie istnieje!");
    }

    public void deleteAll() {
        System.out.println("Jesteś pewien? chcesz usunąc wszystkich użytkowników?");
        System.out.println("Aby potwierdzić wpisz tak, w każdym innnym wypadku użytkownicy zostaną zachowani!");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("tak")) {
            userDAO.deleteAllUsers();
            System.out.println("Usunięto wszystkich użytkowników!");
        }
    }

    private boolean chooseRole() {
        Role role1 = roleDAO.getRoleById(1);
        Role role2 = roleDAO.getRoleById(2);

        boolean exit = false;
        boolean result = true;
        int option = 0;

        do {
            System.out.println("Wybierz role uzytkownia,: 1 - admin , 2 - user, 3 -rezygnuj");
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception exception) {
                System.out.println("Błednie wprowadzona opcja");
            }

            switch (option) {
                case 1:
                    user.getRoles().add(role1);
                    exit = true;
                    System.out.println("Poprawnie wybrano role admin");
                    break;
                case 2:
                    user.getRoles().add(role2);
                    exit = true;
                    System.out.println("Poprawie wybrano role user!");
                    break;

                case 3:
                    exit = true;
                    System.out.println("Nie udało się przypisac roli");
                    result = false;
            }
        } while (!exit);
        return result;
    }

    private boolean chooseTeam() {
        Team team1 = teamDAO.getTeamById(1);
        Team team2 = teamDAO.getTeamById(2);

        boolean exit = false;
        boolean result = true;
        int option = 0;

        do {
            System.out.println("Wybierz druzyne: 1 - Cola , 2 - Pepsi, 3 -rezygnuj");
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception exception) {
                System.out.println("Blednie wpisana opcja!");
            }

            switch (option) {
                case 1:
                    user.setTeam(team1);
                    exit = true;
                    System.out.println("Poprawnie wybrano druzyne Pepsi!");
                    break;
                case 2:
                    user.setTeam(team2);
                    exit = true;
                    System.out.println("Poprawie wybrano druzyne Cola!");
                    break;

                case 3:
                    exit = true;
                    System.out.println("Nie udało się przypisac druzyny");
                    result = false;
            }
        } while (!exit);

        return result;
    }

    private List<Integer> getIds() {
        return userDAO.getAllUsers().stream()
                .map(User::getUserId)
                .collect(Collectors.toList());
    }
}
