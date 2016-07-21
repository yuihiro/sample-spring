package nagi.starter.SpringRest.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import nagi.starter.SpringRest.common.CommonBean;

@Aspect
@Component
public class LoggerAspect {

	static String name = "";
	static String type = "";

	@Around("execution(* nagi.starter.SpringRest..*.*(..))")
	//@Around("bean(Service")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		type = joinPoint.getSignature().getDeclaringTypeName();

		if (type.indexOf("Controller") > -1) {
			name = "Controller : ";
		} else if (type.indexOf("Service") > -1) {
			name = "Service : ";
		} else if (type.indexOf("Repository") > -1) {
			name = "Repository : ";
		} else {
			name = "Call : ";
		}
		CommonBean.log.debug(name + type + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}
}