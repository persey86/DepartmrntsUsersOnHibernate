//package com.department.repository.impl;
//
//import com.department.exceptions.RepositoryException;
//import com.department.models.Department;
//import com.department.repository.DepartmentRepository;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static com.department.utils.SqlUtils.getConnection;
//
///**
// * Created on 02.04.2017.
// */
//public class DepartmentRepositoryImpl implements DepartmentRepository{
//
//    private static final String SELECT_FROM_DEPARTMENTS = "SELECT * from departments d ";
//    private static final String INSERT_INTO_DEPARTMENTS = "INSERT INTO departments (name, created) VALUES (?, ?)";
//    private static final String UPDATE_DEPARTMENT = "UPDATE departments SET name = ?, created = ? WHERE id = ?";
//    private static final String DELETE_DEPARTMENTS = "DELETE FROM departments WHERE id = ?";
//    private static final String GET_DEPARTMENT_WITH_SELECTED_id = "SELECT * from departments where departments.id = ?";
//    private static final String GET_DEPARTMENT_WITH_SELECTED_name = "SELECT * from departments where departments.name = ?";
//
//
//    @Override
//    public Department save(Department entity) throws RepositoryException {
//
//        //we should change variable "entity" to "department" inside the method only to avoid bad code in signature
//        Department department = entity;
//        Integer id = department.getId();
//
//        if (id == null) {
//
//            //Do save if entity doesn't have ID
//            department = createDepartment(department.getName(), department.getCreated());
//        }else {
//            updateDepartment(department.getId(),department.getName(),department.getCreated());
//        }
//
//        //if create or update not done this method will throw Exception
//        return department;
//    }
//
//
//    @Override
//    public List<Department> findAll() throws RepositoryException {
//
//        List<Department> departments = new ArrayList<>();
//
//        try (Connection connection = getConnection()) {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(SELECT_FROM_DEPARTMENTS);
//
//            while (resultSet.next()) {
//                Department user = new Department();
//                user.setId(resultSet.getInt("id"));
//                user.setName(resultSet.getString("name"));
//                user.setCreated(resultSet.getDate("created"));
//                departments.add(user);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RepositoryException("Error while getting departments",e);
//        }
//        return departments;
//    }
//
//    private Department createDepartment(String name, Date created) throws RepositoryException {
//        try (Connection connection = getConnection()) {
//            Department department = null;
//
//            PreparedStatement pStm = connection.prepareStatement(INSERT_INTO_DEPARTMENTS);
//            pStm.setString(1, name);
//            pStm.setDate(2, new java.sql.Date(created.getTime()));
//            Integer isUpdated = pStm.executeUpdate();
//
//
//            if (isUpdated > 0) {
//                department = new Department();
//                department.setName(name);
//                department.setCreated(created);
//            }
//
//            return department;
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RepositoryException("Error while adding new department",e);
//        }
//    }
//
//    @Override
//    public void delete(Integer id) throws RepositoryException {
//        try (Connection connection = getConnection()) {
//            PreparedStatement pStm = connection.prepareStatement(DELETE_DEPARTMENTS);
//            pStm.setInt(1, id);
//            pStm.executeUpdate();
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RepositoryException("Error while deleted models form SQL ",e);
//        }
//    }
//
//    @Override
//    public Department findOne(Integer id) throws RepositoryException {
//
//        try (Connection connection = getConnection()) {
//            Department department = null;
//
//            PreparedStatement pStm = connection.prepareStatement(GET_DEPARTMENT_WITH_SELECTED_id);
//            pStm.setInt(1, id);
//            ResultSet resultSet = pStm.executeQuery();
//            while (resultSet.next()) {
//                department = new Department();
//                department.setId(resultSet.getInt("id"));
//                department.setName(resultSet.getString("name"));
//                department.setCreated(resultSet.getDate("created"));
//            }
//            return department;
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RepositoryException("Error while getting departments by ID",e);
//        }
//    }
//
//    private void updateDepartment(Integer id, String name, Date created) throws RepositoryException {
//        try (Connection connection = getConnection()) {
//            PreparedStatement pStm = connection.prepareStatement(UPDATE_DEPARTMENT);
//
//            pStm.setString(1, name);
//            pStm.setDate(2, new java.sql.Date(created.getTime()));
//            pStm.setInt(3, id);
//
//           pStm.executeUpdate();
//
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RepositoryException("Error while updating departments by ID",e);
//        }
//    }
//
//
//    @Override
//    public Department getByName(String name) throws RepositoryException {
//
//        try (Connection connection = getConnection()) {
//            Department department = null;
//
//            PreparedStatement pStm = connection.prepareStatement(GET_DEPARTMENT_WITH_SELECTED_name);
//            pStm.setString(1, name);
//            ResultSet resultSet = pStm.executeQuery();
//            while (resultSet.next()) {
//                department = new Department();
//                department.setId(resultSet.getInt("id"));
//                department.setName(resultSet.getString("name"));
//            }
//            return department;
//        }catch (SQLException | ClassNotFoundException e){
//            throw new RepositoryException("Error while getting name of department",e);
//        }
//    }
//
//}