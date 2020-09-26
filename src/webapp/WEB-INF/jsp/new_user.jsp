<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>New user</title>
</head>
<body>
<div align="center">
    <h2>New user</h2>
    <form:form action="save" method="post" modelAttribute="user">
        <table border="0" cellpadding="4">
            <%--<tr>--%>
                <%--<td>Id:</td>--%>
                <%--<td><form:input path="id"/></td>--%>
            <%--</tr>--%>
            <tr>
                <td>First name:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><form:input path="password"/></td>
            </tr>
            <%--<tr>--%>
                <%--<td>Age:</td>--%>
                <%--<td><form:input path="age"/></td>--%>
            <%--</tr>--%>
            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
<a href="#" th:href="@{/logout}">Log Out</a>
</body>
</html>