package com.department.validation;


import com.department.exceptions.RepositoryException;
import com.department.models.Department;
import com.department.repository.DepartmentRepository;
import com.department.repository.implHibernate.DepartmentRepositoryImpl;
import net.sf.oval.constraint.CheckWithCheck;
import org.apache.log4j.Logger;


/**
 * Created on 20.04.17.
 */
public class UniqueDepartmentName implements CheckWithCheck.SimpleCheck {

    private DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    private static final Logger LOGGER = Logger.getLogger(UniqueDepartmentName.class);

    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
        try {
            Department validatedDepartment = (Department) validatedObject;
            Department departmentFromDataBase = departmentRepository.getByName(value.toString());
            String departmentName = departmentFromDataBase.getName();
            if (!value.equals(departmentName)) return true;
            else if (departmentFromDataBase.getId() == validatedDepartment.getId()) return true;

        } catch (RepositoryException e) {
            LOGGER.error("Error while getting validation for department", e);
        }
        return false;
    }
}