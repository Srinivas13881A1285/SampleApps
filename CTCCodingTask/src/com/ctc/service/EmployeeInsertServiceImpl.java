package com.ctc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
	public String registerEmployee(EmployeeDTO employeeDTO) {
		System.out.println(employeeDTO);
		EmployeeBO employeeBO = new EmployeeBO(employeeDTO.getId(),employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getEmail(),employeeDTO.getContactNumber(),employeeDTO.getDateOfJoining(),employeeDTO.getStatus());
		int result = employeeDao.insert(employeeBO);
		if(result == 0)
			return "insertion failed";
		else
			return "insertion success";
	}

	@Override
	public List<EmployeeDTO> getAllEmployeesList() {
		List<EmployeeBO> listOfEmployeeBO = employeeDao.getAllEmployees();
		List<EmployeeDTO> listOfEmployeeDTO = new ArrayList<EmployeeDTO>();
		for(EmployeeBO employeeBO : listOfEmployeeBO) {
			EmployeeDTO employeeDTO = new EmployeeDTO(employeeBO.getId(),employeeBO.getFirstName(),employeeBO.getLastName(),employeeBO.getEmail(),employeeBO.getContactNumber(),employeeBO.getDateOfJoining(),employeeBO.getStatus());
			listOfEmployeeDTO.add(employeeDTO);
		}
		return listOfEmployeeDTO;
	}

	@Override
	public String deleteEmployees(List<EmployeeDTO> listOfEmployeeDTO) {
		List<EmployeeBO> listOfEmployeeBO = new ArrayList<EmployeeBO>();
		for(EmployeeDTO employeeDTO : listOfEmployeeDTO) {
			EmployeeBO employeeBO = new EmployeeBO(employeeDTO.getId(),employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getEmail(),employeeDTO.getContactNumber(),employeeDTO.getDateOfJoining(),employeeDTO.getStatus());
			listOfEmployeeBO.add(employeeBO);
		}
		int[] result = employeeDao.deleteEmployees(listOfEmployeeBO);
		if(result==null)
			return "Delete unsucessful";
		else 
			return "Delete Successful";
	}

	@Override
	public String updateEmployeeDetails(List<EmployeeDTO> listOfEmployeeDTO) {
		List<EmployeeBO> listOfEmployeeBO = new ArrayList<EmployeeBO>();
		for(EmployeeDTO employeeDTO : listOfEmployeeDTO) {
			EmployeeBO employeeBO = new EmployeeBO(employeeDTO.getId(),employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getEmail(),employeeDTO.getContactNumber(),employeeDTO.getDateOfJoining(),employeeDTO.getStatus());
			listOfEmployeeBO.add(employeeBO);
		}
		int[] result = employeeDao.updateEmployees(listOfEmployeeBO);
		if(result==null)
			return "Update unsucessful";
		else 
			return "Update Successful";
	}
	public EmployeeDTO fetchEmployeeByNo(int empNo) {
		EmployeeBO bo = null;
		EmployeeDTO dto=null;
		//use DAO
		bo=employeeDao.getEmpByNo(empNo);
		//convert bo to dto
		dto=new EmployeeDTO();
		BeanUtils.copyProperties(bo, dto);
		return dto;

	}// fetchEmployeeByNo(-)
	
	public String modifyEmployee(EmployeeDTO dto) {
		EmployeeBO bo=null;
		int count=0; 
		bo=new  EmployeeBO();
		BeanUtils.copyProperties(dto, bo);
		//use DAO
		count=employeeDao.updateEmployee(bo);
		if (count==0)
			return "Record not upated";
		else
			return "Record updated successsfully";

	}
	public String removeEmployee(int empNo) {
		int count=0;
		//use dao 
		count=employeeDao.deleteEmployee(empNo);
		if(count==0)
			return "Record not deleted";
		else
			return "Record deleted";

	}// removeEmployee(-)
}
