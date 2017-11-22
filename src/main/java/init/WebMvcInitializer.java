package init;

import common.CustomerExceptionHandler;
import common.CustomerHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ruihui.li
 * @version V1.0
 * @Title: Think_In_Java
 * @Package init
 * @Description: 在使用配置文件的方式中SpringMvc组件的方式使用的是
 * <mvc:annotation-driven/>起用注解驱动的MVC组件，在使用Java配置类时可以使用
 * 带有@EnableWebMvc注解的类。@ComponentScan启用组件扫描
 * @date 2017/10/22
 */
@Configuration
@EnableWebMvc
@ComponentScan("action.controller")
public class WebMvcInitializer extends WebMvcConfigurerAdapter{

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(1073741824);
        commonsMultipartResolver.setResolveLazily(true);
        commonsMultipartResolver.setMaxInMemorySize(10485760);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }

    /**
     * 配置Freemarker的视图解析器
     *
     * @return
     */
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setOrder(1);
        freeMarkerViewResolver.setCache(true);
        freeMarkerViewResolver.setContentType("text/html; charset=UTF-8");
        //在页面中使用${request.contextPath}就可获得contextPath
        freeMarkerViewResolver.setRequestContextAttribute("request");
        freeMarkerViewResolver.setExposeSpringMacroHelpers(true);
        //注意此时不需要加/WEB-INF/
        freeMarkerViewResolver.setPrefix("/views/");
        freeMarkerViewResolver.setSuffix(".ftl");

        return freeMarkerViewResolver;
    }

    /**
     * 使用Freemarker还需要配置FreeMarkerConfigurer
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        Properties properties = new Properties();
        properties.put("datetime_format", "yyyy-MM-dd HH:mm:ss");
        properties.put("date_format", "yyyy-MM-dd");
        properties.put("time_format", "HH:mm:ss");
        freeMarkerConfigurer.setFreemarkerSettings(properties);
        return freeMarkerConfigurer;
    }

    @Bean
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new CustomerExceptionHandler();
    }

    @Bean
    public HandlerInterceptor handlerInterceptor(){
        return new CustomerHandlerInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(handlerInterceptor()).addPathPatterns("/home/home.do");
    }
}
