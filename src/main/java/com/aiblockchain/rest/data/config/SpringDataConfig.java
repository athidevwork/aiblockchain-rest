package com.aiblockchain.rest.data.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Athi
 * 
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.aiblockchain.rest.data"}, enableDefaultTransactions = false)
@ComponentScan(basePackages={"com.aiblockchain.rest.data"})
@ImportResource("classpath:/spring/application-context.xml")
public class SpringDataConfig {
/*	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}*/
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.aiblockchain.rest.data" });
		
		//JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		JpaVendorAdapter jpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
		em.setJpaVendorAdapter(jpaVendorAdapter);
		//em.setJpaProperties(jpaProperties());
		em.setJpaProperties(jpaEclipseLinkProperties());
		
		return em;
	}

	private Properties jpaEclipseLinkProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.put("eclipselink.logging.level", "FINE");
		jpaProperties.put("eclipselink.logging.file", "jpa.log");
		jpaProperties.put("eclipselink.logging.timestamp", "true");
		jpaProperties.put("eclipselink.ddl-generation", "none");
		return jpaProperties;
	}

	@Bean
	public Properties jpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
		jpaProperties.put("hibernate.show_sql", "true");
		jpaProperties.put("hibernate.format_sql", "true");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		//jpaProperties.put("hibernate.enable_lazy_load_no_trans", "true");
		
		//jpaProperties.put("spring.jpa.hibernate.ddl-auto", "none");
		
		return jpaProperties;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/aiblockchain?autoReconnect=true&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("p@ssword");
		
		return dataSource;
	}
	
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setDataSource(dataSource());
        transactionManager.setJpaProperties(jpaProperties());
        return transactionManager;
    }	
	
    /*@Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(H2).build();
    }
    
	private JpaVendorAdapter jpaVendorAdapter() {
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(false);
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setDatabase(Database.H2);		
		return jpaVendorAdapter;
	}*/
	    
}
