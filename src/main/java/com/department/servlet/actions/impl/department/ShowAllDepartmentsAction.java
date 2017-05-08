package com.department.servlet.actions.impl.department;

import com.department.exceptions.AppException;
import com.department.models.Department;
import com.department.services.DepartmentService;
import com.department.servlet.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Created on 02.04.2017.
 */
@Component(value = "/")
public class ShowAllDepartmentsAction implements Action {

    @Autowired
    private DepartmentService departmentService;    // = new DepartmentServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppException, ServletException, IOException {

        List<Department> allDepartments = departmentService.findAllEntities();

        request.setAttribute("allDepartments", allDepartments);

        request.getRequestDispatcher("/WEB-INF/" + "index" + ".jsp").forward(request,response);

    }
}
