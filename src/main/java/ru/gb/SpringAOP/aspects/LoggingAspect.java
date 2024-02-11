package ru.gb.SpringAOP.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* ru.gb.SpringAOP.services.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("Вызван метод " + methodName + " с параметрами " + arguments + " , пользователем " + userName + ".");
        Object result = joinPoint.proceed();
        return result;
    }

    @Around("@annotation(TrackUserAction)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature() + " запущен в " + LocalDateTime.now());
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info(joinPoint.getSignature() + " выполнен за " + executionTime + "мс");
        return proceed;
    }
}
