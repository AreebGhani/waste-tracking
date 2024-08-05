package org.aacounty.wastetrackingng;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DataSourceInfoLogger implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceInfoLogger.class);

    private final DataSourceProperties dataSourceProperties;

    public DataSourceInfoLogger(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public void run(String... args) {
        logger.info("Database URL: {}", dataSourceProperties.getUrl());
        logger.info("Database Driver Class Name: {}", dataSourceProperties.getDriverClassName());
    }
}
