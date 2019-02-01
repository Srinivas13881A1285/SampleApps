package com.ctc.dao;

import java.util.List;

import com.ctc.bo.EmployeeBO;

public interface EmployeeDAO {
	public int insert(EmployeeBO employeeBO);
	public List<EmployeeBO> getAllEmployees();
	public int deleteEmployees(String[] Ids);
	public EmployeeBO getEmpByNo(int empNo);
	public int updateEmployee(EmployeeBO employeeBO);
	public int deleteEmployee(int empNo); 
}
