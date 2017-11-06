package com.praveenpremaratne.securitysample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.praveenpremaratne.securitysample")
@PropertySource(value = {"classpath:application.properties"})
@EnableTransactionManagement
public class AppConfig {

    @Autowired
    private static Environment env;

    @Profile("prod")
    @Configuration
    static class configProd {
    }

    @Profile("test")
    @Configuration
    @PropertySource(value = {"classpath:test.properties"})
    static class configTest {

        @Bean(name = "dataSource")
        public DataSource dataSourceTest() {
            return new EmbeddedDatabaseBuilder()
                    .generateUniqueName(true)
                    .setType(EmbeddedDatabaseType.H2)
                    .setScriptEncoding("UTF-8")
                    .addScripts("templates/schema.sql", "templates/data.sql")
                    .build();
        }

        @Bean
        public JpaVendorAdapter jpaVendorAdapter() {
            HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
            adapter.setShowSql(true);
            adapter.setDatabase(Database.H2);
            adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
            adapter.setGenerateDdl(true);
            return adapter;
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.format_sql", String.valueOf(true));

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("com.praveenpremaratne.securitysample.entities");
        emf.setJpaProperties(properties);
        return emf;
    }

    @Bean
    public BeanPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
