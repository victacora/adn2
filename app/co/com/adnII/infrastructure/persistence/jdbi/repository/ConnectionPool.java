package co.com.adnII.infrastructure.persistence.jdbi.repository;

import com.typesafe.config.Config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.inject.Inject;


public class ConnectionPool {

    private Config conf;

    @Inject
    public ConnectionPool(Config conf) {
        this.conf = conf;
    }

    public HikariDataSource getDataSource() {
        HikariConfig jdbcConfig = new HikariConfig();
        Config bdConf=conf.getConfig("db.default");
        Config hikaricpConf=conf.getConfig("db.default.hikaricp");
        jdbcConfig.setMaximumPoolSize(hikaricpConf.getInt("maximumPoolSize"));
        jdbcConfig.setMinimumIdle(hikaricpConf.getInt("minimumIdle"));
        jdbcConfig.setJdbcUrl(bdConf.getString("url"));
        jdbcConfig.setUsername(bdConf.getString("username"));
        jdbcConfig.setPassword(bdConf.getString("password"));
        return new HikariDataSource(jdbcConfig);
    }


}

