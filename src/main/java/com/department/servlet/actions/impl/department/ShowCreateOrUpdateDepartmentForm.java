package com.department.servlet.actions.impl.department;

import com.department.services.DepartmentService;
import com.department.servlet.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 19.04.2017.
 */
@Component(value = "/createOrUpdateDepartment")
public class ShowCreateOrUpdateDepartmentForm implements Action {

    @Autowired
    private DepartmentService departmentService; //= new DepartmentServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String departmentIdString = request.getParameter("departmentId");

        if (departmentIdString != null) {
            Integer departmentId = Integer.parseInt(departmentIdString);
            request.setAttribute("department",  this.departmentService.findOneEntity(departmentId));
        }

        request.getRequestDispatcher("/WEB-INF/" + "createOrUpdateDepartmentForm" + ".jsp").forward(request,response);
    }
}
