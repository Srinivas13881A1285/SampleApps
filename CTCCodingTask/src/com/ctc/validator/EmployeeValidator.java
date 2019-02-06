package com.ctc.validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ctc.command.Employee;
@Component
public class EmployeeValidator implements Validator {

	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Employee.class);
	}

	@Override
	public void validate(Object cmd, Errors errors) {
		Employee employeeCommand = (Employee)cmd;
		String employeeId = employeeCommand.getId();
		String firstName = employeeCommand.getFirstName();
		String lastName = employeeCommand.getLastName();
		String email = employeeCommand.getEmail();
		String contactNumber = employeeCommand.getContactNumber();
		Date dateOfJoining = employeeCommand.getDateOfJoining();
		String status = employeeCommand.getStatus();
		if(employeeId==null || employeeId.equals("") || employeeId.length()==0)
			errors.rejectValue("id", "id.required");
		else if(!Pattern.matches("^EPAM\\d+$", employeeId))
				errors.rejectValue("id","id.pattern");
		
		if(firstName == null || firstName.equals("") || firstName.length()==0)
			errors.rejectValue("firstName", "firstname.required");
		else if(!Pattern.matches("^[a-zA-Z]+$",firstName ))
			errors.rejectValue("firsName", "firstname.pattern");
		
		if(lastName == null || lastName.equals("") || lastName.length()==0)
			errors.rejectValue("lastName", "lastname.required");
		else if(!Pattern.matches("^[a-zA-Z]+$",lastName ))
			errors.rejectValue("lastName", "lastname.pattern");
		
		if(email == null || email.equals("") || email.length()==0)
			errors.rejectValue("email", "email.required");
		else if(!email.equalsIgnoreCase(firstName+"_"+lastName+"@epam.com"))
					errors.rejectValue("email", "email.pattern");
		
		if(contactNumber== null || contactNumber.equals("") || contactNumber.length()==0)
			errors.rejectValue("contactNumber", "contact.required");
		else if(!Pattern.matches("(0|91)?[7-9][0-9]{9}",contactNumber))
			errors.rejectValue("contactNumber", "contact.pattern");
		
		if(dateOfJoining == null)
			errors.rejectValue("dateOfJoining", "dob.required");
		else if(!Pattern.matches("^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$",new SimpleDateFormat("dd-MM-yyyy").format(dateOfJoining)))
			errors.rejectValue("dateOfJoining", "dob.pattern");
		
		if(!(status.equals("Active") || status.equals("InActive") || status.equals("New")))
			errors.rejectValue("status", "status.pattern");
		
	}

}
