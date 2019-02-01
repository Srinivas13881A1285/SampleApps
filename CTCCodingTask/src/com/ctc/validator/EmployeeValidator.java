package com.ctc.validator;

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
		if(employeeCommand.getId()==null || employeeCommand.getId().equals("") || employeeCommand.getId().length()==0)
			errors.rejectValue("id", "id.required");
	}

}
