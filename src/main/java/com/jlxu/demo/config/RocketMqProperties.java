package com.jlxu.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 功能:rocketMQ配置文件
 * 版本：1.0.0
 * 最后修改时间：2020/1/4 20:38
 *
 * @auther jlxu
 */
@Configuration
@PropertySource("classpath:config/application.yml")
@ConfigurationProperties(prefix = "rocket")
@Data
public class RocketMqProperties {
    private String host;
    private String port;
}
