package com.study.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {

    @Autowired
    private ApplicationContext context; //스프링 IoC컨테이너의 인터페이스
    //이 컨테이너에서 객체(빈)을 관리하고 필요한곳에 주입하는 역할을 한다.


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    //히카리 설정을 빈으로 등록
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    //데이터소스로 히카리를 사용하고 빈으로 등록
    public DataSource dataSource() {
        //DataSource : 데이터베이스와의 연결을 추상화한 인터페이스  java.sql패키지
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        //SqlSessionFactory : MyBatis의 핵심구성요소. SQL세션을 생성하는 역할
        //이 세션을 통해 쿼리를 실행하고 결과를 반환받는다.
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
//      factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*Mapper.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        //SqlSessionTemplate : MyBatis-Spring 연동모듈에서 제공. 스프링의 SqlSession 구현체
        //트랜잭션 관리, 예외 변환 등 스프링에 특화된 기능 제공
        //SqlSession을 직접 사용하면 여러 번의 SQL 쿼리 실행 중 예외가 발생했을 때 트랜잭션 롤백 등의 처리를 수동으로 해야 하는 불편함이 있음
        return new SqlSessionTemplate(sqlSessionFactory());
    }

}
