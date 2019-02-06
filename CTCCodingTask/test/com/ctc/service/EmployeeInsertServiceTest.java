package com.ctc.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ctc.bo.EmployeeBO;
import com.ctc.dao.EmployeeDAOImpl;
import com.ctc.dto.EmployeeDTO;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeInsertServiceTest {

	@Mock
	EmployeeDAOImpl employeeDao;
	
	@InjectMocks
	EmployeeManagementServiceImpl employeeService;
	
	EmployeeDTO employeeDTO;
	
	Date dateOfJoining;
	
	@Before
	public void setUp() throws ParseException {
		dateOfJoining=new SimpleDateFormat("dd-MM-yyyy").parse("08-01-2018");
	}
	
	@Test
	public void registerEmployeeTest() throws ParseException {
		employeeDTO = getEmployeeTestData();
		Mockito.when(employeeDao.insert(Mockito.any(EmployeeBO.class))).thenReturn(1);
		String actualResult = employeeService.registerEmployee(employeeDTO);
		assertEquals("Employee Added Successfully",actualResult);
	}
	
	@Test
	public void updateEmployeesTest() {
		Mockito.when(employeeDao.updateEmployees(new String[] {"EPAM001","EPAM002"},"InActive")).thenReturn(2);
		String actualResult = employeeService.updateEmployees(new String[] {"EPAM001", "EPAM002"},"InActive");
		assertEquals("{EPAM001, EPAM002} Employees Status Updated to InActive Successfully",actualResult);
	}
	
	@Test
	public void employeeDeleteTest() {
		Mockito.when(employeeDao.deleteEmployees(new String[] {"EPAM001","EPAM002"})).thenReturn(2);
		String actualResult = employeeService.deleteEmployees(new String[] {"EPAM001","EPAM002"});
		assertEquals("{EPAM001, EPAM002} Employees Deleted Successfully",actualResult);
	}
	
	@Test
	public void getAllEmployeesListTest() throws ParseException {
		Mockito.when(employeeDao.getAllEmployees()).thenReturn(getListOfEmployeesBOTestData());
		List<EmployeeDTO> actualResult = employeeService.getAllEmployeesList();
		List<EmployeeDTO> expectedResult = getListOfEmployeesTestData();
		assertEquals(actualResult,expectedResult);
		assertEquals(actualResult.get(0).getFirstName(),expectedResult.get(0).getFirstName());
	}
	
	@After
	public void tearDown() {
		dateOfJoining = null;
		employeeDTO = null;
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
		EmployeeDTO employeeDTOOne = new EmployeeDTO("EPAM001","Srinivas","Chintakindhi","srinivas_chintakindhi","9866436639",dateOfJoining,"Active");
		EmployeeDTO employeeDTOTwo = new EmployeeDTO("EPAM002","Akhter","Rasool","akhter_rasool","9866436639",dateOfJoining,"Active");
		List<EmployeeDTO> listOfEmployeeDTO = new ArrayList<>();
		listOfEmployeeDTO.add(employeeDTOOne);
		listOfEmployeeDTO.add(employeeDTOTwo);
		return listOfEmployeeDTO;
	}

public List<EmployeeBO> getListOfEmployeesBOTestData() throws ParseException{
	List<EmployeeBO> listOfEmployeeBO = new ArrayList<>();
	EmployeeBO employeeBOOne = new EmployeeBO("EPAM001","Srinivas","Chintakindhi","srinivas_chintakindhi","9866436639",dateOfJoining,"Active");
	EmployeeBO employeeBOTwo = new EmployeeBO("EPAM002","Akhter","Rasool","akhter_rasool","9866436639",dateOfJoining,"Active");
	listOfEmployeeBO.add(employeeBOOne);
	listOfEmployeeBO.add(employeeBOTwo);
	return listOfEmployeeBO;
}
}
