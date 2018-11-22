package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = RANDOM_PORT,
        properties = {
                "spring.datasource.initialization-mode=always",
        }
)
public class TaxControllerTest {

    @LocalServerPort
    public int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldExposeTaxRestEndpointForPoland() {
        // when
        Double vat = restTemplate.getForEntity("http://127.0.0.1:" + port + "/poland/CAR/100", Double.class).getBody();

        // then
        assertThat(vat).isEqualTo(23.00);
    }

    @Test
    public void shouldExposeTaxRestEndpointForGermany() {
        // when
        Double vat = restTemplate.getForEntity("http://127.0.0.1:" + port + "/germany/CAR/100", Double.class).getBody();

        // then
        assertThat(vat).isEqualTo(15.00);
    }

}
