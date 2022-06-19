package com.aop;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect @Component
public class LogAspect {
	Logger log =Logger.getLogger(LogAspect.class);
	
	@Pointcut("execution(* com.controller.*.*(..))")  //���������
	private void anyMethod() {}
	
	@Around("anyMethod()")
	public Object aroundMethod(ProceedingJoinPoint point) {
		Object result=null;
		
		log.trace("ǰ��֪ͨ������..�������¼�� trace��Ϣ");
		
		String methodName=point.getSignature().getName();
		log.info("��ǰִ�еķ�����:"+methodName);

		try {
			result=  point.proceed();	
			log.info("����ִ�����,������Ǻ���֪ͨ");
		}
		catch(Throwable e) {
			e.printStackTrace();
			log.error("����ִ�г���,����֪ͨ");
			
		}finally {
			log.info("����֪ͨ������");
		}
		
		return result;
	}

}