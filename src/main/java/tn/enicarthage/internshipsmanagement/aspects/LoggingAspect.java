package tn.enicarthage.internshipsmanagement.aspects;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Component
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {

    private static final Logger logger =
            LogManager.getLogger(LoggingAspect.class);


    @Before("execution(* tn.enicarthage.internshipsmanagement.restControllers.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        logger.info("In method " + name + " : ");
        System.out.println("In method " + name + " : ");
    }
}