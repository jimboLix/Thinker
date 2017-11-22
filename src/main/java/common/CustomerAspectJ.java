package common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: 自定义切面
 * 使用了@Aspect的注解也不会将这个类视为切面，需要启动自动代理功能。
 * 可以使用@EnableAspectJAutoProxy注解或者在配置文件中使用
 * <aop:sdpectj-autoproxy></aop:sdpectj-autoproxy>标签
 * @date 2017/11/15
 */
@Aspect
public class CustomerAspectJ {

    @Pointcut("execution(* action.service.UserService.save(action.bean.User))")
    public void saveUser(){}

    @Around("saveUser()")
    public Object saveUserAround(ProceedingJoinPoint pj) throws Throwable {
        String packageName = pj.getTarget().getClass().getName();
        String methodName = pj.getSignature().getName();
        StringBuilder key = new StringBuilder(packageName);
        key.append(methodName);
        Object[] args = pj.getArgs();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        StringWriter stringWriter = new StringWriter();
        for(Object o : args){
            objectMapper.writeValue(stringWriter,o);
        }

        key.append(stringWriter.toString());
        System.out.println("key is -----------"+key.toString());
        return  pj.proceed();
    }

}
