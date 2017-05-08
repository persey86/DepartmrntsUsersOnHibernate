<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Department</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
    </div>
</nav>

<div class="main container">

    <div class="row">
        <h1>Departments </h1>
    </div>

    <div class="row">
        <table class="table table-bordered">
            <c:forEach var="department" items="${allDepartments}">
                <tr>

                    <td>${department.name}</td>
                    <td>${department.created}</td>

                    <%--counter of users for each department--%>
                    <td>${fn:length(department.users)}</td>

                    <td><a class="btn btn-primary" href="/createOrUpdateDepartment?departmentId=${department.id}">Edit</a></td>

                    <td>
                        <form method="post" action="<c:url value='/deleteDepartment' />">
                            <input type="hidden" name="departmentId" value="${department.id}">
                            <input class="btn btn-danger" type="submit" value="Delete department"/>
                        </form>
                    </td>

                  <td><a class="btn btn-success" href="/allUsers?id=${department.id}">Users</a></td>

                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="row">
        <td><a class="btn btn-success" href="/createOrUpdateDepartment">Create department</a></td>
    </div>


</div>
</body>
</html>