package root.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import root.service.ExternalInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class AnnotationAspect {
    @Pointcut("@annotation(root.CheckRequest) && args(root.service.ExternalInfo)")
    public void annotationInterceptor(){

    }

    @Around("annotationInterceptor()")
    public Object annotationInterceptorLogging(ProceedingJoinPoint jp) throws Throwable {
        Object [] args = jp.getArgs();
        int index = 0;

        for(int i  = 0; i < args.length; i++){
            String s = args[i].getClass().getName();
            if(args[i].getClass().getName().equals("root.service.ExternalInfo")){
                index = i;

                break;
            }
        }

        Class c = args[index].getClass();
        Field f = c.getDeclaredField("id");
        f.setAccessible(true);
        int val1 = (Integer) f.get(args[index]);

        c = jp.getTarget().getClass();
        f = c.getDeclaredField("idNotProcess");
        f.setAccessible(true);
        int val2 = (Integer) f.get(jp.getTarget());

        Object result = false;

        if(val1 != val2) {
            result = jp.proceed();
        }
        else {
            log.info("Method " + jp.getSignature().getName() + " of class " + jp.getTarget().getClass().getName() + " was not invoked");
        }

        return result;
    }
}
