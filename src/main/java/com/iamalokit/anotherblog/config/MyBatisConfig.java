package com.iamalokit.anotherblog.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.iamalokit.anotherblog.mapper", "com.iamalokit.anotherblog.dao"})
public class MyBatisConfig {

}
