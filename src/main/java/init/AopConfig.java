package init;

//import aop.AopCase;
import common.CustomerAspectJ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2017/11/6
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    @Bean
    public CustomerAspectJ customerAspectJ(){
        return new CustomerAspectJ();
    }
}
