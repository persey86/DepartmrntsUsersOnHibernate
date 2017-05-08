package com.department.services.impl;

import com.department.exceptions.AppException;
import com.department.exceptions.AppValidationException;
import com.department.exceptions.RepositoryException;
import com.department.models.User;
import com.department.repository.UserRepository;
import com.department.repository.implHibernate.UserRepositoryImpl;
import com.department.services.UserService;
import com.department.validation.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 16.04.2017.
 */
// Autoscan paths , component by service-layer
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository; //= new UserRepositoryImpl();

    @Autowired
    private CustomValidator customValidator; //= new CustomValidator();

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllEntities() throws AppException {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findOneEntity(Integer id) throws AppException {
        return userRepository.findOne(id);
    }

    @Override
    public User saveEntityWithValidation(User entity) throws AppException {
        validate(entity);
        return userRepository.save(entity);
    }

    @Override
    public void deleteEntityWithValidation(Integer id) throws AppException {
        userRepository.delete(id);
    }

    private void validate(Object entity) throws AppValidationException, RepositoryException {

        Map<String, String> valid = new HashMap<>();

        this.customValidator.validateEntity(entity, valid);
        if (valid.size() > 0) {
            throw new AppValidationException("element not valid for user",valid);
        }
    }
}
