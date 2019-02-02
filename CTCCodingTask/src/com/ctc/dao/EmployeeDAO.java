package com.ctc.dao;

import java.util.List;

import com.ctc.bo.EmployeeBO;

public interface EmployeeDAO {
	public int insert(EmployeeBO employeeBO);
	public List<EmployeeBO> getAllEmployees();
	public int deleteEmployees(String[] ids);
	public int updateEmployees(String[] ids,String status);
}
