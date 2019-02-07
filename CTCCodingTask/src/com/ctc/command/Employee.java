package com.ctc.command;

import java.util.Date;

public class Employee {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNumber;
	private Date dateOfJoining;
	private String status;
	private String[] checkBoxes;

	public void setCheckBoxes(String[] checkBoxes) {
		this.checkBoxes = checkBoxes;
	}

	public String[] getCheckBoxes() {
		return checkBoxes;
	}

	public Employee() {
	}

	public Employee(String id, String firstName, String lastName, String email, String contactNumber,
			Date dateOfJoining, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.dateOfJoining = dateOfJoining;
		this.status = status;
	}

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

	@Override
	public String toString() {
		return "EmployeeCommand [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", dateOfJoining=" + dateOfJoining + ", status=" + status + "]";
	}

}
