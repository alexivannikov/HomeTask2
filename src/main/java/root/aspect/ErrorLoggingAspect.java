package root.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class ErrorLoggingAspect {
    @Pointcut("execution(* *(..))")
    public void errorLogging(){

    }

    @AfterThrowing(value = "errorLogging()", throwing = "exception")
    public void methodErrorLogging(JoinPoint jp, Exception exception){
        log.info("Method " + jp.getSignature().getName() + " of " + jp.getTarget().getClass() + " was aborted  with exception " + exception) ;
    }
}
