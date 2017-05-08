package com.department.repository.implHibernate;

import com.department.exceptions.RepositoryException;
import com.department.models.User;
import com.department.repository.UserRepository;
import com.department.utils.HibernateUtility;
import org.hibernate.*;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 25.04.17.
 */
//  Realization DAO-template
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;  //= HibernateUtility.getSessionFactory();


    @Override
    public List<User> findAll() throws RepositoryException {
        List<User> users;

        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from user");
            users = query.list();
        }catch (HibernateException e){
            throw new RepositoryException("Can't get all users list",e);
        }
        return users;
    }

    @Override
    public User findOne(Integer Id) throws RepositoryException {
        User user = new User();

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM user WHERE id= :id");
            query.setParameter("id", Id);
            if (query.uniqueResult() != null)
                user = (User) query.uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException("Can't resolve method findOne", e);
        }
        return user;
    }

    @Override
    public User save(User user) throws RepositoryException {
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            session.saveOrUpdate(user);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            throw new RepositoryException("Error in the moment of saving user",e);
        }
        return user;
    }

    @Override
    public void delete(Integer id) throws RepositoryException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()){
            transaction=session.beginTransaction();
            session.delete(findOne(id));
            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null)transaction.rollback();
            throw new RepositoryException("Error while deleting user", e);
        }
    }

    @Override
    public List<User> getUsersWhereDepartmentId(Integer departmentId) throws RepositoryException {
        List<User> users;

        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("from user where department.id= :department_id");
            query.setParameter("department_id", departmentId);
            users = query.list();
        }catch (HibernateException e){
            throw new RepositoryException("Error while getting users from selected department by Id", e);
        }
        return users;
    }

    @Override
    public User getByEmail(String email) throws RepositoryException {
        User user = new User();

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("from user where email= :email");
            query.setParameter("email", email);
            if (query.uniqueResult() != null)
                user = (User) query.uniqueResult();
            session.getTransaction().commit();
        }catch (HibernateException e){
            throw new RepositoryException("Error while getting user by e-mail", e);
        }
        return user;
    }
}
