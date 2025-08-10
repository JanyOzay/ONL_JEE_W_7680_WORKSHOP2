package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_QUERY =
            "SELECT * FROM users";

    private Connection conn;

    // Konstruktor přijímá aktivní připojení k databázi
    public UserDao(Connection conn) {
        this.conn = conn;
    }

    // Hashování hesla pomocí BCrypt
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Vytvoření nového uživatele v DB
    public User create(User user) throws SQLException {
        try (PreparedStatement preStmt = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preStmt.setString(1, user.getUserName());
            preStmt.setString(2, user.getEmail());
            preStmt.setString(3, hashPassword(user.getPassword()));

            int affectedRows = preStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Vytvoření uživatele se nezdařilo, nebyl ovlivněn žádný řádek.");
            }

            try (ResultSet rs = preStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                    return user;
                } else {
                    throw new SQLException("Vytvoření uživatele se nezdařilo, ID nebylo získáno.");
                }
            }
        }
    }

    // Načtení uživatele podle ID
    public User read(int userId) throws SQLException {
        try (PreparedStatement preStmt = conn.prepareStatement(READ_USER_QUERY)) {
            preStmt.setInt(1, userId);
            try (ResultSet rs = preStmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserName(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }
        }
        return null;
    }

    // Aktualizace existujícího uživatele
    public void update(User user) throws SQLException {
        try (PreparedStatement preStmt = conn.prepareStatement(UPDATE_USER_QUERY)) {
            preStmt.setString(1, user.getUserName());
            preStmt.setString(2, user.getEmail());

            // Zkontrolujeme, zda je heslo již zahashované (začíná "$2a$")
            String pwd = user.getPassword();
            if (!pwd.startsWith("$2a$")) {
                pwd = hashPassword(pwd);
            }
            preStmt.setString(3, pwd);

            preStmt.setInt(4, user.getId());

            preStmt.executeUpdate();
        }
    }

    // Smazání uživatele podle ID
    public void delete(int userId) throws SQLException {
        try (PreparedStatement preStmt = conn.prepareStatement(DELETE_USER_QUERY)) {
            preStmt.setInt(1, userId);
            preStmt.executeUpdate();
        }
    }

    // Načtení všech uživatelů z databáze
    public User[] findAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preStmt = conn.prepareStatement(FIND_ALL_QUERY);
             ResultSet rs = preStmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
        }
        return userList.toArray(new User[0]);
    }
}
