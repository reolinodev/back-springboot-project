package com.back.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ParameterAop {

    @Pointcut("execution(* com.back.admin.service..*(..))")
    private void cut() {
    }

    /**
     * 전달받은 파라미터를 반환한다.
     */
    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("\n[Excute Method] : " + joinPoint.toString());

        for (Object obj : args) {
            log.info("\n[Param Type] : " + obj.getClass().getSimpleName());
            log.info("\n[Param Value] : " + obj);
        }
    }

    /**
     * 결과값을 반환한다.
     */
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        log.info("\n[Return Obj] : " + returnObj);
    }
}
