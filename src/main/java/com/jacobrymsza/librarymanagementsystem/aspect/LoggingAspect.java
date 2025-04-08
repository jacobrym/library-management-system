package com.jacobrymsza.librarymanagementsystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging method execution in the library management system.
 */
@Aspect
@Component
public class LoggingAspect {
  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  /**
   * Logs method entry before execution in controllers and services.
   *
   * @param joinPoint the join point providing method details
   */
  @Before("execution(* com.jacobrymsza.librarymanagementsystem.controller..*.*(..)) || "
        + "execution(* com.jacobrymsza.librarymanagementsystem.service..*.*(..))")
  public void logMethodEntry(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    Object[] args = joinPoint.getArgs();
    logger.info("Entering method: {}.{} with arguments: {}", className, methodName, args);
  }

  /**
   * Logs method exit after successful execution in controllers and services.
   *
   * @param joinPoint the join point providing method details
   * @param result the result returned by the method
   */
  @AfterReturning(
      pointcut = "execution(* com.jacobrymsza.librarymanagementsystem.controller..*.*(..)) || "
               + "execution(* com.jacobrymsza.librarymanagementsystem.service..*.*(..))",
      returning = "result")
  public void logMethodExit(JoinPoint joinPoint, Object result) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    logger.info("Exiting method: {}.{} with result: {}", className, methodName, result);
  }

  /**
   * Logs exceptions thrown by methods in controllers and services.
   *
   * @param joinPoint the join point providing method details
   * @param exception the exception thrown by the method
   */
  @AfterThrowing(
      pointcut = "execution(* com.jacobrymsza.librarymanagementsystem.controller..*.*(..)) || "
               + "execution(* com.jacobrymsza.librarymanagementsystem.service..*.*(..))",
      throwing = "exception")
  public void logMethodException(JoinPoint joinPoint, Throwable exception) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    logger.error("Exception in method: {}.{}: {}", className, methodName,
                                                   exception.getMessage(), exception);
  }
}
