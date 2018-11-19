package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MariaDBContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = DemoApplicationTests.Initializer.class)
public class DemoApplicationTests {

	@ClassRule
	public static MariaDBContainer mariaDBContainer = (MariaDBContainer) new MariaDBContainer()
			.withPrivilegedMode(true);

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

	@Test
	public void contextLoads() {
		Assertions.assertThat(true).isTrue();
	}

}
