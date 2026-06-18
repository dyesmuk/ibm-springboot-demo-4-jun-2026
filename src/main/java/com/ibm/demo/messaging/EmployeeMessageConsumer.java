package com.ibm.demo.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMessageConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeMessageConsumer.class);

	@JmsListener(destination = EmployeeQueue.Names.CREATED)
	public void onEmployeeCreated(EmployeeEvent event) {

		LOG.info("[CONSUMER] Received from '{}': {}", EmployeeQueue.Names.CREATED, event);

		LOG.info("[CONSUMER] New employee joined: {} {} ({})", event.getFirstName(), event.getLastName(),
				event.getEmail());
	}

	@JmsListener(destination = EmployeeQueue.Names.UPDATED)
	public void onEmployeeUpdated(EmployeeEvent event) {

		LOG.info("[CONSUMER] Received from '{}': {}", EmployeeQueue.Names.UPDATED, event);

		LOG.info("[CONSUMER] Employee updated: {} {} ({})", event.getFirstName(), event.getLastName(),
				event.getEmail());
	}

	@JmsListener(destination = EmployeeQueue.Names.DELETED)
	public void onEmployeeDeleted(EmployeeEvent event) {

		LOG.info("[CONSUMER] Received from '{}': {}", EmployeeQueue.Names.DELETED, event);

		LOG.info("[CONSUMER] Employee removed: id={}", event.getEmployeeId());
	}
}

//package com.ibm.demo.messaging;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
///**
// * JMS Message Consumer — listens to employee queues and processes messages.
// *
// * @JmsListener marks a method as a message listener. Spring automatically: -
// *              Subscribes the method to the specified queue - Calls the method
// *              whenever a message arrives - Deserializes the message back into
// *              EmployeeEvent
// *
// *              In a real application, each listener could: - Send a
// *              notification - Update an audit log - Trigger another service -
// *              Forward to another system
// *
// *              Here we log the event — keeping it simple for the demo.
// */
//@Component
//public class EmployeeMessageConsumer {
//
//	private static final Logger LOG = LoggerFactory.getLogger(EmployeeMessageConsumer.class);
//
//	/**
//	 * Listens to employee.created queue. Triggered every time a new employee is
//	 * created.
//	 */
//	@JmsListener(destination = EmployeeMessageConstants.QUEUE_EMPLOYEE_CREATED)
//	public void onEmployeeCreated(EmployeeEvent event) {
//		LOG.info("[CONSUMER] Received from '{}': {}", EmployeeMessageConstants.QUEUE_EMPLOYEE_CREATED, event);
//		LOG.info("[CONSUMER] New employee joined: {} {} ({})", event.getFirstName(), event.getLastName(),
//				event.getEmail());
//		// In real app: send welcome email, notify HR system, create user account etc.
//	}
//
//	/**
//	 * Listens to employee.updated queue. Triggered every time an employee record is
//	 * updated.
//	 */
//	@JmsListener(destination = EmployeeMessageConstants.QUEUE_EMPLOYEE_UPDATED)
//	public void onEmployeeUpdated(EmployeeEvent event) {
//		LOG.info("[CONSUMER] Received from '{}': {}", EmployeeMessageConstants.QUEUE_EMPLOYEE_UPDATED, event);
//		LOG.info("[CONSUMER] Employee updated: {} {} ({})", event.getFirstName(), event.getLastName(),
//				event.getEmail());
//		// In real app: sync to other systems, notify manager etc.
//	}
//
//	/**
//	 * Listens to employee.deleted queue. Triggered every time an employee is
//	 * deleted.
//	 */
//	@JmsListener(destination = EmployeeMessageConstants.QUEUE_EMPLOYEE_DELETED)
//	public void onEmployeeDeleted(EmployeeEvent event) {
//		LOG.info("[CONSUMER] Received from '{}': {}", EmployeeMessageConstants.QUEUE_EMPLOYEE_DELETED, event);
//		LOG.info("[CONSUMER] Employee removed: id={}", event.getEmployeeId());
//		// In real app: revoke access, archive records, notify HR etc.
//	}
//}