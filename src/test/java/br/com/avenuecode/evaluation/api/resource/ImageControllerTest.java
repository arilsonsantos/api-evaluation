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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
public class ImageControllerTest {

	@Autowired
    TestRestTemplate testRestTemplate;
	
	@Test	
    public void testFindAllImage() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/api/evaluation/images", String.class);
        
        String expected = "[{\"type\":\"JPG\"},{\"type\":\"PNG\"}]";
        
        assertEquals(result.getBody(), expected);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        
        log.info("*** (GET) Resource:  /api/evaluation/images - Status: " +  result.getStatusCode());
	}
	
	@Test	
    public void testFindAllImageWithProduct() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/api/evaluation/images/product", String.class);
        
        String expected = "[{\"type\":\"JPG\"},{\"type\":\"PNG\"}]";
        
        assertEquals(result.getBody(), expected);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        
        log.info("*** (GET) Resource:  /api/evaluation/images - Status: " +  result.getStatusCode());
	}
}
