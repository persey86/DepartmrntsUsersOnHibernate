package com.department.services.impl;

import com.department.exceptions.AppException;
import com.department.exceptions.AppValidationException;
import com.department.exceptions.RepositoryException;
import com.department.models.Department;
import com.department.repository.DepartmentRepository;
import com.department.repository.implHibernate.DepartmentRepositoryImpl;
import com.department.services.DepartmentService;
import com.department.validation.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 15.04.2017.
 */
// Autoscan paths , component by service-layer
@Service
public class DepartmentServiceImpl implements DepartmentService {


//dependency injection
    @Autowired
    private DepartmentRepository departmentRepository;  //= new DepartmentRepositoryImpl();

    @Autowired
    private CustomValidator customValidator;            //= new CustomValidator();

    @Override
//    Transaction attributes for ...
    @Transactional(readOnly = true)
    public List<Department> findAllEntities() throws AppException {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Department findOneEntity(Integer id) throws AppException {
        return departmentRepository.findOne(id);
    }

    @Override
    public Department saveEntityWithValidation(Department entity) throws AppException {
        validate(entity);
        return departmentRepository.save(entity);
    }

    @Override
    public void deleteEntityWithValidation(Integer id) throws AppException {
        departmentRepository.delete(id);
    }

    private void validate(Object entity) throws AppValidationException, RepositoryException {

        Map<String, String> valid = new HashMap<>();

        this.customValidator.validateEntity(entity, valid);
        if (valid.size() > 0) {
            throw new AppValidationException("element not valid for department",valid);
        }
    }


}
