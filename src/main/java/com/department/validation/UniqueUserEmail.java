package com.department.validation;

import com.department.exceptions.RepositoryException;
import com.department.models.User;
import com.department.repository.UserRepository;
import com.department.repository.implHibernate.UserRepositoryImpl;
import net.sf.oval.constraint.CheckWithCheck;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created on 20.04.17.
 */

@Component
public class UniqueUserEmail implements CheckWithCheck.SimpleCheck {

    @Autowired
    private UserRepository userRepository;      // = new UserRepositoryImpl();
    private static final Logger LOGGER = Logger.getLogger(UniqueUserEmail.class);

    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {

        try {
            User validatedEmployee = (User) validatedObject;
            User userFromDataBase = userRepository.getByEmail(value.toString());
            String email = userFromDataBase.getEmail();
            if (!value.equals(email))
                return true;
            else if (userFromDataBase.getId() == validatedEmployee.getId())
                return true;

        } catch (RepositoryException e) {
//            LOGGER.error("Error while getting unique user e-mail", e);
        }
        return false;
    }
}