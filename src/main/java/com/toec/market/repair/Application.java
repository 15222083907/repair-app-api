
package com.toec.market.repair;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@EnableTransactionManagement//启用事务
@MapperScan("com.toec.market.repair.mapper")
public class Application {

	
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jdbc.poolSize}")
	private Integer poolSize;
	
 	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	} 
	/**
	 * SpringBoot整合数据源
	 * @return
	 */
	@Bean
	public DataSource hikariDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setReadOnly(false);
		dataSource.setConnectionTimeout(30000);
		dataSource.setIdleTimeout(600000);
		dataSource.setMaxLifetime(1800000);
		dataSource.setMaximumPoolSize(poolSize);
		return dataSource;
	}
	
}
