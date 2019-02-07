<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body bgcolor="yellow" text="blue">
	<f:form name="frm"  method="POST">
		<table border="2">
			<tr>
				<th>Id</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Email</th>
				<th>ContactNumber</th>
				<th>DateOfJoining</th>
				<th>Status</th>
			</tr>
			<c:forEach var="employee" items="${employeesList}">
				<tr>
					<td>${employee.id}</td>
					<td>${employee.firstName}</td>
					<td>${employee.lastName}</td>
					<td>${employee.email}</td>
					<td>${employee.contactNumber}</td>
					<td>${employee.dateOfJoining}</td>
					<td>${employee.status}</td>
					<td><input type="checkbox" name="checkBoxes" value="${employee.id}"></td>
				</tr>
			</c:forEach>
		</table>
		<br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       
    <input type="submit" value="Update Selected Employees Status to Active"
			formaction="updateStatusToActive.htm">&nbsp;
    <input type="submit" value="Update Selected Employees Status to InActive"
			formaction="updateStatusToInActive.htm">&nbsp;
    <input type="submit" value="Update Selected Employees Status to New"
			formaction="updateStatusToNew.htm">&nbsp;
    <input type="submit" value="Delete Selected Employeese" formaction="deleteEmployees.htm">&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </f:form>
	<br>
	<br>
	<a href="welcome.htm">HOME</a>
</body>
</html>