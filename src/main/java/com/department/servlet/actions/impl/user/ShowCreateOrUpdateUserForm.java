package com.department.servlet.actions.impl.user;

import com.department.repository.DepartmentRepository;
import com.department.repository.UserRepository;
import com.department.servlet.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 19.04.2017.
 */
@Component(value = "/createOrUpdateUser")
public class ShowCreateOrUpdateUserForm implements Action {

    @Autowired
    private DepartmentRepository departmentRepositoryImpl;
    @Autowired
    private UserRepository userRepositoryImpl;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setAttribute("allDepartments", departmentRepositoryImpl.findAll());

        String userIdString = request.getParameter("userId");

        if (userIdString != null) {
            Integer userId = Integer.parseInt(userIdString);
            request.setAttribute("user", userRepositoryImpl.findOne(userId));
        }
        request.getRequestDispatcher("/WEB-INF/" + "createOrUpdateUserForm" + ".jsp").forward(request, response);
    }
}
