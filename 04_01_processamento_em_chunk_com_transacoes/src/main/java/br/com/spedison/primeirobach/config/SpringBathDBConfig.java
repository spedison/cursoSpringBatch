package br.com.spedison.primeirobach.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/***
 * Exemplo para criar datasources distintos usando um para o springbath e outro para a aplicação.
 * @Primary é usando para forçar a substituição do beam do datasource primário (Já definido no Auto-Configuration)
 * A transação utilizada para a execução do Step da App é um DataSourceTransactionManager que usa
 * domente uma conexão.
 */
@Configuration
public class SpringBathDBConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource sprinBatchDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("appDataSource")
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("appTransactionManager")
    public PlatformTransactionManager createTrasaction(@Qualifier("appDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
