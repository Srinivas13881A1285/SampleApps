package test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import com.ctc.controller.EmployeeController;
import com.ctc.service.EmployeeInsertServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {

	@Mock
	private EmployeeInsertServiceImpl employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	View mockView;
	
	private MockMvc mockMvc;
	
	
	@Test
	public void welcomeTest() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		mockMvc.perform(get("/welcome.htm")).andExpect(status().isOk()).
											andExpect(view().name("welcome.jsp"));
	}
	
	@AfterClass
	public static void tearDown() {
		
	}

}
