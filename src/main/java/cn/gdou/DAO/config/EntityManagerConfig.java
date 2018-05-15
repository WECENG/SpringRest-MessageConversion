package cn.gdou.DAO.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
@EnableJpaRepositories("cn.gdou.DAO.repository")
@EnableTransactionManagement
public class EntityManagerConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource ds, JpaVendorAdapter adapter){
        LocalContainerEntityManagerFactoryBean ebf=
                new LocalContainerEntityManagerFactoryBean();
        ebf.setDataSource(ds);
        ebf.setJpaVendorAdapter(adapter);
        ebf.setPackagesToScan("cn.gdou.DAO.entity");
        Properties properties=new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        ebf.setJpaProperties(properties);
        return ebf;
    }

    @Bean
    public JpaVendorAdapter adapter(){
        HibernateJpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL57InnoDBDialect");
        return adapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager();
    }

    @Bean
    public BeanPostProcessor persistenceTraslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
