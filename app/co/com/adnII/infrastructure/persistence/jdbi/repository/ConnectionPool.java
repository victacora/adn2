package co.com.adnII.infrastructure.persistence.jdbi.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.typesafe.config.Config;

import javax.inject.Inject;


public class ConnectionPool {

    private Config conf;

    @Inject
    public ConnectionPool(Config conf) {
        this.conf = conf;
    }

    public HikariDataSource getDataSource() {
        HikariConfig jdbcConfig = new HikariConfig();
        jdbcConfig.setMaximumPoolSize(conf.getInt("db.default.hikaricp.maximumPoolSize"));
        jdbcConfig.setMinimumIdle(conf.getInt("db.default.hikaricp.minimumIdle"));
        jdbcConfig.setJdbcUrl(conf.getString("db.default.jdbcUrl"));
        jdbcConfig.setUsername(conf.getString("db.default.username"));
        jdbcConfig.setPassword(conf.getString("db.default.password"));
        return new HikariDataSource(jdbcConfig);
    }


}

