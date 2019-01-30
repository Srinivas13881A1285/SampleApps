package com.ctc.dao;

import java.util.List;

import com.ctc.bo.EmployeeBO;

public interface EmployeeDAO {
	public int insert(EmployeeBO employeeBO);
	public List<EmployeeBO> getAllEmployees();
	public int[] deleteEmployees(List<EmployeeBO> listOfEmployeeBO);
	public int[] updateEmployees(List<EmployeeBO> listOfEmployeeBO);
	public EmployeeBO getEmpByNo(int empNo);
	public int updateEmployee(EmployeeBO employeeBO);
	public int deleteEmployee(int empNo); 
}
