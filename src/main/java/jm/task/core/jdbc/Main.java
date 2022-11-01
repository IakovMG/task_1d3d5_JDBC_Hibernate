package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        User user1 = new User("Mickey", "Mouse", (byte) 94);
        User user2 = new User("Spike", "Spiegel", (byte) 91);
        User user3 = new User("Donald", "Duck", (byte) 88);
        User user4 = new User("Khiro", "Khamada", (byte) 22);


        List<User> uList = new ArrayList<>();
        uList.add(user1);
        uList.add(user2);
        uList.add(user3);
        uList.add(user4);


        uList.forEach(x -> {
            userService.saveUser(x.getName(), x.getLastName(), x.getAge());
            System.out.printf("Пользователь: %s %s добавлен в базу данных\n", x.getName(), x.getLastName());
        });
        userService.getAllUsers().forEach(x -> System.out.println(x));
        userService.cleanUsersTable();
        userService.dropUsersTable();
        userService.closeConnection();


    }
}
