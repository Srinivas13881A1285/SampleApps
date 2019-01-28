package com.ctc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
		List<EmployeeBO> listBO = (List<EmployeeBO>)jt.query(GET_ALL_EMPLOYEES_QRY, new EmployeeRowMapper());
		return listBO;
	}
	
	private class EmployeeRowMapper implements RowMapper<EmployeeBO>{
		@Override
		public EmployeeBO mapRow(ResultSet rs, int pos) throws SQLException {
			EmployeeBO  empbo = new EmployeeBO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getString(7));
			return empbo;
		}
	}

	@Override
	public final int[] deleteEmployees(List<EmployeeBO> listOfEmployeeBO) {
		int[] result = jt.batchUpdate(DELETE_QRY,new BatchUpdate(listOfEmployeeBO));
		return result;
	}
	
	
	@Override
	public final int[] updateEmployees(List<EmployeeBO> listOfEmployeeBO) {
		int[] result = jt.batchUpdate(UPDATE_QRY,new BatchUpdate(listOfEmployeeBO));
		return result;
	}

	private static final class BatchUpdate implements BatchPreparedStatementSetter{
		private List<EmployeeBO> listOfEmployeeBO;
		public BatchUpdate(List<EmployeeBO> listOfEmployeeBO) {
			this.listOfEmployeeBO = listOfEmployeeBO;
		}
		@Override
		public int getBatchSize() {
			return listOfEmployeeBO.size();
		}

		@Override
		public void setValues(PreparedStatement ps, int index) throws SQLException {
			ps.setString(1, listOfEmployeeBO.get(index).getId());
			ps.setString(2, listOfEmployeeBO.get(index).getFirstName());
			ps.setString(3, listOfEmployeeBO.get(index).getLastName());
			ps.setString(4, listOfEmployeeBO.get(index).getEmail());
			ps.setString(5, listOfEmployeeBO.get(index).getContactNumber());
			ps.setDate(6, (Date) listOfEmployeeBO.get(index).getDateOfJoining());
			ps.setString(7, listOfEmployeeBO.get(index).getStatus());
		}
	}
	
}
