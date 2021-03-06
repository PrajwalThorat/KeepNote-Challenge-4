package com.stackroute.keepnote.aspectj;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */

@Aspect
@Component
public class LoggingAspect {
	/*
	 * Write loggers for each of the methods of User controller, any particular
	 * method will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */

	 private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	 @Pointcut("execution (* com.stackroute.keepnote.controller.UserController.*(..))")
	 public void allControllermethod(){

	 }

	 @Before("allControllerMethod()")
	 public void beforeAdvice(JoinPoint joinPoint){
		 logger.info("************ @Before ************");
		 logger.debug("Method Name : " + joinPoint.getSignature().getName());
		 logger.debug("Method Args : " + Arrays.toString(joinPoint.getArgs()));
		 logger.info("************ @Before End ************");
	 }
	 @After("allControllerMethods()")
	public void afterAdvice(JoinPoint joinPoint) {
		logger.info("************ @After ************");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("Method Args : " + Arrays.toString(joinPoint.getArgs()));
		logger.info("*********************************");
	}
	@AfterReturning(value="allControllerMethods()",returning = "result")
	public void afterReturningAdvice(JoinPoint joinPoint,Object result) {
		logger.info("************ @AfterReturning ************");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
		logger.debug("Return Value: " + result);
		logger.info("*****************************************");
	}
	@AfterThrowing(value="allControllerMethods()", throwing="error")
	public void afterThrowingAdvice(JoinPoint joinPoint, Throwable error) {
		logger.info("************ @AfterThrowing ************");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
		logger.debug("Exception : " + error);
		logger.info("****************************************");
	}

}
