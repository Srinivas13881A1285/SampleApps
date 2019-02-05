package test;

import static org.junit.Assert.assertEquals;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import com.ctc.dao.EmployeeDAOImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDAOTest {

	@Mock
	JdbcTemplate jt;
	
	@InjectMocks
	static
	EmployeeDAOImpl employeeDao ;
	
	static EmployeeBO employeeBO;
	static Date date1;
	
	private static final String INSERT_QRY = "INSERT INTO EMP(EMPID,FIRSTNAME,LASTNAME,EMAIL,CONTACTNUMBER,DOB,STATUS) VALUES(?,?,?,?,?,?,?)";
	private static final String GET_ALL_EMPLOYEES_QRY = "SELECT * FROM EMP";
	
	
	@BeforeClass
	public static void setUp() throws ParseException {
		 date1=new SimpleDateFormat("dd-MM-yyyy").parse("08-01-2018");  
	}
	
	@Test
	public void EmployeeInsertionTest() throws ParseException {
		employeeBO = getEmployeeTestData();
		Mockito.when(jt.update(INSERT_QRY,employeeBO.getId(),employeeBO.getFirstName(),employeeBO.getLastName(),employeeBO.getEmail(),employeeBO.getContactNumber(),employeeBO.getDateOfJoining(),employeeBO.getStatus())).thenReturn(1);
		int actualResult = employeeDao.insert(employeeBO);
		assertEquals(actualResult,1);
	}

	
	@Test
	public void getAllEmployeeTest() throws DataAccessException, ParseException {
		Mockito.when(jt.query(GET_ALL_EMPLOYEES_QRY, new EmployeeRowMapper())).thenReturn(getListOfEmployeesTestData());
		List<EmployeeBO> actualData = employeeDao.getAllEmployees();
		List<EmployeeBO> expectedData = getListOfEmployeesTestData();
		System.out.println(actualData);
		System.out.println(expectedData);
//		EmployeeBO[] actualArrayData = new EmployeeBO[actualData.size()];
//		actualArrayData = actualData.toArray(actualArrayData);
//		EmployeeBO[] expectedArrayData = new EmployeeBO[expectedData.size()];
//		expectedArrayData = expectedData.toArray(expectedArrayData);
//		assertArrayEquals(actualArrayData,expectedArrayData);
//		assertNotNull(actualData);
	}
	
	@Test
	public void employeeUpdateTest() {
		Mockito.when(jt.update("UPDATE EMP SET STATUS=? WHERE EMPID IN ('1','2')","InActive")).thenReturn(1);
		int actualResult = employeeDao.updateEmployees(new String[] {"1","2"},"InActive");
		assertEquals(actualResult,1);
	}
	
	@Test
	public void employeeDeleteTest() {
		Mockito.when(jt.update("DELETE FROM EMP WHERE EMPID IN ('1','2')")).thenReturn(1);
		int actualResult = employeeDao.deleteEmployees(new String[] {"1","2"});
		assertEquals(actualResult,1);
	}
	
	public EmployeeBO getEmployeeTestData() throws ParseException {
		EmployeeBO employeeBO = new EmployeeBO();
		employeeBO.setId("EPAM100");
		employeeBO.setFirstName("Akhter");
		employeeBO.setLastName("Rasool");
		employeeBO.setEmail("akhter_rasool@epam.com");
		employeeBO.setContactNumber("9866436639");
		Date date1=new SimpleDateFormat("dd-MM-yyyy").parse("08-01-2018");  
		employeeBO.setDateOfJoining(date1);
		employeeBO.setStatus("New");
		return employeeBO;
	}
	
	public List<EmployeeBO> getListOfEmployeesTestData() throws ParseException{
		EmployeeBO employeeBOOne = new EmployeeBO("EPAM001","Srinivas","Chintakindhi","srinivas_chintakindhi","9866436639",date1,"Active");
		EmployeeBO employeeBOTwo = new EmployeeBO("EPAM002","Akhter","Rasool","akhter_rasool","9866436639",date1,"Active");
		return Arrays.asList(employeeBOOne,employeeBOTwo);
	}
	
	public class EmployeeRowMapper implements RowMapper<EmployeeBO>{
		@Override
		public EmployeeBO mapRow(ResultSet rs, int pos) throws SQLException {
			EmployeeBO  empbo = new EmployeeBO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getString(7));
			return empbo;
		}
	}
	
	@AfterClass
	public static void tearDown() {
		employeeBO = null;
		employeeDao = null;
		
	}
	
}
