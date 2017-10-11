package init;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import util.AppConfig;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: 对spring组件的初始化
 * 如：扫描包
 * 用于初始化非web組件
 * @date 2017/10/11
 */
@Configuration
@ComponentScan(basePackages = {"action.dao","action.service"})
public class ApplicationRootConfig {

    /**
     * 读取系统配置文件
     * @return
     */
    @Bean
    public PropertyPlaceholderConfigurer getProperties(){
        PropertyPlaceholderConfigurer appConfig = new AppConfig();
        Resource jdbcResource = new ClassPathResource("properties/jdbc.properties");
        appConfig.setLocations(jdbcResource);
        return appConfig;
    }

    /**
     * 初始化数据连接池
     * @return
     */
    @Bean
    public DruidDataSource initDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(AppConfig.getProperty("jdbcUrl"));
        dataSource.setDriverClassName(AppConfig.getProperty("driverClass"));
        dataSource.setUsername(AppConfig.getProperty("user"));
        dataSource.setPassword(AppConfig.getProperty("password"));
        return dataSource;
    }

}
