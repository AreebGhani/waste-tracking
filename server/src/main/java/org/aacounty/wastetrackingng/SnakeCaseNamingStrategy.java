package org.aacounty.wastetrackingng;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnakeCaseNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    private static final long serialVersionUID = 1L;

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        Logger logger = LoggerFactory.getLogger(DataSourceInfoLogger.class);

        logger.info(name + " to be read as Table " + addUnderscores(name.getText()).toLowerCase());
        return new Identifier(addUnderscores(name.getText()).toLowerCase(), name.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        Logger logger = LoggerFactory.getLogger(DataSourceInfoLogger.class);

        logger.info(name + " to be read as Column " + addUnderscores(name.getText()).toLowerCase());
        return new Identifier(addUnderscores(name.getText()).toLowerCase(), name.isQuoted());
    }

    protected static String addUnderscores(String name) {
        final StringBuilder buf = new StringBuilder(name.replace('.', '_'));
        for (int i = 1; i < buf.length() - 1; i++) {
            if (Character.isLowerCase(buf.charAt(i - 1)) &&
                    Character.isUpperCase(buf.charAt(i)) &&
                    Character.isLowerCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString();
    }
}
