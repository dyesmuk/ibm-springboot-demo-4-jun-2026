package com.ibm.demo.messaging;

public enum EmployeeQueue {

	CREATED("employee.created"), UPDATED("employee.updated"), DELETED("employee.deleted");

	private final String name;

	EmployeeQueue(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public interface Names {
		String CREATED = "employee.created";
		String UPDATED = "employee.updated";
		String DELETED = "employee.deleted";
	}
}

//package com.ibm.demo.messaging;
//
//public enum EmployeeQueue {
//
//	CREATED("employee.created"), UPDATED("employee.updated"), DELETED("employee.deleted");
//
//	// Also expose as constants for use in @JmsListener
//	public interface Names {
//		String CREATED = "employee.created";
//		String UPDATED = "employee.updated";
//		String DELETED = "employee.deleted";
//	}
//
//	private final String queueName;
//
//	EmployeeQueue(String queueName) {
//		this.queueName = queueName;
//	}
//
//	public String getName() {
//		return queueName;
//	}
//}
//
////package com.ibm.demo.messaging;
////
/////**
//// * Central place for all JMS queue names. Using constants avoids typos — one
//// * wrong letter in a queue name means messages go nowhere and no error is
//// * thrown.
//// */
////public class EmployeeMessageConstants {
////
////	private EmployeeMessageConstants() {
////	}
////
////	public static final String QUEUE_EMPLOYEE_CREATED = "employee.created";
////	public static final String QUEUE_EMPLOYEE_UPDATED = "employee.updated";
////	public static final String QUEUE_EMPLOYEE_DELETED = "employee.deleted";
////}