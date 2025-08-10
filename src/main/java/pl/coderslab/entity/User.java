package pl.coderslab.entity;

public class User {

    private int userId;
    private String name;
    private String emailAddress;
    private String hashedPassword;

    public User() {
        this.userId = 0;
    }

    public User(String name, String emailAddress, String hashedPassword) {
        this.userId = 0;
        this.name = name;
        this.emailAddress = emailAddress;
        this.hashedPassword = hashedPassword;
    }

    public User(int userId, String name, String emailAddress, String hashedPassword) {
        this.userId = userId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.hashedPassword = hashedPassword;
    }

    // Nové get/set metody podle aktuálních názvů
    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String password) {
        this.hashedPassword = password;
    }

    // Alias metody pro kompatibilitu s UserDao
    public int getId() {
        return getUserId();
    }

    public void setId(int id) {
        setUserId(id);
    }

    public String getUserName() {
        return getName();
    }

    public void setUserName(String username) {
        setName(username);
    }

    public String getEmail() {
        return getEmailAddress();
    }

    public void setEmail(String email) {
        setEmailAddress(email);
    }

    public String getPassword() {
        return getHashedPassword();
    }

    public void setPassword(String password) {
        setHashedPassword(password);
    }
}
