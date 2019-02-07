package com.ctc.service;

import java.util.List;

import com.ctc.dto.EmployeeDTO;

public interface EmployeeManagementService {

	public String registerEmployee(EmployeeDTO employeeDTO);

	public List<EmployeeDTO> getAllEmployeesList();

	public String deleteEmployees(String[] ids);

	public String updateEmployeesStatus(String[] ids, String status);

}