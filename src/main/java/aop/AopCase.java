package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description:
 * @date 2017/11/6
 */
@Aspect
public class AopCase {
    @Pointcut("execution(* action.service.UserService.getUserById(..))")
    public void performe(){}

    @Around("performe()")
    public Object doAroud(ProceedingJoinPoint pj){
        Object[] args = pj.getArgs();
        try {
            return pj.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
