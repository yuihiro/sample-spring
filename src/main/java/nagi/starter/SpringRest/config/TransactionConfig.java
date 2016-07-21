package nagi.starter.SpringRest.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@EnableTransactionManagement /* <tx:annotation-driven> */
@Configuration
//@ImportResource("classpath:aop.xml")
public class TransactionConfig implements TransactionManagementConfigurer {

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://10.10.200.207:3306/air?zeroDateTimeBehavior=convertToNull&amp;autoReconnect=true");
		dataSource.setUsername("air");
		dataSource.setPassword("unet00!");
		dataSource.setInitialSize(10);
		dataSource.setMaxActive(100);
		dataSource.setMinIdle(0);
		dataSource.setMaxIdle(50);
		dataSource.setMaxWait(10000);
		dataSource.setTimeBetweenEvictionRunsMillis(5000);
		dataSource.setMinEvictableIdleTimeMillis(5000);
		dataSource.setRemoveAbandoned(true);
		dataSource.setRemoveAbandonedTimeout(60);
		dataSource.setLogAbandoned(true);
		//dataSource.setValidationQuery("select 1");		
		//dataSource.setTestOnBorrow(true);
		return dataSource;
	}
}