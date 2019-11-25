package com.sdm.StarRental.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
public class DatabaseConfig {


        @Value("${mysql.hostname}")
        private String hostname;

        @Value("${mysql.database}")
        private String database;

        @Value("${mysql.username}")
        private String userName;

        @Value("${mysql.password}")
        private String password;

        @Value("${mysql.port}")
        private String port;

        /**
         * This method provides you with the object od {@link DB} using which you can query MySQL.
         *
         *
         * @return
         * @throws Exception
         */
        @Bean
        public Connection getSQLDb() throws Exception {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url      = "jdbc:mysql://"+hostname+":"+Integer.valueOf(port)+"/"+database;
            String user     = userName;
            String pass = password;

            Connection connection =
                    DriverManager.getConnection(url, user, pass);

            return connection;

        }
}
