package com.ctc.dao;

import java.util.List;

import com.ctc.bo.EmployeeBO;

public interface EmployeeDAO {
	public int insertEmployee(EmployeeBO employeeBO);

	public List<EmployeeBO> fetchAllEmployees();

	public int deleteEmployees(String[] ids);

	public int updateEmployeesStatus(String[] ids, String status);
}
