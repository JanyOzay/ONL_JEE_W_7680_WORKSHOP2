package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainDaoTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/workshop2?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "kickflip88";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            UserDao userDao = new UserDao(connection);

            // Vytvoření uživatele Viktor Hazard
            User userToAdd = new User("Viktor Hazard", "viktor.hazard@gmail.com", "hovnokleslo");
            User savedUser = userDao.create(userToAdd);
            System.out.println("Uživatel přidán s ID: " + savedUser.getId());

            // Načtení uživatele podle ID
            User fetchedUser = userDao.read(savedUser.getId());
            if (fetchedUser != null) {
                System.out.println("Načtený uživatel: " + fetchedUser.getUserName() + ", e-mail: " + fetchedUser.getEmail());
            } else {
                System.out.println("Uživatel s tímto ID nebyl nalezen.");
            }

            // Úprava uživatele (změna jména a hesla)
            fetchedUser.setUserName("Viktor Updated");
            fetchedUser.setPassword("novyHeslo456");
            userDao.update(fetchedUser);
            System.out.println("Data uživatele byla úspěšně změněna.");

            // Výpis všech uživatelů v DB
            System.out.println("Všichni uživatelé v databázi:");
            User[] users = userDao.findAll();
            for (User u : users) {
                System.out.printf("ID: %d | Uživatelské jméno: %s | Email: %s%n", u.getId(), u.getUserName(), u.getEmail());
            }

            // Odstranění uživatele
            userDao.delete(savedUser.getId());
            System.out.println("Uživatel s ID " + savedUser.getId() + " byl odstraněn.");

        } catch (SQLException e) {
            System.err.println("Chyba při komunikaci s databází: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
