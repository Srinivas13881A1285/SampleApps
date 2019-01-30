package com.ctc.command;

import java.util.Arrays;
import java.util.Date;

public class EmployeeCommand {
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNumber;
	private Date dateOfJoining;
	private String status;
	private String[] deleteCheckBoxes;
	private int clientToken;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getDeleteCheckBoxes() {
		return deleteCheckBoxes;
	}
	public void setDeleteCheckBoxes(String[] deleteCheckBoxes) {
		this.deleteCheckBoxes = deleteCheckBoxes;
	}
	public int getClientToken() {
		return clientToken;
	}
	public void setClientToken(int clientToken) {
		this.clientToken = clientToken;
	}
	@Override
	public String toString() {
		return "EmployeeCommand [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", dateOfJoining=" + dateOfJoining + ", status=" + status
				+ ", deleteCheckBoxes=" + Arrays.toString(deleteCheckBoxes) + ", clientToken=" + clientToken + "]";
	}
	
	

}
