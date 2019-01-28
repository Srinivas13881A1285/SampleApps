package com.ctc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ctc.command.EmployeeCommand;
import com.ctc.service.EmployeeInsertService;

public class EmployeeController extends MultiActionController{
	
	private EmployeeInsertService employeeService;
	
	public void setEmployeeService(EmployeeInsertService employeeService) {
		this.employeeService = employeeService;
	}

	public ModelAndView insert(HttpServletRequest request,HttpServletResponse response,EmployeeCommand cmd) {
		System.out.println(cmd.getId());
		return new ModelAndView("user","operation","Insert Operation");
	}
	
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response,EmployeeCommand cmd) {
		System.out.println(cmd.getId());
		return new ModelAndView("user","operation","Update Operation");
	}
	
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,EmployeeCommand cmd) {
		System.out.println(cmd.getId());
		return new ModelAndView("user","operation","Delete Operation");
	}
	
	public ModelAndView invalid(HttpServletRequest request,HttpServletResponse response,EmployeeCommand cmd) {
		System.out.println(cmd.getId());
		return new ModelAndView("user","operation","Invalid Operation");
	} 

}
