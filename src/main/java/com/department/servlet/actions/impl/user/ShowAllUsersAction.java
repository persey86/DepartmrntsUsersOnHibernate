package com.department.servlet.actions.impl.user;

import com.department.exceptions.RepositoryException;
import com.department.models.User;
import com.department.repository.implHibernate.UserRepositoryImpl;
import com.department.servlet.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created on 06.04.17.
 */
@Component(value = "/allUsers")
public class ShowAllUsersAction implements Action {
    @Autowired
    UserRepositoryImpl userRepositoryImpl;// = new UserRepositoryImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws RepositoryException, ServletException, IOException {

        Integer departmentId = Integer.parseInt(request.getParameter("id"));

        List<User> allUsers = userRepositoryImpl.getUsersWhereDepartmentId(departmentId);

        request.setAttribute("allUsers", allUsers);

        request.getRequestDispatcher("/WEB-INF/" + "allUsers" + ".jsp").forward(request, response);

    }
}
