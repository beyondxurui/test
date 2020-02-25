//package com.jlxu.demo.config;
//
//import org.activiti.spring.SpringAsyncExecutor;
//import org.activiti.spring.SpringProcessEngineConfiguration;
//import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//
//
///**
// * 功能
// * 创建时间：2020年02月24日
// * 文件名称：ActivitiConfig
// * 版本：1.0.0
// * 最后修改时间：2020/2/24 20:33
// *
// * @auther jlxu
// */
//@Configuration
//public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource activitiDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
//            PlatformTransactionManager transactionManager,
//            SpringAsyncExecutor springAsyncExecutor) throws IOException {
//        return baseSpringProcessEngineConfiguration(activitiDataSource(), transactionManager, springAsyncExecutor);
//    }
//}
