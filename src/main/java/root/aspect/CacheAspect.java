package root.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import root.MethodArgs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Aspect
@Slf4j
public class CacheAspect {
    public static final Map <String, Map<MethodArgs, Object>> CACHE = new ConcurrentHashMap<>();

    @Pointcut("execution(* *(..))")
    public void cacheAspect(){

    }

    @Around("cacheAspect()")
    public Object checkMethodCache(ProceedingJoinPoint jp) throws Throwable{
        Map<MethodArgs, Object> map = CACHE.get(jp.getSignature().getName());
        LinkedList <Object> argsList = new LinkedList();
        Object result = null;

        Object [] o = jp.getArgs();

        for(int i = 0; i < o.length; i++){
            argsList.add(o[i]);
        }

        MethodArgs args = new MethodArgs(argsList);

        if(map != null){
            if(!map.containsKey(args)){
                log.info("Method " + jp.getSignature().getName() + " of class " + jp.getTarget().getClass() + " has NO cache");

                result = jp.proceed();

                map.put(args, result);
            }
            else{
                result = map.get(args);

                log.info("Method " + jp.getSignature().getName() + " of class " + jp.getTarget().getClass() + " has cache. Result = " + result);
            }
        }
        else{
            log.info("Method " + jp.getSignature().getName() + " of class " + jp.getTarget().getClass() + " has NO cache");

            map = new ConcurrentHashMap<>();
            result = jp.proceed();

            map.put(args, result);

            CACHE.put(jp.getSignature().getName(), map);
        }

        return result;
    }
}
