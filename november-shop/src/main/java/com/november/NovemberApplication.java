package com.november;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * TODO  启动类
 * @author yangzexu
 * @date 2018年1月5日 
 * @version 1.0
 */

@EnableTransactionManagement  //添加事务管理功能
@MapperScan("com.november.**.mapper")
@SpringBootApplication
public class NovemberApplication 
{
	private static Logger logger = Logger.getLogger(NovemberApplication.class);
	
    public static void main( String[] args )
    {
     logger.info("--------------------------------------------------");
     logger.info("-----------------------应用启动---------------------");
     logger.info("--------------------------------------------------");
     SpringApplication.run(NovemberApplication.class, args);
    }
}
