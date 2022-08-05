package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        userServiceImpl.createUsersTable();

        User user1 = new User("Bill", "Denbrough", (byte) 11);
        userServiceImpl.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

        User user2 = new User("Ben", "Hanscom", (byte) 11);
        userServiceImpl.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        User user3 = new User("Bev", "Marsh", (byte) 12);
        userServiceImpl.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        User user4 = new User("Richie", "Tozier", (byte) 12);
        userServiceImpl.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> userList = userServiceImpl.getAllUsers();
        System.out.println(userList);
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
