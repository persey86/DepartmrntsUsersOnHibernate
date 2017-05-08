package com.department.repository.implHibernate;

import com.department.exceptions.RepositoryException;
import com.department.models.Department;
import com.department.repository.DepartmentRepository;
import com.department.utils.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Created on 25.04.17.
 */
public class DepartmentRepositoryImpl implements DepartmentRepository {

private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

    @Override
    public List<Department> findAll() throws RepositoryException {
        List<Department> departments;
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from departments");
            departments = query.list();
        }catch (HibernateException e){
            throw new RepositoryException("Can't get all departments",e);
        }
        return departments;
    }

    @Override
    public Department findOne(Integer id) throws RepositoryException {
        Department department;

        try (Session session = sessionFactory.openSession()){
            department = new Department();
            Query query = session.createQuery("from departments WHERE id = :id");
            query.setParameter("id",id);
            if (query.uniqueResult() != null){
                department = (Department) query.uniqueResult();
            }
        }catch (HibernateException e){
            throw new RepositoryException("Can't get department by id", e);
        }
        return department;
    }

    @Override
    public Department save(Department department) throws RepositoryException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            department.setName(department.getName());
            department.setCreated(new Date());

            session.saveOrUpdate(department);

             transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Save in transaction has finished by failure", e);
        }
        return department;
    }

    @Override
    public void delete(Integer id) throws RepositoryException {
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(findOne(id));
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public Department getByName(String name) throws RepositoryException {
        Department department;

        try (Session session = sessionFactory.openSession()){
            department = new Department();
            Query query = session.createQuery("from departments where name = :name");
            query.setParameter("name", name);
            if (query.uniqueResult()!=null)
            department = (Department) query.uniqueResult();
        }catch (HibernateException e){
            throw new RepositoryException("Method 'getByName' has finished by failure",e);
        }
        return department;
    }
}
