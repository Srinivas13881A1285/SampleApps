package com.ctc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctc.bo.EmployeeBO;
import com.ctc.dao.EmployeeDAO;
import com.ctc.dto.EmployeeDTO;
@Service
public class EmployeeInsertServiceImpl implements EmployeeInsertService {
	
	@Autowired
	private EmployeeDAO employeeDao;
	
	public void setEmployeeDao(EmployeeDAO employeeDao) {
		this.employeeDao =employeeDao;
	}
	@Override
	public final String registerEmployee(EmployeeDTO employeeDTO) {
		EmployeeBO employeeBO = new EmployeeBO(employeeDTO.getId(),employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getEmail(),employeeDTO.getContactNumber(),employeeDTO.getDateOfJoining(),employeeDTO.getStatus());
		int result = employeeDao.insert(employeeBO);
		if(result == 1)
			return "Insertion Success";
		else
			return "Insertion Fail";
	}

	@Override
	public final List<EmployeeDTO> getAllEmployeesList() {
		List<EmployeeBO> listOfEmployeeBO = employeeDao.getAllEmployees();
		List<EmployeeDTO> listOfEmployeeDTO = new ArrayList<EmployeeDTO>();
		for(EmployeeBO employeeBO : listOfEmployeeBO) {
			EmployeeDTO employeeDTO = new EmployeeDTO(employeeBO.getId(),employeeBO.getFirstName(),employeeBO.getLastName(),employeeBO.getEmail(),employeeBO.getContactNumber(),employeeBO.getDateOfJoining(),employeeBO.getStatus());
			listOfEmployeeDTO.add(employeeDTO);
		}
		return listOfEmployeeDTO;
	}

	@Override
	public final String deleteEmployees(String[] Ids) {
		int result = employeeDao.deleteEmployees(Ids);
		if(result==0)
			return "Delete unsucessful";
		else 
			return "Delete Successful";
	}
	@Override
	public final String updateEmployees(String[] ids,String status) {
		int result = employeeDao.updateEmployees(ids,status);
		if(result ==  0)
			return "Update Fail";
		else
			return "Update Sucess";
	}

	

	
	

}
