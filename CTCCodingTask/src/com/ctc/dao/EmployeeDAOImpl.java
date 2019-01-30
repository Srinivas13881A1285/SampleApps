package com.ctc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ctc.bo.EmployeeBO;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private static final String INSERT_QRY = "INSERT INTO EMP(EMPID,FIRSTNAME,LASTNAME,EMAIL,CONTACTNUMBER,DOB,STATUS) VALUES(?,?,?,?,?,?,?)";
	private static final String UPDATE_QRY = "UPDATE EMP SET FIRSTNAME=?,LASTNAME=?EMAIL=?,CONTACTNUMBER=?,DATE=?,STATUS=? WHERE EMPID=?";
	private static final String DELETE_QRY = "DELETE ";
	private static final String GET_ALL_EMPLOYEES_QRY = "SELECT * FROM EMP";
	private static final String GET_EMP_BY_NO = "SELECT * FROM EMP WHERE EMPID=?";
	private static final String DELETE_EMP_BY_NO="DELETE FROM EMP WHERE EMPID=?"; 
	
	@Autowired
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

	public EmployeeBO getEmpByNo(int empNo) {
		EmployeeBO bo=null; 		  
		bo=jt.queryForObject(GET_EMP_BY_NO, new RowMapper<EmployeeBO>() {

			public EmployeeBO mapRow(ResultSet rs, int rowNum) throws SQLException {
				EmployeeBO bo=new EmployeeBO();
				bo.setId(rs.getString(1));
				bo.setFirstName(rs.getString(2));
				bo.setLastName(rs.getString(3));
				bo.setEmail(rs.getString(4));
				bo.setContactNumber(rs.getString(5));
				bo.setDateOfJoining(rs.getDate(6));
				bo.setStatus(rs.getString(7));
				return bo;
			}}, empNo);
		return bo;		
	}

	public int updateEmployee(EmployeeBO employeeBO) {
		int count=0;
		//use template 
		count = jt.update(UPDATE_QRY,employeeBO.getId(),employeeBO.getFirstName(),employeeBO.getLastName(),employeeBO.getEmail(),employeeBO.getContactNumber(),employeeBO.getDateOfJoining(),employeeBO.getStatus());	  
		return count;
	}//updateEmployee(-)
	
	public int deleteEmployee(int empNo) {
		int count=0;
		//USE template
		count=jt.update(DELETE_EMP_BY_NO, empNo);
		return count;
	}//deleteEmployee(-)

}
