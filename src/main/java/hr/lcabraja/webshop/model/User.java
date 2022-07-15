package hr.lcabraja.webshop.model;

public class User {
    private static Long lastId = 0L;
    private Long id;
    private String username;
    private String password;
    private Role role;

    public User() {
    }

    public User(String username, String password, Role role) {
        this.id = lastId++;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
