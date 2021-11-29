package root.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class ExecutionLoggingAspect {
    @Pointcut("execution(* root.service.*.*(..))")
    public void methodLogging(){

    }

    @Before("methodLogging()")
    public void methodStartLogging(JoinPoint jp){
        log.info("Method " + jp.getSignature().getName() + " of " + jp.getTarget().getClass() + " STARTED execution");
    }

    @After("methodLogging()")
    public void methodFinishLogging(JoinPoint jp){
        log.info("Method " + jp.getSignature().getName() + " of " + jp.getTarget().getClass() + " FINISHED execution");
    }
}
