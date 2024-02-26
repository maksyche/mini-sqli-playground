package com.maksyche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Main {

    private final JdbcTemplate jdbcTemplate;

    public Main(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDatabase() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (" +
                             "   id NUMBER NOT NULL," +
                             "   username VARCHAR(255) NOT NULL," +
                             "   pass_hash VARCHAR(255) NOT NULL," +
                             "   is_admin BOOLEAN NOT NULL," +
                             "   info VARCHAR(255) NOT NULL," +
                             "   PRIMARY KEY (id)," +
                             "   UNIQUE (username)" +
                             ");");

        jdbcTemplate.execute("TRUNCATE TABLE users;");

        jdbcTemplate.execute("INSERT INTO users (id, username, pass_hash, is_admin, info) " +
                             "VALUES (1, 'maksyche', 'c21f969b5f03d33d43e04f8f136e7682', true, 'I`m teaching security');");
        jdbcTemplate.execute("INSERT INTO users (id, username, pass_hash, is_admin, info) " +
                             "VALUES (2, 'john_doe', '6541e866609a08e3348734c66dc5071e', false, 'I`m a hacker');");
        jdbcTemplate.execute("INSERT INTO users (id, username, pass_hash, is_admin, info) " +
                             "VALUES (3, 'jane_doe', '6541e866609a08e3348734c66dc5071e', false, 'I want to be a hacker');");
    }
}