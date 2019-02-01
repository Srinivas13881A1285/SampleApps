package com.ctc.service;

import java.util.List;

import com.ctc.bo.EmployeeBO;
import com.ctc.dto.EmployeeDTO;

public interface EmployeeInsertService {

	public String registerEmployee(EmployeeDTO employeeDTO);
	public List<EmployeeDTO> getAllEmployeesList();
	public String deleteEmployees(String[] Ids);
	public String updateEmployeeDetails(List<EmployeeDTO> listOfEmployeeDTO);
	public EmployeeDTO fetchEmployeeByNo(int empNo);
	public String modifyEmployee(EmployeeDTO dto);
	public String removeEmployee(int empNo); 

}
