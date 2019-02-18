package dto;

public class User implements Identifiable {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private int id;

    public User(String firstName, String lastName, String login, String password) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.id = login.hashCode();
    }

    public User(String firstName, String lastName, String login, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.id = id;
    }

    public User(String firstName, String lastName, String login) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.id = login.hashCode();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", id=" + id +
                '}';
    }
}
