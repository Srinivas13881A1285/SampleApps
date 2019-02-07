package com.ctc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ctc.command.Employee;
import com.ctc.dto.EmployeeDTO;
import com.ctc.service.EmployeeManagementService;
import com.ctc.validator.EmployeeValidator;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeManagementService employeeService;

	@Mock
	private EmployeeValidator employeeValidator;

	private MockMvc mockMvc;

	Date dateOfJoining;

	@Before
	public void setUp() throws ParseException {
		dateOfJoining = new SimpleDateFormat("dd-MM-yyyy").parse("08-01-2018");
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	public void testWelcome() throws Exception {
		mockMvc.perform(get("/welcome.htm")).andExpect(status().isOk()).andExpect(view().name("welcome"));
	}

	@Test
	public void testDisplayForm() throws Exception {
		mockMvc.perform(get("/getRegistrationForm.htm")).andExpect(status().isOk())
				.andExpect(view().name("employeeregistration")).andExpect(model().attributeExists("employee"));
	}

	@Test
	public void testSubmitForm() throws Exception {
		EmployeeDTO employeeDTO = getEmployeeTestData();
		Mockito.when(employeeService.registerEmployee(employeeDTO)).thenReturn("Employee Added Successfully");
		Mockito.when(employeeValidator.supports(Employee.class)).thenReturn(true);
		mockMvc.perform(post("/registerEmployee.htm")).andExpect(status().isOk())
				.andExpect(view().name("resultstatus"));
	}

	@Test
	public void testUpdateEmployeesStatusInActive() throws Exception {
		Mockito.when(employeeService.updateEmployeesStatus(new String[] { "EPAM001,EPAM002" }, "InActive"))
				.thenReturn("{EPAM001, EPAM002} Employees Status Updated to InActive Successfully");
		mockMvc.perform(post("/updateStatusToInActive.htm").param("checkBoxes", new String[] { "EPAM001,EPAM002" }))
				.andExpect(status().isOk()).andExpect(view().name("resultstatus"))
				.andExpect(model().attributeExists("statusMessage")).andExpect(model().attribute("statusMessage",
						"{EPAM001, EPAM002} Employees Status Updated to InActive Successfully"));
	}

	@Test
	public void testDeleteEmployees() throws Exception {
		Mockito.when(employeeService.deleteEmployees(new String[] { "EPAM001", "EPAM002" }))
				.thenReturn("{EPAM001, EPAM002} Employees Deleted Successfully");
		mockMvc.perform(post("/deleteEmployees.htm").param("checkBoxes", new String[] { "EPAM001", "EPAM002" }))
				.andExpect(status().isOk()).andExpect(view().name("resultstatus"))
				.andExpect(model().attributeExists("statusMessage"))
				.andExpect(model().attribute("statusMessage", "{EPAM001, EPAM002} Employees Deleted Successfully"));
	}

	@Test
	public void testGetAllEmployees() throws Exception {
		List<EmployeeDTO> listOfEmployeeDTO = getListOfEmployeesDTOTestData();
		Mockito.when(employeeService.getAllEmployeesList()).thenReturn(getListOfEmployeesDTOTestData());
		mockMvc.perform(get("/getAllEmployees.htm")).andExpect(status().isOk())
				.andExpect(view().name("allemployeedetails")).andExpect(model().attributeExists("employeesList"))
				.andExpect(model().attribute("employeesList", listOfEmployeeDTO));
	}

	public EmployeeDTO getEmployeeTestData() throws ParseException {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId("EPAM100");
		employeeDTO.setFirstName("Akhter");
		employeeDTO.setLastName("Rasool");
		employeeDTO.setEmail("akhter_rasool@epam.com");
		employeeDTO.setContactNumber("9866436639");
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse("08-01-2018");
		employeeDTO.setDateOfJoining(date1);
		employeeDTO.setStatus("New");
		return employeeDTO;
	}

	public List<EmployeeDTO> getListOfEmployeesDTOTestData() throws ParseException {
		EmployeeDTO employeeDTOOne = new EmployeeDTO("EPAM001", "Srinivas", "Chintakindhi", "srinivas_chintakindhi",
				"9866436639", dateOfJoining, "Active");
		EmployeeDTO employeeDTOTwo = new EmployeeDTO("EPAM002", "Akhter", "Rasool", "akhter_rasool", "9866436639",
				dateOfJoining, "Active");
		List<EmployeeDTO> listOfEmployeeDTO = new ArrayList<>();
		listOfEmployeeDTO.add(employeeDTOOne);
		listOfEmployeeDTO.add(employeeDTOTwo);
		return listOfEmployeeDTO;
	}

	@After
	public void tearDown() {
		dateOfJoining = null;
		mockMvc = null;
	}
}
