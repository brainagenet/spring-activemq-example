package net.brainage.example.config;

import net.brainage.core.mapper.SqlMapMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by ms29.seo on 2016-08-26.
 */
@Configuration
public class MapperConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);
        ssfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return ssfb.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory(), ExecutorType.REUSE);
        return sqlSessionTemplate;
    }

    @Bean
    public SqlMapMapper sqlMapMapper(SqlSessionTemplate sqlSessionTemplate) throws Exception {
        SqlMapMapper sqlMapMapper = new SqlMapMapper();
        sqlMapMapper.setSqlSessionTemplate(sqlSessionTemplate());
        return sqlMapMapper;
    }

}
