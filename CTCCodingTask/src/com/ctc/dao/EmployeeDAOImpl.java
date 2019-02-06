package com.ctc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ctc.bo.EmployeeBO;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private static final String INSERT_QRY = "INSERT INTO EMP(EMPID,FIRSTNAME,LASTNAME,EMAIL,CONTACTNUMBER,DOB,STATUS) VALUES(?,?,?,?,?,?,?)";
	private static final String UPDATE_QRY = "UPDATE EMP SET STATUS=? WHERE EMPID IN ";
	private static final String GET_ALL_EMPLOYEES_QRY = "SELECT * FROM EMP";
	private static final String DELETE_EMP_BY_NO="DELETE FROM EMP WHERE EMPID IN "; 
	
	@Autowired
	private JdbcTemplate jt;
	

	@Override
	public  int insert(EmployeeBO employeeBO) {
		return jt.update(INSERT_QRY,employeeBO.getId(),employeeBO.getFirstName(),employeeBO.getLastName(),employeeBO.getEmail(),employeeBO.getContactNumber(),employeeBO.getDateOfJoining(),employeeBO.getStatus());
	}

	@Override
	public  List<EmployeeBO> getAllEmployees() {
		return jt.query(GET_ALL_EMPLOYEES_QRY, new EmployeeRowMapper());
	}
	
	private class EmployeeRowMapper implements RowMapper<EmployeeBO>{
		@Override
		public EmployeeBO mapRow(ResultSet rs, int pos) throws SQLException {
			return new EmployeeBO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getString(7));
		}
	}

	@Override
	public  int deleteEmployees(String[] ids) {
		String condition;
		StringBuilder sb = new StringBuilder("(");
		for(int i=0;i<ids.length;i++) {
			if(i==ids.length-1)
				sb.append("'"+ids[i]+"'");
			else
				sb.append("'"+ids[i]+"',");
		}
		sb.append(")");
		condition = sb.toString();
		return jt.update(DELETE_EMP_BY_NO.concat(condition));
	}

	@Override
	public  int updateEmployees(String[] ids,String status) {
		String condition;
		StringBuilder sb = new StringBuilder("(");
		for(int i=0;i<ids.length;i++) {
			if(i==ids.length-1)
				sb.append("'"+ids[i]+"'");
			else
				sb.append("'"+ids[i]+"',");
		}
		sb.append(")");
		condition = sb.toString();
		return jt.update(UPDATE_QRY.concat(condition),status);
		
		
		
		
		
	}
	

	

	



	
	

	
}
