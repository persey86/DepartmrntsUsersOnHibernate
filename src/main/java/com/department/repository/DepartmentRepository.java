package com.department.repository;

import com.department.exceptions.RepositoryException;
import com.department.models.Department;

/**
 * Created on 15.04.2017.
 */
public interface DepartmentRepository extends GeneralRepository <Department, Integer, RepositoryException>{

    Department getByName(String name) throws RepositoryException;
}
