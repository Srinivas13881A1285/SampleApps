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
			<c:forEach var="dto" items="${listDTO}">
				<tr>
					<td>${dto.id}</td>
					<td>${dto.firstName}</td>
					<td>${dto.lastName}</td>
					<td>${dto.email}</td>
					<td>${dto.contactNumber}</td>
					<td>${dto.dateOfJoining}</td>
					<td>${dto.status}</td>
					<td><input type="checkbox" name="checkBoxes" value="${dto.id}"></td>
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