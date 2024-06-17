package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = """   
                        CREATE TABLE IF NOT EXISTS users
                        (id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(45),
                        lastname VARCHAR(45),
                        age SMALLINT 
                    );
                    """;
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Создана таблица users");
        } catch (Exception e) {
            System.out.println("Таблица users не создана");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Удалена таблица users");
        } catch (Exception e) {
            System.out.println("Таблица users не удалена");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("Добавлен пользователь " + name + " " + lastName);
        } catch (Exception e) {
            System.out.println("Пользователь " + name + " " + lastName + "не добавлен");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            System.out.println("Пользователь " + id + " удалён");
        } catch (Exception e) {
            System.out.println("Пользователь " + id + " не удалён");
        }

    }

    @Override
    public List getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User").list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete from User");
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица users очищена");
        } catch (Exception e) {
            System.out.println("Таблица не очищена");
        }
    }
}
