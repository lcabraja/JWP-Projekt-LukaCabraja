package hr.lcabraja.webshop.model;

import java.time.LocalDateTime;

public class Audit {
    private static Long lastId = 0L;
    private Long id;
    private User user;
    private LocalDateTime createdAt;
    private String ipAddress;

    public Audit(User user, LocalDateTime createdAt, String ipAddress) {
        this.id = lastId++;
        this.user = user;
        this.createdAt = createdAt;
        this.ipAddress = ipAddress;
    }

    public Audit() {
        this.id = lastId++;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
