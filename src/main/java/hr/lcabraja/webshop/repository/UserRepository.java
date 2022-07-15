package hr.lcabraja.webshop.repository;

import hr.lcabraja.webshop.model.Role;
import hr.lcabraja.webshop.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static UserRepository singleton = null;
    private List<User> users = new ArrayList<>();

    private UserRepository() {
        users.add(new User("lcabraja", "1234567890", Role.ADMINISTRATOR));
        users.add(new User("jkustan", "kraljzvrki", Role.USER));
    }

    public static List<User> getAllUsers() {
        if (singleton == null) {
            singleton = new UserRepository();
            return getAllUsers();
        }
        return singleton.users;
    }

    public static Optional<User> getUserWithId(long userId) {
        return getAllUsers().stream().filter(u -> u.getId().equals(userId)).findFirst();
    }
}
