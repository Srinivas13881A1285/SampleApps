<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="pink" text="blue">
	<c:choose>
		<c:when test="${!empty dtoByNo}">
			<fieldset>
				<legend style="color: yellow;">Edit Employee Profile</legend>
				<table border="1">
					<tr>
						<td bgcolor="orange" height="40%" width="60%"><form:form
								action="saveEditedData.htm" method="POST"
								modelAttribute="employee">
            Employee ID:<input type="text" name="id"
									value="${dtoByNo.id}" disabled="true">
								<br>
								<input type="hidden" name="id" value="${dtoByNo.id}">
								<br>
            Employee FirstName :<input type="text" name="firstname"
									value="${dtoByNo.firstName}">
								<br>
			Employee FirstName :<input type="text" name="lastname"
									value="${dtoByNo.lastName}">
								<br>						 
            Email:<input type="text" name="email" value="${dtoByNo.email}">
								<br>
            Contact Number:<input type="text" name="contactnumber"
									value="${dtoByNo.contactNumber}">
			Date Of Joining:<input type="text" name="dob" value="${dtoByNo.dateOfJoining}">		
			
			Status:<input type="text" name="status" value="${dtoByNo.status}">				
									
								<br>
								<br>
								<input type="submit" value="Update">&nbsp;&nbsp;&nbsp;
             <input type="reset" value="Cancel">
								<br>
								<br>
								<a href="welcome.htm">HOME</a>

							</form:form>
						
					</tr>
				</table>
			</fieldset>
		</c:when>
		<c:when test="${!empty updateResult}">
          ${updateResult}<br>
			<br>
			<a href="welcome.htm">HOME</a>
			</td>

		</c:when>
		<c:otherwise>
			<br>
			<b>unable to fetch data to modify</b>
		</c:otherwise>

	</c:choose>

	<br>
	<br>

</body>
</html>