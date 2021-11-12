package com.pms.employee;

import javax.sql.DataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@ComponentScan("com.pms.petopia")
@EnableWebMvc
@PropertySource("classpath:com/pms/petopia/config/jdbc.properties")
@EnableTransactionManagement
@MapperScan("com.pms.petopia.dao")
public class AppConfig {

  @Bean
  public DataSource dataSource(
      @Value("${jdbc.driver}") String jdbcDriver,
      @Value("${jdbc.url}") String jdbcUrl,
      @Value("${jdbc.username}") String jdbcUsername,
      @Value("${jdbc.password}") String jdbcPassword) {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);
    return ds;
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx) throws Exception {

    LogFactory.useLog4J2Logging();

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);

    sqlSessionFactoryBean.setTypeAliasesPackage("com.pms.petopia.domain");
    sqlSessionFactoryBean.setMapperLocations(appCtx.getResources("classpath:com/pms/petopia/mapper/*Mapper.xml"));
    return sqlSessionFactoryBean.getObject();

  }

  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

  @Bean
  public ViewResolver viewResolver() {
    // 기존의 기본 ViewResolver를 이 메소드가 리턴하는 객체로 대체한다.
    // 요청 핸들러가 리턴한 jsp 이름을 가지고 앞뒤로 경로를 붙여서 찾는다.
    InternalResourceViewResolver vr = new InternalResourceViewResolver();

    vr.setViewClass(JstlView.class); // JSTL을 처리해줄 클래스 지정
    vr.setPrefix("/WEB-INF/jsp/");
    vr.setSuffix(".jsp");

    return vr;

  }

}
