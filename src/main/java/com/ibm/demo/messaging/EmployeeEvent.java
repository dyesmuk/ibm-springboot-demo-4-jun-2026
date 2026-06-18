package com.ibm.demo.messaging;

import java.io.Serializable;

/**
 * The message payload sent to the JMS queue.
 *
 * Must implement Serializable — JMS serializes this object when placing it on
 * the queue and deserializes it on the consumer side.
 *
 * Kept minimal — only the data the consumer needs. Never send the full Employee
 * entity as a message — keep it lightweight.
 */
public class EmployeeEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private String employeeId;
	private String firstName;
	private String lastName;
	private String email;

	// eventType tells the consumer what happened: CREATED / UPDATED / DELETED
	private String eventType;

	public EmployeeEvent() {
	}

	public EmployeeEvent(String employeeId, String firstName, String lastName, String email, String eventType) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.eventType = eventType;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		return "EmployeeEvent [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", eventType=" + eventType + "]";
	}
}