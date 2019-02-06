package test;

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

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ctc.command.Employee;
import com.ctc.controller.EmployeeController;
import com.ctc.dto.EmployeeDTO;
import com.ctc.service.EmployeeInsertService;
import com.ctc.validator.EmployeeValidator;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest3 {

	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeInsertService employeeService;
	
	@Mock
	private EmployeeValidator employeeValidator;
	
	private MockMvc mockMvc;
	
	static Date date1;
	
	@BeforeClass
	public static void setUp() throws ParseException {
		date1=new SimpleDateFormat("dd-MM-yyyy").parse("08-01-2018");
	}
	
	@Test
	public void testWelcome() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		mockMvc.perform(get("/welcome.htm"))
				.andExpect(status().isOk())
				.andExpect(view().name("welcome"));
	}
	
	@Test
	public void testDisplayForm() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		mockMvc.perform(get("/insert.htm"))
			.andExpect(status().isOk())
			.andExpect(view().name("input"))
			.andExpect(model().attributeExists("employee"));
	}
	
	@Test
	public void testSubmitForm() throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		EmployeeDTO employeeDTO = getEmployeeTestData();
		Mockito.when(employeeService.registerEmployee(employeeDTO)).thenReturn("Insertion Success");
		Mockito.when(employeeValidator.supports(Employee.class)).thenReturn(true);
		mockMvc.perform(post("/insert.htm"))
				.andExpect(status().isOk())
				.andExpect(view().name("result"));
	}				
			

	@Test
	public void testUpdateEmployee() throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		Mockito.when(employeeService.updateEmployees(new String[] {"1"},"New")).thenReturn("Update Success");
		mockMvc.perform(post("/editEmpNew.htm").param("checkBoxes", new String[] {"1"}))
			.andExpect(status().isOk())
			.andExpect(view().name("result"))
			.andExpect(model().attributeExists("result"))
			.andExpect(model().attribute("result", "Update Success"));
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		Mockito.when(employeeService.deleteEmployees(new String[]{"1","2"})).thenReturn("Delete Successful");
		mockMvc.perform(post("/deleteEmp.htm").param("checkBoxes", new String[] {"1","2"}))
				.andExpect(status().isOk())
				.andExpect(view().name("result"))
				.andExpect(model().attributeExists("result"))
				.andExpect(model().attribute("result", "Delete Successful"));
	}
	
	@Test
	public void testShowAllEmployees() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		List<EmployeeDTO> listOfEmployeeDTO = getListOfEmployeesTestData();
		Mockito.when(employeeService.getAllEmployeesList()).thenReturn(getListOfEmployeesTestData());
		mockMvc.perform(get("/selectAll.htm")).andExpect(status().isOk()).andExpect(view().name("show_all")).andExpect(model().attributeExists("listDTO")).andExpect(model().attribute("listDTO", listOfEmployeeDTO));
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
}
