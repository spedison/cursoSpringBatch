package br.com.spedison.primeirobach.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppDBConfig {

    @Bean("appDBDataSource")
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("appEntityManagerFactory")
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean("appDBEntityManager")
    public LocalContainerEntityManagerFactoryBean appEntityManager(
            @Qualifier("appEntityManagerFactory") EntityManagerFactoryBuilder factoryBuilder,
            @Qualifier("appDBDataSource") DataSource dataSource) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MariaDB10Dialect");
        properties.put("spring.jpa.properties.hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "validate");
        return factoryBuilder
                .dataSource(dataSource)
                .properties(properties)
                .packages("br.com.spedison.primeirobach.model")
                .build();
    }

    @Bean("appTransaction")
    public PlatformTransactionManager createTrasaction(@Qualifier("appDBEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getNativeEntityManagerFactory());
    }
}