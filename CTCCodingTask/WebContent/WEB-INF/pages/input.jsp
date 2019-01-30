<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib  uri="http://www.springframework.org/tags"  prefix="t"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css" class="err">
.err {
	font-family: calibri;
	font-size: 100%;
	color: red
}
</style>
<style type="text/css">
span {
	font-family: calibri;
	font-size: 100%;
	color: yellow
}
</style>
<script type="text/javascript">
	/*  var  nameValid=false;
	var  jobValid=false;
	var  salValid=false;
	 function validateName() {
	name =frm.name.value;
	nameLen=name.length;
	alert("name lengt is :"+nameLen)
	if(name==null || name.length==0 || name.equals("")){
		nameValid=false;
		alert("name lengt is(ifff) :"+nameLen)
		document.getElementById("spnName").innerHTML="Name should not be empty, please enter name";
	}
	else  if( nameLen!=0){
		alert("name lengt  is(else iff) :"+nameLen)
		nameValid=false;
		document.getElementById("spnName").innerHTML="Characters in name should be between 5 and 10";
	}
	else{
		alert("name lengt  is(else ) :"+nameLen)
	    nameValid=true;
		document.getElementById("spnName").innerHTML="";
	}    
	
	alert("name lengt is(last) :"+nameLen)
	
	   
	}//end validateName()

	function validateJob(){
	job =frm.job.value;
	if(job==null || job.length==0 || job.equals("")){
		jobValid=false;
		document.getElementById("spnJob").innerHTML="Job should not be empty, please enter job.";
	}
	 
	else{
	alert("job note::::: elseee "+job);
	    jobValid=true;
		document.getElementById("spnJob").innerHTML="";
	}    
	
	   
	}//end validateJob()


	function  validateSalary(){
	sal =frm.salary.value;
	salValue=Math.round(sal);
	if(sal==null || sal.length==0 || sal.equals("")){
		salValid=false;
		document.getElemntById("spnSalary").innerHTML="Salary should not be empty, please enter salary.";
	}

	else if( salValue<10000 || salValue>20000){
		salValid=false;
		document.getElementById("spnSalary").innerHTML="Salary should  be min 10000 and  max 20000";
	}
	
	else{
	    salValid=true;
		document.getElementById("spnSalary").innerHTML="";
	}    
	
	   
	}//end validateSalary()


	function  isValidated() {
	if(nameValid==true && jobValid==true && salValid==true)
	return true;
	else
		return false;
	}//isvValidated
	 */
	return true;
</script>

<meta charset="ISO-8859-1">
<title>input</title>
</head>
<body bgcolor="green">
	<fieldset>
		<f:form method="POST" name="frm" enctype="multipart/form-data"
			modelAttribute="employee" onsubmit="return  isValidated()">
			
             Id <f:input id="id" type="text" path="id"/>
			
			<br> 
             FirstName:<f:input id="firstName" type="text" path="firstName"/>
			
			<br>
           LastName:<f:input id="lastName" type="text" path="lastName"/>
			
			<br>
		
			Email:<f:input id="email" type="text" path="email"/>
			
			<br>
			
			ContactNumber:<f:input id="contactNumber" type="text" path="contactNumber"/>
			
			
			DateOfJoining:<f:input id="dateOfJoining" type="text" path="dateOfJoining"/>
			
			
			Status:<f:input id="status" type="text" path="status"/>	
			
			
			<input type="submit" value="register"/> &nbsp;&nbsp;&nbsp;&nbsp; 
            
			


		</f:form> 
	</fieldset>
	
	<a href="welcome.htm">Home</a>
	
	<br>
	 ${result}
	
	

</body>
</html>