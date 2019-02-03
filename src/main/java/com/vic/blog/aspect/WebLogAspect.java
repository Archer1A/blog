package com.vic.blog.aspect;


import com.vic.blog.aspect.annotation.LogAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class WebLogAspect {

   private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

  // @Pointcut("execution(public * com.vic.blog.Controller..*.*(..))")
   @Pointcut("@annotation(com.vic.blog.aspect.annotation.LogAnnotation)")
   public void webLog(){};

   @After("webLog()")
   public void after(JoinPoint joinpoint){
      MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();
       Method method = methodSignature.getMethod();
       LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
       System.out.println("注解拦截器 " + logAnnotation.name());

   }
}
