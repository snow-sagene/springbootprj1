package com.study.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.study.domain..*Controller.*(..)) || execution(* com.study.domain..*Service.*(..)) || execution(* com.study.domain..*Mapper.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String name = joinPoint.getSignature().getDeclaringTypeName();
        //getSignature()는 실행되는 대상 객체의 메서드에 대한 정보를 가지고 옵니다
        String type =
                StringUtils.contains(name, "Controller") ? "Controller ===> " :
                StringUtils.contains(name, "Service") ? "Service ===> " :
                StringUtils.contains(name, "Mapper") ? "Mapper ===> " :
                "";

        log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
        return joinPoint.proceed();
    }

}
