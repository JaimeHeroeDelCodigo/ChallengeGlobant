package mx.com.globant.hotel.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;

@Component
@Aspect
public class EndPointHotelTiming {	  
	  long startTime; 
	  long endTime;
	  long resTime;
	  @Pointcut("execution(* mx.com.globant.hotel.rest.*.*(..))")	  
	  public void timingMethod() {		  
	  }	  
	  
	  // Consejos  ********************************************************************	  
	  
	  @Before(value = "timingMethod()")
	  public void advicePreTiming() {
		startTime = System.nanoTime();  
	  }	  
	  
	  @After(value="timingMethod()")
	  public void advicePostTiming(JoinPoint jp) {
		  endTime = System.nanoTime();
		  resTime = (endTime - startTime)/1000000 ;
		  LogManager.getLogger("El tiempo de respuesta del servicio "
	               + jp.getSignature().getName() 
	               + " fue de " 
	               + resTime
	               + " ms");
		 
	  }	  
}
