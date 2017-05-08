package com.department.servlet.actions.impl.user;

import com.department.exceptions.AppException;
import com.department.repository.implHibernate.UserRepositoryImpl;
import com.department.services.UserService;
import com.department.servlet.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 06.04.17.
 */
@Component(value = "/deleteUser")
public class DeleteUserAction implements Action {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppException, IOException {

        String idDeletingUser = request.getParameter("userId");
        Integer intId = Integer.parseInt(idDeletingUser);
        this.userService.deleteEntityWithValidation(intId);
        userRepositoryImpl.delete(intId);

        response.sendRedirect("/");
    }
}

