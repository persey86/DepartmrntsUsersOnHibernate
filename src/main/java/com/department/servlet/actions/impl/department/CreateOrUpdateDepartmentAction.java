package com.department.servlet.actions.impl.department;

import com.department.exceptions.AppException;
import com.department.exceptions.AppValidationException;
import com.department.models.Department;
import com.department.services.DepartmentService;
import com.department.servlet.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created on 19.04.2017.
 */
//Autodetect classes with autoscan path like class-controller and Return this name from bean if this name is exist
@Component(value = "/createOrUpdateDepartmentAction")
public class CreateOrUpdateDepartmentAction implements Action {

    @Autowired
    private DepartmentService departmentService; // = new DepartmentServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppException, IOException, ServletException {

        String departmentIdString = request.getParameter("departmentId");

        Integer departmentId = null;
        if (!departmentIdString.equals("")) {
            departmentId = Integer.parseInt(departmentIdString);
        }
        String name = request.getParameter("departmentName");

        Department department = new Department();
        department.setId(departmentId);
        department.setName(name);
        department.setCreated(new Date());


        try {
            // saving and redirect to main page
            departmentService.saveEntityWithValidation(department);

            response.sendRedirect("/");
        } catch (AppValidationException e) {

            Map<String, String> mapErr = e.getMapErr();

            request.setAttribute("department", department);

            // show error message on index page
            request.setAttribute("mapErr", mapErr);

            request.getRequestDispatcher("/WEB-INF/" + "createOrUpdateDepartmentForm" + ".jsp").forward(request,response);
        }
    }
}
