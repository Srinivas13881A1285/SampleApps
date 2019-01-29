package com.ctc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.ctc.bo.EmployeeBO;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static final String INSERT_QRY = "INSERT INTO EMP VALUES (?,?,?,?,?,?,?)";
	private static final String UPDATE_QRY = "UPDATE EMP SET FIRSTNAME=?,LASTNAME=?EMAIL=?,CONTACTNUMBER=?,DATE=?,STATUS=? WHERE EMPID=?";
	private static final String DELETE_QRY = "DELETE ";
	private static final String GET_ALL_EMPLOYEES_QRY = "SELECT * FROM EMP";
	
	private JdbcTemplate jt;
	
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	@Override
	public  int insert(EmployeeBO employeeBO) {
		int result = jt.update(INSERT_QRY,employeeBO.getId(),employeeBO.getFirstName(),employeeBO.getLastName(),employeeBO.getEmail(),employeeBO.getContactNumber(),employeeBO.getDateOfJoining(),employeeBO.getStatus());
		return result;
	}

	@Override
	public final List<EmployeeBO> getAllEmployees() {
		List<EmployeeBO> listOfAllEmployees = jt.query(GET_ALL_EMPLOYEES_QRY,new RowMapperResultSetExtractor<List<EmployeeBO>>null{
		
			
		}
	}	
	


	@Override
	public final String deleteEmployees(List<EmployeeBO> listOfEmployeeBO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final String updateEmployees(List<EmployeeBO> listOfEmployeeBO) {
		// TODO Auto-generated method stub
		return null;
	}

}
