package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        // создание таблицы
        userDaoHibernate.createUsersTable();

        // добавление пользователей
        userDaoHibernate.saveUser("Ivan", "Ivanov", (byte) 45);
        userDaoHibernate.saveUser("Sergey", "Petrov", (byte) 27);
        userDaoHibernate.saveUser("Ivan", "Ivov", (byte) 62);
        userDaoHibernate.saveUser("Vova", "Sidorov", (byte) 12);

        // вывод всех пользователей
        List<User> users = userDaoHibernate.getAllUsers();
        users.forEach(System.out::println);

        // удаление пользователя по id
        userDaoHibernate.removeUserById(2L);

        // вывод оставшихся пользователей
        users = userDaoHibernate.getAllUsers();
        users.forEach(System.out::println);

        // очистка таблицы
        userDaoHibernate.cleanUsersTable();

        //вывод пользователей
        users = userDaoHibernate.getAllUsers();
        users.forEach(System.out::println);

        userDaoHibernate.dropUsersTable();
    }
}

