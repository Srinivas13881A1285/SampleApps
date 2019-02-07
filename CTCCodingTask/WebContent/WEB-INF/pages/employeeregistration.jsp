<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>
	<center>Registration Page</center>
</h1>



<form:form method="post" action = "registerEmployee.htm" commandName="employee">

	<span style='color: red'><form:errors path="*" /></span><br>
	<br>
	
	Id:<form:input path="id" />  EPAM followed by number(example EPAM0001)
	<br>
	<br>
	FirstName:<form:input path="firstName" />
	<br>
	<br>
	LastName:<form:input path="lastName" /><br>
	<br>
	Email:<form:input path="email"/>(firstname_lastname@epam.com)<br><br>
	
	ContactNumber:<form:input path="contactNumber"/><br><br>
	
	Date Of Joining:<form:input path="dateOfJoining"/>(dd-MM-yyyy)<br><br>
	
	Status:<form:input path="status"/>(New/Active/InActive)<br><br>
	
	<input type="submit" value="Add" />
	<br>
</form:form>
  <a href="welcome.htm">HOME</a>
<br>


 