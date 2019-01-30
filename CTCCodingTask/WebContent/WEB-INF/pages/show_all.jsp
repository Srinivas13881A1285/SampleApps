<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="t" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
 

</script> 
 
</head>

<body bgcolor="yellow" text="blue">
     <f:form name="frm" action="editEmp.htm" method="POST">    
    <table border="2" >
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
<%--                <td> <a href="editEmp.htm?empNo=${dto.empNo}"><t:message code="table.column.name.edit"/></a> 
 --%>      
                         
           <td><input type="checkbox" name="editCheckBoxes" value="${dto.id}"></td>
               
<%--                <td><a style="color:green" href="deleteEmp.htm?empNo=${dto.empNo}"><t:message code="table.column.name.delete"/></a> </td>                      
 --%>
                 <td>  <input type="checkbox" name="deleteCheckBoxes" value="${dto.id}"></td>
              </tr>          
         </c:forEach> 
    </table>
  <br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       
    <input type="submit"  value="update" formaction="editEmp.htm">&nbsp;
    <input type="submit"  value="delete" formaction="deleteEmp.htm">&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   
    </f:form>
    
    <br><br>
    <a href="welcome.htm"><t:message code="link.text.home" text="bk"></t:message></a>
    
	&nbsp;&nbsp;<a href="selectAll.htm?lang=hi"><%-- <t:message code="link.text.hindi"/> --%>
</a>&nbsp;&nbsp;&nbsp;
     
<a href="selectAll.htm?lang=en">English</a>&nbsp;&nbsp;<a href="selectAll.htm?lang=de">German</a>
</body>
</html>