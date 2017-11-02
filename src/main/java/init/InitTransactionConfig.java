package init;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description:
 * @date 2017/10/11
 */
@Configuration
@EnableTransactionManagement
public class InitTransactionConfig {

    @Resource
    private DruidDataSource dataSource;


    @Bean
    public DataSourceTransactionManager initTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

}
