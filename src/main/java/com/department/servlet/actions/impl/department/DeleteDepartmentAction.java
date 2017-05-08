package com.department.servlet.actions.impl.department;

import com.department.exceptions.AppException;
import com.department.services.DepartmentService;
import com.department.servlet.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 03.04.17.
 */
//Autodetect classes with autoscan path like class-controller and Return this name from bean if this name is exist
// equals HttpServlet
@Component(value = "/deleteDepartment")
public class DeleteDepartmentAction implements Action {

    @Autowired
    private DepartmentService departmentService; // = new DepartmentServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppException, IOException {

        String idDeletedDepartment = request.getParameter("departmentId");
        Integer intId = Integer.parseInt(idDeletedDepartment);
        this.departmentService.deleteEntityWithValidation(intId);

        response.sendRedirect("/");
    }
}
