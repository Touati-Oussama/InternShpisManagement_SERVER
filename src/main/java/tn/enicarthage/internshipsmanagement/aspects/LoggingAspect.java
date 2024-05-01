package tn.enicarthage.internshipsmanagement.aspects;


import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {

    private static final Logger LG = Logger.getLogger(LoggingAspect.class);

    @Before("execution(* tn.enicarthage.internshipsmanagement.restControllers.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        LG.info("In method " + name + " : ");
    }
}