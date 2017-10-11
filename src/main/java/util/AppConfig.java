package util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: 获取配置文件的工具类
 * @date 2017/10/11
 */
public class AppConfig extends PropertyPlaceholderConfigurer {

    private static Map<String,Object> ctxApplicationProperties;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxApplicationProperties = new HashMap<String, Object>();
        for (Object key : props.keySet()){
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxApplicationProperties.put(keyStr,value);
        }
    }

    public static String getProperty(String key){
        return (String) ctxApplicationProperties.get(key);
    }
}
