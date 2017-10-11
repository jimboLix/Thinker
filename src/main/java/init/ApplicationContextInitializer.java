package init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: 在servlet 3.0环境中，web容器会在类路径中查找
 * 实现了javax.servlet.ServletContainerInitializer接口的类，
 * 如果能找到此类就会用它来配置Servlet容器。Spring提供了这个接口的实现，
 * 名为SpringServletContainerInitializer，
 * 这个实现又查找实现了WebApplicationInitializer接口的类。
 * Spring 3.2引入了一个WebApplicationInitializer的一个便利的基础实现类：
 * AbstractAnnotationConfigDispatcherServletInitializer。
 * 它会创建DispatcherServleContext和RootContext，现在只需扩展它即可。
 * @date 2017/10/11
 */
public class ApplicationContextInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationRootConfig.class,InitTransactionConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    protected String[] getServletMappings() {
        return new String[0];
    }
}
