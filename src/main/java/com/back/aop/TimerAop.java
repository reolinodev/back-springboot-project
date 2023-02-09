package com.back.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Slf4j
public class TimerAop {

    /**
     * 서비스의 실행시간을 로그로 반환한다.
     */
    @Around("execution(* com.back.admin.service..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("\n[Excute Time] : " + stopWatch.getTotalTimeSeconds());
        }
    }
}