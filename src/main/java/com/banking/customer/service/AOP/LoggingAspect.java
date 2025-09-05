package com.banking.customer.service.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.banking.customer.service.ServiceImpl.*.*(..))")
    public void customerServiceMethods() {}

    @Before("customerServiceMethods()")
    public void logMethodStart(JoinPoint joinPoint) {
        log.info("Entering Method: {} with argument: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "customerServiceMethods()" , returning = "result")
    public void logMethodEnd(JoinPoint joinPoint , Object result) {
        log.info("Exiting method: {} with result: {}",
                joinPoint.getSignature().toShortString(),
                result
                );
    }

    @AfterThrowing(pointcut = "customerServiceMethods()" , throwing = "ex")
    public void logMethodException(JoinPoint joinPoint , Throwable ex) {
        log.error("Exception in method: {} with message: {}",
                joinPoint.getSignature().toShortString(),
                ex.getMessage(),
                ex
                );
    }

    @Around("customerServiceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long duration = System.currentTimeMillis() -start; log.info("Execution time of: {} : {} ms",
                joinPoint.getSignature().toShortString(),duration);
        return proceed;
        
    }
}
