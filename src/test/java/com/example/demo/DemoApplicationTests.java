package com.example.demo;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MariaDBContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = RANDOM_PORT, properties = {
        "spring.datasource.initialization-mode=always",
        "spring.jpa.database-platform=org.hibernate.dialect.MariaDB103Dialect",
        "spring.datasource.driver-class-name=org.mariadb.jdbc.Driver"
})
@ContextConfiguration(initializers = DemoApplicationTests.Initializer.class)
public class DemoApplicationTests {

    @ClassRule
    public static MariaDBContainer mariaDBContainer = new MariaDBContainer();

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + mariaDBContainer.getJdbcUrl(),
                    "spring.datasource.username=" + mariaDBContainer.getUsername(),
                    "spring.datasource.password=" + mariaDBContainer.getPassword()
            );
            values.applyTo(configurableApplicationContext);
        }
    }

    @LocalServerPort
    public int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReadDataFromMariaDB() {
        // given data.sql

        // when
        Book book = restTemplate.getForEntity("http://127.0.0.1:" + port + "/books/0", Book.class).getBody();

        // then
        assertThat(book).isEqualTo(new Book(0, "T01", "D01"));
    }

}
