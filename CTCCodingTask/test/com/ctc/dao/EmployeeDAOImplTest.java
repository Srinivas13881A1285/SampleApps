package com.ctc.dao;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ctc.bo.EmployeeBO;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDAOImplTest {

	@Mock
	JdbcTemplate jt;

	@InjectMocks
	static EmployeeDAOImpl employeeDao;

	static EmployeeBO employeeBO;
	static Date dateOfJoining;

	private static final String INSERT_QRY = "INSERT INTO EMP(EMPID,FIRSTNAME,LASTNAME,EMAIL,CONTACTNUMBER,DOB,STATUS) VALUES(?,?,?,?,?,?,?)";

	@BeforeClass
	public static void setUp() throws ParseException {
		dateOfJoining = new SimpleDateFormat("dd-MM-yyyy").parse("08-01-2018");
	}

	@Test
	public void testEmployeeInsertion() throws ParseException {
		employeeBO = getEmployeeTestData();
		Mockito.when(jt.update(INSERT_QRY, employeeBO.getId(), employeeBO.getFirstName(), employeeBO.getLastName(),
				employeeBO.getEmail(), employeeBO.getContactNumber(), employeeBO.getDateOfJoining(),
				employeeBO.getStatus())).thenReturn(1);
		int actualResult = employeeDao.insertEmployee(employeeBO);
		assertEquals(1, actualResult);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testfetchAllEmployees() throws DataAccessException, ParseException {
		Mockito.when(jt.query(Mockito.anyString(), Mockito.any(RowMapper.class)))
				.thenReturn(getListOfEmployeesTestData());
		List<EmployeeBO> actualData = employeeDao.fetchAllEmployees();
		List<EmployeeBO> expectedData = getListOfEmployeesTestData();
		assertEquals(expectedData, actualData);
	}

	@Test
	public void testUpateEmployeesStatusInActive() {
		Mockito.when(jt.update("UPDATE EMP SET STATUS=? WHERE EMPID IN ('EPAM001','EPAM002')", "InActive"))
				.thenReturn(2);
		int actualResult = employeeDao.updateEmployeesStatus(new String[] { "EPAM001", "EPAM002" }, "InActive");
		assertEquals(2, actualResult);
	}

	@Test
	public void testEmployeesDelete() {
		Mockito.when(jt.update("DELETE FROM EMP WHERE EMPID IN ('EPAM001','EPAM002')")).thenReturn(2);
		int actualResult = employeeDao.deleteEmployees(new String[] { "EPAM001", "EPAM002" });
		assertEquals(2, actualResult);
	}

	@AfterClass
	public static void tearDown() {
		employeeBO = null;
		employeeDao = null;
		dateOfJoining = null;
	}

	public EmployeeBO getEmployeeTestData() throws ParseException {
		EmployeeBO employeeBO = new EmployeeBO();
		employeeBO.setId("EPAM100");
		employeeBO.setFirstName("Akhter");
		employeeBO.setLastName("Rasool");
		employeeBO.setEmail("akhter_rasool@epam.com");
		employeeBO.setContactNumber("9866436639");
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse("08-01-2018");
		employeeBO.setDateOfJoining(date1);
		employeeBO.setStatus("New");
		return employeeBO;
	}

	public List<EmployeeBO> getListOfEmployeesTestData() throws ParseException {
		List<EmployeeBO> listOfEmployeeBO = new ArrayList<>();
		EmployeeBO employeeBOOne = new EmployeeBO("EPAM001", "Srinivas", "Chintakindhi", "srinivas_chintakindhi",
				"9866436639", dateOfJoining, "Active");
		EmployeeBO employeeBOTwo = new EmployeeBO("EPAM002", "Akhter", "Rasool", "akhter_rasool", "9866436639",
				dateOfJoining, "Active");
		listOfEmployeeBO.add(employeeBOTwo);
		listOfEmployeeBO.add(employeeBOOne);
		return listOfEmployeeBO;
	}

}
