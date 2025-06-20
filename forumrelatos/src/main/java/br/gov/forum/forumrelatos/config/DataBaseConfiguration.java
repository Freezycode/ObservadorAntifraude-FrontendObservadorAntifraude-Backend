package br.gov.forum.forumrelatos.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //@Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }
    /**
    *Configuracao do Hikary
     */
    @Bean
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        config.setMaximumPoolSize(10); /**implementando o maximo de conexões liberadas */
        config.setMinimumIdle(1); /** tamanho inicial do pool*/
        config.setPoolName("Forum-db-pool");
        config.setMaxLifetime(600000); /**600 mil ms 10 minutos de conexão*/
        config.setConnectionTimeout(100000); /**timeout para poder conseguir uma coneão*/
        config.setConnectionTestQuery("select 1"); /** querry de teste*/

        return new HikariDataSource(config);
    }

}
