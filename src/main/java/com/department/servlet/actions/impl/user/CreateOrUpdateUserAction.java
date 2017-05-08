package com.department.servlet.actions.impl.user;

import com.department.exceptions.AppException;
import com.department.exceptions.AppValidationException;
import com.department.models.User;
import com.department.services.DepartmentService;
import com.department.services.UserService;
import com.department.servlet.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created on 19.04.2017.
 */
//Autodetect classes with autoscan path like class-controller and Return this name from bean if this name is exist
// equals HttpServlet
@Component(value = "/createOrUpdateUserAction")
public class CreateOrUpdateUserAction  implements Action {

@Autowired
    private UserService userService;
@Autowired
    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppException, ServletException, IOException, ParseException {

        User user = new User();
        String userIdString = request.getParameter("userId");
        Integer userId = null;
        if (!userIdString.equals("")) {
            userId = Integer.parseInt(userIdString);
        }

        String nameUser = request.getParameter("userName");
        String surnameUser = request.getParameter("userSurname");
        String emailUser = request.getParameter("userEmail");
        Double userSalary = Double.parseDouble(request.getParameter("userSalary"));
        Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String birthdayUser = request.getParameter("userBirthday");


        user.setId(userId);
        user.setName(nameUser);
        user.setSurname(surnameUser);
        user.setEmail(emailUser);
        user.setBirthday((new SimpleDateFormat("yyyy-mm-dd")).parse(birthdayUser));
        user.setSalary(userSalary);
        user.setCreated(new Date());
        user.setDepartment(departmentService.findOneEntity(departmentId));

        try {

            //  update user
            userService.saveEntityWithValidation(user);

            response.sendRedirect("/allUsers?id=" +  departmentId);

        }catch (AppValidationException e){
            Map<String,String> mapErr = e.getMapErr();
            request.setAttribute("mapErr", mapErr);

            request.setAttribute("allDepartments", departmentService.findAllEntities());
            request.setAttribute("user", user);

            request.getRequestDispatcher("/WEB-INF/" + "createOrUpdateUserForm" + ".jsp").forward(request,response);
        }
    }
}