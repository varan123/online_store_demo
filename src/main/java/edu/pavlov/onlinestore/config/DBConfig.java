package edu.pavlov.onlinestore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    /**
     * The @Value annotation is used to inject values from the
     * application.properties file into the fields of the class.
     */
    @Value("${spring.datasource.url}")
    private String url;

    /**
     * The @Value annotation is used to inject values from the
     * application.properties file into the fields of the class.
     */
    @Value("${spring.datasource.username}")
    private String username;

    /**
     * The @Value annotation is used to inject values from the
     * application.properties file into the fields of the class.
     */
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * The @Value annotation is used to inject values from the
     * application.properties file into the fields of the class.
     */
    @Value("${spring.datasource.driverClassName}")
    private String driver;

    /**
     * The @Bean annotation is used to define a bean in the Spring
     * application context.
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

}
