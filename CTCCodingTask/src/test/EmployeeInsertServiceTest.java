package test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ctc.bo.EmployeeBO;
import com.ctc.dao.EmployeeDAOImpl;
import com.ctc.dto.EmployeeDTO;
import com.ctc.service.EmployeeInsertServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeInsertServiceTest {

	@Mock
	EmployeeDAOImpl employeeDao;
	
	@InjectMocks
	EmployeeInsertServiceImpl employeeService;
	
	EmployeeDTO employeeDTO;
	
	static Date date1;
	public static void setUp() throws ParseException {
		date1=new SimpleDateFormat("dd-MM-yyyy").parse("08-01-2018");
	}
	
	@Test
	public void registerEmployeeTest() throws ParseException {
		employeeDTO = getEmployeeTestData();
		EmployeeBO employeeBO = new EmployeeBO(employeeDTO.getId(),employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getEmail(),employeeDTO.getContactNumber(),employeeDTO.getDateOfJoining(),employeeDTO.getStatus());
		Mockito.when(employeeDao.insert(employeeBO)).thenReturn(1);
		String result = employeeService.registerEmployee(employeeDTO);
		System.out.println(result);
	}
	
	@Test
	public void updateEmployeesTest() {
		Mockito.when(employeeDao.updateEmployees(new String[] {"1","2"},"InActive")).thenReturn(1);
		String actualResult = employeeService.updateEmployees(new String[] {"1", "2"},"InActive");
		assertEquals(actualResult,"Update Sucess");
	}
	
	@Test
	public void employeeDeleteTest() {
		Mockito.when(employeeDao.deleteEmployees(new String[] {"1","2"})).thenReturn(1);
		String actualResult = employeeService.deleteEmployees(new String[] {"1","2"});
		assertEquals(actualResult,"Delete Successful");
	}
	
	@Test
	public void getAllEmployeesListTest() throws ParseException {
		Mockito.when(employeeDao.getAllEmployees()).thenReturn(getListOfEmployeesBOTestData());
		List<EmployeeDTO> actualResult = employeeService.getAllEmployeesList();
		List<EmployeeDTO> expectedResult = getListOfEmployeesTestData();
		assertEquals(actualResult,expectedResult);
		assertEquals(actualResult.get(0).getFirstName(),expectedResult.get(0).getFirstName());
	}
	
	@AfterClass
	public static void tearDown() {
		
	}
	
	public EmployeeDTO getEmployeeTestData() throws ParseException {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId("EPAM100");
		employeeDTO.setFirstName("Akhter");
		employeeDTO.setLastName("Rasool");
		employeeDTO.setEmail("akhter_rasool@epam.com");
		employeeDTO.setContactNumber("9866436639");
		Date date1=new SimpleDateFormat("dd-MM-yyyy").parse("08-01-2018");  
		employeeDTO.setDateOfJoining(date1);
		employeeDTO.setStatus("New");
		return employeeDTO;
	}

public List<EmployeeDTO> getListOfEmployeesTestData() throws ParseException{
		EmployeeDTO employeeDTOOne = new EmployeeDTO("EPAM001","Srinivas","Chintakindhi","srinivas_chintakindhi","9866436639",date1,"Active");
		EmployeeDTO employeeDTOTwo = new EmployeeDTO("EPAM002","Akhter","Rasool","akhter_rasool","9866436639",date1,"Active");
		List<EmployeeDTO> listOfEmployeeDTO = new ArrayList<>();
		listOfEmployeeDTO.add(employeeDTOOne);
		listOfEmployeeDTO.add(employeeDTOTwo);
		return listOfEmployeeDTO;
	}

public List<EmployeeBO> getListOfEmployeesBOTestData() throws ParseException{
	List<EmployeeBO> listOfEmployeeBO = new ArrayList<>();
	EmployeeBO employeeBOOne = new EmployeeBO("EPAM001","Srinivas","Chintakindhi","srinivas_chintakindhi","9866436639",date1,"Active");
	EmployeeBO employeeBOTwo = new EmployeeBO("EPAM002","Akhter","Rasool","akhter_rasool","9866436639",date1,"Active");
	listOfEmployeeBO.add(employeeBOOne);
	listOfEmployeeBO.add(employeeBOTwo);
	return listOfEmployeeBO;
}
}
