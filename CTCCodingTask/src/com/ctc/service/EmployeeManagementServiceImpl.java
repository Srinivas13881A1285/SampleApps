package com.ctc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctc.bo.EmployeeBO;
import com.ctc.dao.EmployeeDAO;
import com.ctc.dto.EmployeeDTO;
@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {
	
	@Autowired
	private EmployeeDAO employeeDao;
	
	@Override
	public String registerEmployee(EmployeeDTO employeeDTO) {
		EmployeeBO employeeBO = new EmployeeBO(employeeDTO.getId(),employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getEmail(),employeeDTO.getContactNumber(),employeeDTO.getDateOfJoining(),employeeDTO.getStatus());
		int result = employeeDao.insert(employeeBO);
		if(result == 1)
			return "Employee Added Successfully";
		else
			return "Employee Not Added Successfully";
	}

	@Override
	public List<EmployeeDTO> getAllEmployeesList() {
		List<EmployeeBO> listOfEmployeeBO = employeeDao.getAllEmployees();
		List<EmployeeDTO> listOfEmployeeDTO = new ArrayList<>();
		for(EmployeeBO employeeBO : listOfEmployeeBO) {
			EmployeeDTO employeeDTO = new EmployeeDTO(employeeBO.getId(),employeeBO.getFirstName(),employeeBO.getLastName(),employeeBO.getEmail(),employeeBO.getContactNumber(),employeeBO.getDateOfJoining(),employeeBO.getStatus());
			listOfEmployeeDTO.add(employeeDTO);
		}
		return listOfEmployeeDTO;
	}

	@Override
	public String deleteEmployees(String[] ids) {
		int result = employeeDao.deleteEmployees(ids);
		if(result == ids.length)
			return Arrays.toString(ids)+" Employees Deleted Successfully";
		else 
			return Arrays.toString(ids)+" Employees Not Deleted Successfully";
	}
	@Override
	public String updateEmployees(String[] ids,String status) {
		int result = employeeDao.updateEmployees(ids,status);
		if(result ==  ids.length)
			return Arrays.toString(ids)+" Employees Status Updated to "+status+" Successfully";
		else
			return Arrays.toString(ids)+" Employees Status Not Updated to "+status+" Successfully";
	}

	

	
	

}
