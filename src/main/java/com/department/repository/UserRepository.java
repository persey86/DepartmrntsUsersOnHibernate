package com.department.repository;

import com.department.exceptions.RepositoryException;
import com.department.models.User;

import java.util.List;

/**
 * Created on 15.04.2017.
 */
public interface UserRepository extends GeneralRepository<User, Integer, RepositoryException>{

    List<User> getUsersWhereDepartmentId(Integer departmentId) throws RepositoryException;

    User getByEmail(String email) throws RepositoryException;
}
