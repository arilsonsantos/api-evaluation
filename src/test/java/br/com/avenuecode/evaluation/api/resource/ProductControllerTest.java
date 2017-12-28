package br.com.avenuecode.evaluation.api.resource;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
public class ProductControllerTest {

	@Autowired
    TestRestTemplate testRestTemplate;
	
	@Test	
    public void testHelloController() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/api/evaluation/products", String.class);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        
    }
}
