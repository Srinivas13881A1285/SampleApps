package com.ctc.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import com.ctc.service.EmployeeInsertService;
import com.ctc.validator.EmployeeValidator;

@Controller
public class EmployeeController{
	
	@Autowired
	private EmployeeInsertService employeeService;
	
	@Autowired
	private EmployeeValidator employeeValidator;
	
	@RequestMapping("/welcome.htm")
	public String showHome() {
		return "welcome";		
	}
	

	@RequestMapping(value="/insert.htm", method=RequestMethod.GET)
	public String displayForm(Model model) {
		Employee employeeCommand = new  Employee();
		model.addAttribute("employee", employeeCommand);
		return "input";		
	}
	
	@RequestMapping(value="/insert.htm", method=RequestMethod.POST)
	public String register(Model model,@ModelAttribute("employee") Employee cmd ,BindingResult errors) {
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(cmd.getId());
		employeeDTO.setFirstName(cmd.getFirstName());
		employeeDTO.setLastName(cmd.getLastName());
		employeeDTO.setEmail(cmd.getEmail());
		employeeDTO.setContactNumber(cmd.getContactNumber());
		employeeDTO.setDateOfJoining(cmd.getDateOfJoining());
		employeeDTO.setStatus(cmd.getStatus());
		
		
		if(employeeValidator.supports(Employee.class)) {
			employeeValidator.validate(cmd, errors);
			if(errors.hasErrors()) {
				return "input";
			}
		}
		
		String result = employeeService.registerEmployee(employeeDTO);
		model.addAttribute("statusMessage",result);
		return "result";
	}
	
	
	@RequestMapping(value="/selectAll.htm")
	public  String retriveAllEmployees(Map<String,Object> map){
		List<EmployeeDTO> listDTO=null;
		//use service
		listDTO=employeeService.getAllEmployeesList();
		map.put("listDTO",listDTO);
		return   "show_all";

	}
	
	@RequestMapping(value="/editEmp.htm" , method=RequestMethod.POST)
	public  String retriveEmployeeByEmpNo(@RequestParam("editCheckBoxes")String[] checkboxValues, Map<String, Object> map){
		EmployeeDTO  dtoByNo=null;
		System.out.println("editttttttttttttttttttchecBB   ::"+Arrays.toString(checkboxValues));
		//use service
		dtoByNo=employeeService.fetchEmployeeByNo(Integer.parseInt(checkboxValues[0]));
		map.put("dtoByNo", dtoByNo);
		return "edit_employee";    	 
	}//retriveEmployeeByEmpNo(-,-)

	@RequestMapping(value="/saveEditedData.htm", method=RequestMethod.POST)
	public String updateProfile(@ModelAttribute("employee") Employee cmd, Map<String,Object> map ) {
		EmployeeDTO dto=null;
		String result=null;
		//convert cmd to dto
		dto=new EmployeeDTO(); 
		dto.setId(cmd.getId());//use servcie
		dto.setFirstName(cmd.getFirstName());
		dto.setLastName(cmd.getLastName());
		dto.setEmail(cmd.getEmail());
		dto.setContactNumber(cmd.getContactNumber());
		dto.setDateOfJoining(cmd.getDateOfJoining());
		dto.setStatus(cmd.getStatus());
		result= employeeService.modifyEmployee(dto);
		map.put("updateResult",result);
		return "edit_employee";

	}//updateProfile(-)

	@RequestMapping(value="/deleteEmp.htm", method=RequestMethod.POST)
	public String deleteEmployeeDetails(@RequestParam("deleteCheckBoxes")String[] chboxvals,  Map<String,Object> map) {
		String deleteResult=null;
		System.out.println("deleteeeeeeeeeeEMP :::"+Arrays.toString(chboxvals));
		deleteResult=employeeService.deleteEmployees(chboxvals);
		map.put("result", deleteResult);
		return "result";
		 
	}//deleteEmployeeDetails
	

	@InitBinder
	public void myInitBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf,true));
	}
}
