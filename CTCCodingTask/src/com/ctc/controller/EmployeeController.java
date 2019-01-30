package com.ctc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ctc.command.EmployeeCommand;
import com.ctc.dto.EmployeeDTO;
import com.ctc.service.EmployeeInsertService;

@Controller
public class EmployeeController{
	
	@Autowired
	private EmployeeInsertService employeeService;
	
	public void setEmployeeService(EmployeeInsertService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping("/welcome.htm")
	public String showHome() {
		return "welcome";		
	}

	@RequestMapping(value="/insert.htm", method=RequestMethod.GET)
	public String displayForm(@ModelAttribute("employee") EmployeeCommand cmd, HttpSession ses ) {
		int serverToken=0;
		serverToken=new Random().nextInt(1000);
		ses.setAttribute("serverToken",serverToken);
		cmd.setClientToken(serverToken);		 
		return "input";		
	}
	
	@RequestMapping(value="/insert.htm", method=RequestMethod.POST)
	public ModelAndView  register(@Validated @ModelAttribute("employee") EmployeeCommand cmd ,BindingResult br, HttpSession ses) {
		System.out.println(cmd);
		int serverToken=0;  
		int clientToken=0;
		serverToken=(Integer)ses.getAttribute("serverToken");  	 
		clientToken=cmd.getClientToken();
		System.out.println("data");
		System.out.println(cmd);
		System.out.println("CLEINT TKN ::"+clientToken);
		System.out.println("SER TKN ::"+serverToken);
		if(true)                      
		{	
			ses.setAttribute("serverToken",new Random().nextInt(1000));
			if(br.hasErrors()) {
				return new ModelAndView("input");
			}
			else {
				EmployeeDTO dto=null;
				String result=null;
				ModelAndView mav=null;
				dto=new EmployeeDTO();
				//convert command obj to DTO
				dto.setId(cmd.getId());
				dto.setFirstName(cmd.getFirstName());
				dto.setLastName(cmd.getLastName());
				dto.setEmail(cmd.getEmail());
				dto.setContactNumber(cmd.getContactNumber());
				dto.setDateOfJoining(cmd.getDateOfJoining());
				dto.setStatus(cmd.getStatus());
				
                 try {
				//use service
                	 	result=employeeService.registerEmployee(dto);
                 }
                 catch (Exception e) {
					e.printStackTrace();
				}
				mav=new ModelAndView();
				mav.addObject("result", result);
				mav.setViewName("result");
				return mav;
			}
		}//if
		else 
		{
			return new ModelAndView("double_post");
		}
	}//register(-,-)
	
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
	public String updateProfile(@ModelAttribute("employee") EmployeeCommand cmd, Map<String,Object> map ) {
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
		//use service
		 
			
		deleteResult=employeeService.removeEmployee(Integer.parseInt(chboxvals[0]));
		map.put("result", deleteResult);
		return "result";
		 
	}//deleteEmployeeDetails
}
