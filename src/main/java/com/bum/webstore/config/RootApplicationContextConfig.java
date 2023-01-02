package com.bum.webstore.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("com.bum.webstore")
public class RootApplicationContextConfig {
	@Bean
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

	private DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(org.mariadb.jdbc.Driver.class.getName());
		ds.setUrl("jdbc:mariadb://localhost:3306/webstore");
		ds.setUsername("myself");
		ds.setPassword("1234");
		return ds;
	}
}
