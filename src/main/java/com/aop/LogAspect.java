package com.aop;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect @Component
public class LogAspect {
	Logger log =Logger.getLogger(LogAspect.class);
	
	@Pointcut("execution(* com.controller.*.*(..))")  //定义切入点
	private void anyMethod() {}
	
	@Around("anyMethod()")
	public Object aroundMethod(ProceedingJoinPoint point) {
		Object result=null;
		
		log.trace("前置通知触发了..在这里记录了 trace信息");
		
		String methodName=point.getSignature().getName();
		log.info("当前执行的方法是:"+methodName);

		try {
			result=  point.proceed();	
			log.info("方法执行完比,输出的是后置通知");
		}
		catch(Throwable e) {
			e.printStackTrace();
			log.error("方法执行出错,例外通知");
			
		}finally {
			log.info("最终通知触发了");
		}
		
		return result;
	}

}