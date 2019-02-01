<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>
	<center>Login Page</center>
</h1>



<form:form method="post" commandName="employee">

	<span style='color: red'><form:errors path="*" /></span><br>
	<br>
	
	Id:<form:input path="id" />
	<br>
	FirstName:<form:input path="firstName" />
	<br>
	LastName:<form:input path="lastName" /><br>
	
	Email:<form:input path="email"/><br>
	
	ContactNumber:<form:input path="contactNumber"/><br>
	
	Date Of Joining:<form:input path="dateOfJoining"/>(dd-MM-yyyy)<br>
	
	Status:<form:input path="status"/><br>
	
	<input type="submit" value="Login" />
	<br>
</form:form>

<br>


 