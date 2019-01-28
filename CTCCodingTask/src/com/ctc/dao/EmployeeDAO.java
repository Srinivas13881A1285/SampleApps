package com.ctc.dao;

import java.util.List;

import com.ctc.bo.EmployeeBO;

public interface EmployeeDAO {
	public int insert(EmployeeBO employeeBO);
	public List<EmployeeBO> getAllEmployees();
	public String deleteEmployees(List<EmployeeBO> listOfEmployeeBO);
	public String updateEmployees(List<EmployeeBO> listOfEmployeeBO);
}
