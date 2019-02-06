package com.ctc.bo;

import java.util.Date;
import java.util.Objects;

public class EmployeeBO {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNumber;
	private Date dateOfJoining;
	private String status;
	
	public EmployeeBO() {
		
	}

	public EmployeeBO(String id, String firstName, String lastName, String email, String contactNumber,
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
		return "EmployeeBO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", dateOfJoining=" + dateOfJoining + ", status=" + status + "]";
	}

	@Override
    public boolean equals(Object anotherObj) {
        if (anotherObj == this) return true;
        if (!(anotherObj instanceof EmployeeBO)) {
            return false;
        }
        EmployeeBO employeeBO = (EmployeeBO) anotherObj;
        return Objects.equals(id,employeeBO.id) &&
        		Objects.equals(firstName, employeeBO.firstName) &&
        		Objects.equals(lastName, employeeBO.lastName) &&
        		Objects.equals(email, employeeBO.email) &&
        		Objects.equals(contactNumber, employeeBO.contactNumber) &&
        		Objects.equals(dateOfJoining,employeeBO.dateOfJoining) &&
        		Objects.equals(status,employeeBO.status);
    }

	@Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName,email,contactNumber,dateOfJoining,status);
    }
	
}
