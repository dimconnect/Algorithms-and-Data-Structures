package aop.annotationbased;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logging {

	@Pointcut("execution(* aop.annotationbased.Student.*(..))")
	private void selectAll(){}
	
	@Before("selectAll()")
	public void beforeAdvice(){
		System.out.println("Going to setup student profile!");
	}
	
	@After("selectAll()")
	public void afterAdvice(){
		System.out.println("Student profile has been setup!");
	}
	
	@AfterReturning("execution(* aop.annotationbased.Student.getAge(..))")
	public void printAge(){
		System.out.println(" age");
	}
	
	@AfterReturning("execution(* aop.annotationbased.Student.getName(..))")
	public void println(){
		System.out.println();
	}
	
	@AfterReturning(pointcut="selectAll()", returning="retVal")
	public void afterReturningAdvice(Object retVal){
		System.out.print("Returning: " + retVal);
	}
	
	@AfterThrowing(pointcut="selectAll()", throwing="iae")
	public void afterThrowingAdvice(IllegalArgumentException iae){
		System.out.println("There has been exception: " + iae);
	}
}
