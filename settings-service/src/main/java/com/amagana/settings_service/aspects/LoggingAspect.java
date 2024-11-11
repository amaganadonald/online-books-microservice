package com.amagana.settings_service.aspects;


import com.amagana.settings_service.dto.AddressRequestDTO;
import com.amagana.settings_service.dto.CategoryRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
	
	/* @Pointcut("execution(* com.amagana.settingsservice.controllers.*.*(..))") */
	@Pointcut("within(com.amagana.settingsservice.services.*)")
	public void loggingPointCut() {}
	
	@Before(value = "loggingPointCut()")
	public void loggingBefore(JoinPoint joinpoint) {
        log.info("Before execute method ::{}", joinpoint.getSignature());
	}
	
	@AfterReturning(value="loggingPointCut()", returning = "result")
	public void loggingAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("after returning for method {}", result);
	}
	
	@AfterThrowing(value="loggingPointCut()", throwing = "exception")
	public void loggingAfterThrowing(JoinPoint joinpoint, Exception exception){
        log.error("after error from method::{} with error {}", joinpoint.getSignature(), exception.getMessage());
	}
	
	@Around(value = "loggingPointCut()")
	public Object loggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object object = joinPoint.proceed();
		if (object instanceof AddressRequestDTO) {
            log.info("Around proceed Address ::{}", joinPoint.getArgs());
		} else if (object instanceof CategoryRequestDTO) {
            log.info("Around proceed Category::{}", joinPoint.getArgs()[0]);
		}
		return object;
	}

}
