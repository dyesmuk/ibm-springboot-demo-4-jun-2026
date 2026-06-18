package com.ibm.demo.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMessageProducer {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeMessageProducer.class);

	private final JmsTemplate jmsTemplate;

	public EmployeeMessageProducer(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendEmployeeCreatedEvent(EmployeeEvent event) {

		LOG.info("[PRODUCER] Sending to queue '{}': {}", EmployeeQueue.CREATED.getName(), event);

		jmsTemplate.convertAndSend(EmployeeQueue.CREATED.getName(), event);
	}

	public void sendEmployeeUpdatedEvent(EmployeeEvent event) {

		LOG.info("[PRODUCER] Sending to queue '{}': {}", EmployeeQueue.UPDATED.getName(), event);

		jmsTemplate.convertAndSend(EmployeeQueue.UPDATED.getName(), event);
	}

	public void sendEmployeeDeletedEvent(EmployeeEvent event) {

		LOG.info("[PRODUCER] Sending to queue '{}': {}", EmployeeQueue.DELETED.getName(), event);

		jmsTemplate.convertAndSend(EmployeeQueue.DELETED.getName(), event);
	}
}
//package com.ibm.demo.messaging;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Component;
//
///**
// * JMS Message Producer — sends EmployeeEvent messages to queues.
// *
// * JmsTemplate is Spring's helper class for JMS operations. It handles: -
// * Opening/closing connections - Creating sessions - Serializing the message
// * object - Sending to the correct queue
// *
// * We just call jmsTemplate.convertAndSend() — one line per send.
// */
//@Component
//public class EmployeeMessageProducer {
//
//	private static final Logger LOG = LoggerFactory.getLogger(EmployeeMessageProducer.class);
//
//	// Spring auto-configures JmsTemplate when Artemis is on the classpath
//	private final JmsTemplate jmsTemplate;
//
//	public EmployeeMessageProducer(JmsTemplate jmsTemplate) {
//		this.jmsTemplate = jmsTemplate;
//	}
//
//	/**
//	 * Send a message to the employee.created queue. Called from EmployeeServiceImpl
//	 * after a new employee is saved.
//	 */
//	public void sendEmployeeCreatedEvent(EmployeeEvent event) {
//		LOG.info("[PRODUCER] Sending to queue '{}': {}", EmployeeMessageConstants.QUEUE_EMPLOYEE_CREATED, event);
//		jmsTemplate.convertAndSend(EmployeeMessageConstants.QUEUE_EMPLOYEE_CREATED, event);
//	}
//
//	/**
//	 * Send a message to the employee.updated queue. Called from EmployeeServiceImpl
//	 * after an employee is updated.
//	 */
//	public void sendEmployeeUpdatedEvent(EmployeeEvent event) {
//		LOG.info("[PRODUCER] Sending to queue '{}': {}", EmployeeMessageConstants.QUEUE_EMPLOYEE_UPDATED, event);
//		jmsTemplate.convertAndSend(EmployeeMessageConstants.QUEUE_EMPLOYEE_UPDATED, event);
//	}
//
//	/**
//	 * Send a message to the employee.deleted queue. Called from EmployeeServiceImpl
//	 * after an employee is deleted.
//	 */
//	public void sendEmployeeDeletedEvent(EmployeeEvent event) {
//		LOG.info("[PRODUCER] Sending to queue '{}': {}", EmployeeMessageConstants.QUEUE_EMPLOYEE_DELETED, event);
//		jmsTemplate.convertAndSend(EmployeeMessageConstants.QUEUE_EMPLOYEE_DELETED, event);
//	}
//}