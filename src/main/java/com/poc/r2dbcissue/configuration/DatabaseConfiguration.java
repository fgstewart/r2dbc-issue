package com.poc.r2dbcissue.configuration;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;

import java.time.Duration;

@Configuration
public class DatabaseConfiguration {

  @Bean("preload")
  public ConnectionFactoryInitializer initializer(
      @Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
    ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(connectionFactory);

    CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
    populator.addPopulators(
        new ResourceDatabasePopulator(new ClassPathResource("psql-schema.sql")));

    initializer.setDatabasePopulator(populator);
    return initializer;
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    final String dbUrl = "r2dbc:postgresql://test@localhost:5432/test";
    ConnectionFactory connectionFactory = ConnectionFactories.get(dbUrl);
    ConnectionPoolConfiguration pool =
        ConnectionPoolConfiguration.builder(connectionFactory)
            .maxIdleTime(Duration.ofMinutes(30))
            .initialSize(1)
            .maxSize(10)
            .maxCreateConnectionTime(Duration.ofSeconds(10))
            .build();
    return new ConnectionPool(pool);
  }

}
