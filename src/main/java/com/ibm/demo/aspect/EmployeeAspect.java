package com.ibm.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * AOP Aspect for Employee service layer.
 *
 * Key AOP concepts demonstrated here:
 *
 * Aspect — this class itself (@Aspect) Advice — the methods annotated
 * with @Before, @After etc. Pointcut — the expression that defines WHERE advice
 * applies JoinPoint — a specific moment during execution (method call) Weaving
 * — Spring wires the aspect into the target at runtime
 *
 * Advice types:
 * 
 * @Before — runs BEFORE the method executes
 * @After — runs AFTER the method (always, like finally)
 * @AfterReturning — runs AFTER successful return
 * @AfterThrowing — runs AFTER an exception is thrown
 * @Around — wraps the method — runs BEFORE and AFTER
 */
@Aspect
@Component
public class EmployeeAspect {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeAspect.class);

	// ── Pointcut Definitions ──────────────────────────────────────────────────
	// Define once, reuse across multiple advice methods
	// Pointcut expression targets all methods in EmployeeServiceImpl

	@Pointcut("execution(* com.ibm.demo.service.impl.EmployeeServiceImpl.*(..))")
	public void employeeServiceMethods() {
	}

	// Pointcut targeting only write operations (create, update, delete)
	@Pointcut("execution(* com.ibm.demo.service.impl.EmployeeServiceImpl.createEmployee(..))"
			+ " || execution(* com.ibm.demo.service.impl.EmployeeServiceImpl.updateEmployee(..))"
			+ " || execution(* com.ibm.demo.service.impl.EmployeeServiceImpl.deleteEmployee(..))")
	public void employeeWriteOperations() {
	}

	// ── @Before ───────────────────────────────────────────────────────────────
	// Runs BEFORE the method executes
	// Use case: logging, security checks, input validation

	@Before("employeeServiceMethods()")
	public void logBeforeMethod(JoinPoint joinPoint) {
		// joinPoint.getSignature() — gives method name and class
		// joinPoint.getArgs() — gives the arguments passed
		LOG.info("[BEFORE] Calling: {}.{}() with args: {}", joinPoint.getTarget().getClass().getSimpleName(),
				joinPoint.getSignature().getName(), joinPoint.getArgs());
	}

	// ── @After ────────────────────────────────────────────────────────────────
	// Runs AFTER the method — whether it succeeded or threw an exception
	// Like a finally block — always executes
	// Use case: cleanup, releasing resources

	@After("employeeServiceMethods()")
	public void logAfterMethod(JoinPoint joinPoint) {
		LOG.info("[AFTER] Completed: {}.{}()", joinPoint.getTarget().getClass().getSimpleName(),
				joinPoint.getSignature().getName());
	}

	// ── @AfterReturning ───────────────────────────────────────────────────────
	// Runs AFTER method returns successfully — NOT called if exception thrown
	// 'returning' binds the actual return value to the parameter
	// Use case: logging result, post-processing the return value

	@AfterReturning(pointcut = "employeeServiceMethods()", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		LOG.info("[AFTER RETURNING] {}.{}() returned: {}", joinPoint.getTarget().getClass().getSimpleName(),
				joinPoint.getSignature().getName(), result);
	}

	// ── @AfterThrowing ────────────────────────────────────────────────────────
	// Runs ONLY when the method throws an exception
	// 'throwing' binds the actual exception to the parameter
	// Use case: error logging, alerting, auditing failures

	@AfterThrowing(pointcut = "employeeServiceMethods()", throwing = "ex")
	public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
		LOG.error("[AFTER THROWING] {}.{}() threw: {} — message: {}", joinPoint.getTarget().getClass().getSimpleName(),
				joinPoint.getSignature().getName(), ex.getClass().getSimpleName(), ex.getMessage());
	}

	// ── @Around ───────────────────────────────────────────────────────────────
	// Most powerful advice — wraps the entire method execution
	// Must call joinPoint.proceed() to actually execute the target method
	// Use case: execution time measurement, transaction management, caching
	// Applied only to write operations here to avoid duplicate logging with
	// @Before/@After

	@Around("employeeWriteOperations()")
	public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

		String methodName = joinPoint.getSignature().getName();
		LOG.info("[AROUND - START] {}", methodName);

		long startTime = System.currentTimeMillis();

		Object result;
		try {
			// proceed() calls the actual target method
			// without this line, the real method NEVER executes
			result = joinPoint.proceed();
		} finally {
			long timeTaken = System.currentTimeMillis() - startTime;
			LOG.info("[AROUND - END] {} completed in {} ms", methodName, timeTaken);
		}

		return result;
	}
}