/**
 * 
 */
package io.starter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author John McMahon Copyright 2015 Starter Inc., all rights reserved.
 *
 */
@Aspect
public class StarterAspects {

	
	@Around("execution(* get*(..))")
	public Object handleGet(ProceedingJoinPoint jp) throws Throwable{
		System.err.println("Executing getter: "+ jp);
		return jp.proceed(jp.getArgs());
	}
	
}