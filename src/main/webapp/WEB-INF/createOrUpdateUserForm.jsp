<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create or Update</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>

<div class="main container">

    <div class="row">
        <h2>Create or update user form</h2>
    </div>

    <div class="row">
        <form method="post" action="<c:url value='/createOrUpdateUser' />">

            <input type="hidden"  name="userId" value="${user.id}"/>

            <input type="text" pattern="^[a-zA-Z0-9_]*$" placeholder="name" name="userName" value="${user.name}"/>
            <div class="text-danger">${mapErr['name']}</div>

            <input type="text" pattern="^[a-zA-Z0-9_]*$" placeholder="surname" name="userSurname" value="${user.surname}"/>
            <div class="text-danger">${mapErr['surname']}</div>

            <input type="text" placeholder="e-mail" name="userEmail" value="${user.email}"/>
            <div class="text-danger">${mapErr['email']}</div>

            <input required type="number"  placeholder="salary" name="userSalary" value="${user.salary}"/>
            <div class="text-danger">${mapErr['salary']}</div>

            <input required type="date"  placeholder="birthday" name="userBirthday" value="${user.birthday}"/>
            <div class="text-danger">${mapErr['birthday']}</div>


            <select name="departmentId">
                <c:forEach var="department" items="${allDepartments}">
                    <c:if test="${department.id==department_id}">
                        <option value="${department.id}" selected>${department.name}</option>
                    </c:if>
                    <c:if test="${department.id!=department_id}">
                        <option value="${department.id}">${department.name}</option>
                    </c:if>
                </c:forEach>
            </select>

            <input class="btn btn-success" type="submit" value="Save changes"/>
        </form>


    </div>
</div>
</body>
</html>
