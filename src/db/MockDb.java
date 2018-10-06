package db;

import model.Person;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDb {
    public static final List<User> users = new ArrayList<>();
    public static final Map<Integer, Person> personMap = new HashMap<>();
    public static final Map<Integer, Map<Integer, Person>> tree = new HashMap<>();
    static {
        users.add(new User("Gasimzade", "ali123", "Man", 1, 8478));
    }
}