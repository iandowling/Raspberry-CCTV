package com.raspberrycctv;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import domain.User;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "domain")
@EntityScan(basePackages = "domain")
@PropertySource({"classpath:application.properties"})
public class RaspberryCctvApplication extends WebMvcAutoConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(RaspberryCctvApplication.class);
	
	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RaspberryCctvApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RaspberryCctvApplication.class);
    }
	
	@Autowired
    JdbcTemplate jdbcTemplate;

    public void run(String... strings) throws Exception {

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE users");
        jdbcTemplate.execute("CREATE TABLE users(" +
                "id BIGINT NOT NULL AUTO_INCREMENT, username VARCHAR(50) NOT NULL, "
                + "email VARCHAR(80) NOT NULL, password VARCHAR(80) NOT NULL,"
                + "PRIMARY KEY (id), UNIQUE INDEX (email))");
        jdbcTemplate.execute("INSERT INTO users VALUES(" + "100, 'admin', 'admin@rcctv.com', '$2a$04$JN.PmLiY2kxyyHxZp.pvU.5DbDGOtJSW/UE8yZhzSznZfu90OlgUG')");
        
        // Split up the array of whole names into an array of username/email names
        List<Object[]> splitUpNames = Arrays.asList("admin admin@rcctv.com").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting user record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO users(username, email, password) VALUES (?,?,?)", splitUpNames);

        log.info("Querying for customer records where username = 'Admin':");
        jdbcTemplate.query(
                "SELECT id, username, email FROM customers WHERE username = ?", new Object[] { "admin" },
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"))
        ).forEach(user -> log.info(user.toString()));
    }
}
