package com.ctc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctc.command.Employee;
import com.ctc.dto.EmployeeDTO;
import com.ctc.service.EmployeeManagementService;
import com.ctc.validator.EmployeeValidator;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeManagementService employeeService;

	@Autowired
	private EmployeeValidator employeeValidator;

	private static final String RESULT_STATUS_VIEW = "resultstatus";
	private static final String RESULT_ATTRIBUTE = "statusMessage";

	@RequestMapping("/welcome.htm")
	public String showHome() {
		return "welcome";
	}

	@RequestMapping(value = "/getRegistrationForm.htm", method = RequestMethod.GET)
	public String displayForm(Model model) {
		Employee employeeCommand = new Employee();
		model.addAttribute("employee", employeeCommand);
		return "employeeregistration";
	}

	@RequestMapping(value = "/registerEmployee.htm", method = RequestMethod.POST)
	public String registerEmployee(Model model, @ModelAttribute("employee") Employee cmd, BindingResult errors) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(cmd.getId());
		employeeDTO.setFirstName(cmd.getFirstName());
		employeeDTO.setLastName(cmd.getLastName());
		employeeDTO.setEmail(cmd.getEmail());
		employeeDTO.setContactNumber(cmd.getContactNumber());
		employeeDTO.setDateOfJoining(cmd.getDateOfJoining());
		employeeDTO.setStatus(cmd.getStatus());
		if (employeeValidator.supports(Employee.class)) {
			employeeValidator.validate(cmd, errors);
			if (errors.hasErrors()) {
				return "employeeregistration";
			}
		}
		String registrationStatusMsg = employeeService.registerEmployee(employeeDTO);
		model.addAttribute(RESULT_ATTRIBUTE, registrationStatusMsg);
		return RESULT_STATUS_VIEW;
	}

	@RequestMapping(value = "/getAllEmployees.htm")
	public String retriveAllEmployees(Map<String, Object> map) {
		List<EmployeeDTO> employeelistDTO = employeeService.getAllEmployeesList();
		map.put("employeesList", employeelistDTO);
		return "allemployeedetails";
	}

	@RequestMapping(value = "/updateStatusToActive.htm", method = RequestMethod.POST)
	public String updateEmployeeStatusToActive(@RequestParam("checkBoxes") String[] checkboxValues,
			Map<String, Object> map) {
		String updateEmployeeStatusMsg = employeeService.updateEmployeesStatus(checkboxValues, "Active");
		map.put(RESULT_ATTRIBUTE, updateEmployeeStatusMsg);
		return RESULT_STATUS_VIEW;

	}

	@RequestMapping(value = "/updateStatusToInActive.htm", method = RequestMethod.POST)
	public String updateEmployeeStatusToInActive(@RequestParam("checkBoxes") String[] checkboxValues,
			Map<String, Object> map) {
		String updateEmployeeStatusMsg = employeeService.updateEmployeesStatus(checkboxValues, "InActive");
		map.put(RESULT_ATTRIBUTE, updateEmployeeStatusMsg);
		return RESULT_STATUS_VIEW;
	}

	@RequestMapping(value = "/updateStatusToNew.htm", method = RequestMethod.POST)
	public String updateEmployeeStatusToNew(@RequestParam("checkBoxes") String[] checkboxValues,
			Map<String, Object> map) {
		String updateEmployeeStatusMsg = employeeService.updateEmployeesStatus(checkboxValues, "New");
		map.put(RESULT_ATTRIBUTE, updateEmployeeStatusMsg);
		return RESULT_STATUS_VIEW;
	}

	@RequestMapping(value = "/deleteEmployees.htm", method = RequestMethod.POST)
	public String deleteEmployeeDetails(@RequestParam("checkBoxes") String[] chboxvals, Map<String, Object> map) {
		String deleteEmployeesStatusMsg = employeeService.deleteEmployees(chboxvals);
		map.put(RESULT_ATTRIBUTE, deleteEmployeesStatusMsg);
		return RESULT_STATUS_VIEW;
	}

	@InitBinder
	public void myInitBinder(WebDataBinder binder) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
	}
}
