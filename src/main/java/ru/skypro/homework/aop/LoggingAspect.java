package ru.skypro.homework.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public * *.* (..))")
    public void isAnyMethod() {
    }

    @Pointcut("within(ru.skypro.homework.service..*)")
    public void isServiceLayer() {
    }

    @Around("isAnyMethod() && isServiceLayer()")
    public Object addLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = getClassName(joinPoint);
        String methodName = getMethodName(joinPoint);

        log.trace("Method - {} was invoked in class - {}", className, methodName);

        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            log.trace("Method - {} was finished in class - {}", className, methodName);
        }
    }


    private String getClassName(ProceedingJoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

}
