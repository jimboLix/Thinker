package init;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * @author ruihui.li
 * @version V1.0
 * @Description:
 * @date 2017/10/11
 */
@Configuration
public class IntitSessionFactory {


    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        org.springframework.core.io.Resource[] resources = new org.springframework.core.io.Resource[]{
                new ClassPathResource("action/dao/UserDao.xml")
        };
        sqlSessionFactory.setMapperLocations(resources);
        return sqlSessionFactory;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("action.dao");
        return mapperScannerConfigurer;
    }
}
