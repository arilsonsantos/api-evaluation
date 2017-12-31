package br.com.avenuecode.evaluation.api.resource;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
public class ProductControllerTest {

	@Autowired
    TestRestTemplate testRestTemplate;
	
	@Test	
    public void testGetAllProduct() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/api/evaluation/products", String.class);
        
        String expected = "["
       		+ "{\"id\":1,"
       		+ "\"name\":\"PRODUCT 01\","
       		+ "\"description\":\"DESCRIPTION OF PRODUCT 01\"},"
        		+ "{\"id\":2,"
        		+ "\"name\":\"PRODUCT 01.01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 01.01\","
        		+ "\"parentProductId\":1},"
        		+ "{\"id\":3,"
        		+ "\"name\":\"PRODUCT 02\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 02\"},"
        		+ "{\"id\":4,"
        		+ "\"name\":\"PRODUCT 03\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 03\"},"
        		+ "{\"id\":5,"
        		+ "\"name\":\"PRODUCT 03.01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 03.03\","
        		+ "\"parentProductId\":4},"
        		+ "{\"id\":6,"
        		+ "\"name\":\"PRODUCT 03.01.01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 03.01.01\","
        		+ "\"parentProductId\":5}]";
        
        
        assertEquals(result.getBody(), expected);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        
        log.info("*** Resource:  /api/evaluation/products - Status: " +  result.getStatusCode());
    }
	
	@Test	
    public void testGetAllProductWithProduct() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/api/evaluation/products/product", String.class);
        
        String expected = "["
        		+ "{\"id\":1,"
        	+ "\"name\":\"PRODUCT 01\","
        	+ "\"description\":\"DESCRIPTION OF PRODUCT 01\","
        	+ "\"products\":["
        	+ "{\"id\":2,"
        	+ "\"name\":\"PRODUCT 01.01\","
        	+ "\"description\":\"DESCRIPTION OF PRODUCT 01.01\","
        	+ "\"parentProductId\":1}]},"
        	+ "{\"id\":4,"
        	+ "\"name\":\"PRODUCT 03\","
        	+ "\"description\":\"DESCRIPTION OF PRODUCT 03\","
        	+ "\"products\":["
        	+ "{\"id\":5,"
        	+ "\"name\":\"PRODUCT 03.01\","
        	+ "\"description\":\"DESCRIPTION OF PRODUCT 03.03\","
        	+ "\"parentProductId\":4,"
        	+ "\"products\":["
        	+ "{\"id\":6,"
        	+ "\"name\":\"PRODUCT 03.01.01\","
        	+ "\"description\":\"DESCRIPTION OF PRODUCT 03.01.01\","
        	+ "\"parentProductId\":5}"
        	+ "]}]}]";
        
        
        assertEquals(result.getBody(), expected);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        
        log.info("*** Resource:  /api/evaluation/products/product - Status: " +  result.getStatusCode());
    }
	
	@Test	
    public void testFindAllProductWithImage() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/api/evaluation/products/image", String.class);
        
        String expected = "[{\"id\":1,"
        		+ "\"name\":\"PRODUCT 01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 01\","
        		+ "\"images\":["
        		+ "{"
        		+ "\"id\":1,"
        		+ "\"type\":\"JPG\"}]"
        		+ "},"
        		+ "{\"id\":2,"
        		+ "\"name\":\"PRODUCT 01.01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 01.01\","
        		+ "\"parentProductId\":1,"
        		+ "\"images\":["
        		+ "{\"id\":2,"
        		+ "\"type\":\"JPG\"},"
        		+ "{\"id\":3,"
        		+ "\"type\":\"PNG\"}"
        		+ "]},"
        		+ "{\"id\":5,"
        		+ "\"name\":\"PRODUCT 03.01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 03.03\","
        		+ "\"parentProductId\":4,"
        		+ "\"images\":"
        		+ "[{"
        		+ "\"id\":4,\"type\":"
        		+ "\"JPG\"}]}]";
        
        assertEquals(result.getBody(), expected);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        
        log.info("*** Resource:  /api/evaluation/products/image - Status: " +  result.getStatusCode());
    }
	
	
	
	@Test	
    public void testFindAllProductWithProductAndOrImage() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/api/evaluation/products/product-and-or-image", String.class);
        
        String expected = "[{\"id\":1,"
        		+ "\"name\":\"PRODUCT 01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 01\","
        		+ "\"images\":"
        		+ "["
        		+ "{\"id\":1,"
        		+ "\"type\":\"JPG\"}"
        		+ "],"
        		+ "\"products\":["
        		+ "{\"id\":2,"
        		+ "\"name\":\"PRODUCT 01.01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 01.01\","
        		+ "\"parentProductId\":1,"
        		+ "\"images\":"
        		+ "["
        		+ "{\"id\":3,"
        		+ "\"type\":\"PNG\"},"
        		+ "{\"id\":2,"
        		+ "\"type\":\"JPG\"}"
        		+ "]"
        		+ "}]},"
        		+ "{\"id\":3,"
        		+ "\"name\":\"PRODUCT 02\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 02\"},"
        		+ "{\"id\":4,"
        		+ "\"name\":\"PRODUCT 03\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 03\","
        		+ "\"products\":"
        		+ "["
        		+ "{\"id\":5,"
        		+ "\"name\":\"PRODUCT 03.01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 03.03\","
        		+ "\"parentProductId\":4,"
        		+ "\"images\":"
        		+ "["
        		+ "{\"id\":4,"
        		+ "\"type\":\"JPG\"}"
        		+ "],"
        		+ "\"products\":["
        		+ "{\"id\":6,"
        		+ "\"name\":\"PRODUCT 03.01.01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 03.01.01\","
        		+ "\"parentProductId\":5}"
        		+ "]}]}]";
        
        assertEquals(result.getBody(), expected);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        
        log.info("*** Resource:  /api/evaluation/products/product-and-or-image - Status: " +  result.getStatusCode());
    }
	
	@Test	
    public void testOneProductt() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/api/evaluation/products/1", String.class);
        
        String expected = "{\"id\":1,\"name\":\"PRODUCT 01\",\"description\":\"DESCRIPTION OF PRODUCT 01\"}";
        
        assertEquals(result.getBody(), expected);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        
        log.info("*** Resource:  /api/evaluation/products/1 - Status: " +  result.getStatusCode());
	}
	
	@Test	
    public void testOneProductWithProduct() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/api/evaluation/products/1/product", String.class);
        
        String expected = "{\"id\":1,"
        		+ "\"name\":\"PRODUCT 01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 01\","
        		+ "\"products\":["
        		+ "{\"id\":2,"
        		+ "\"name\":\"PRODUCT 01.01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 01.01\","
        		+ "\"parentProductId\":1}]}";
        
        assertEquals(result.getBody(), expected);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        
        log.info("*** Resource:  /api/evaluation/products/1/product - Status: " +  result.getStatusCode());
	}
	
	@Test	
    public void testOneProductWithImage() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/api/evaluation/products/1/image", String.class);
        
        String expected = "{\"id\":1,\"name\":\"PRODUCT 01\",\"description\":\"DESCRIPTION OF PRODUCT 01\",\"images\":[{\"id\":1,\"type\":\"JPG\"}]}";
        
        assertEquals(result.getBody(), expected);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        
        log.info("*** Resource:  /api/evaluation/products/1/image - Status: " +  result.getStatusCode());
	}
	
	@Test	
    public void testOneProductWithProductAndOrImage() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/api/evaluation/products/1/product-and-or-image", String.class);
        
        String expected = "{\"id\":1,"
        		+ "\"name\":\"PRODUCT 01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 01\","
        		+ "\"images\":"
        		+ "["
        		+ "{\"id\":1,"
        		+ "\"type\":\"JPG\"}"
        		+ "],"
        		+ "\"products\":"
        		+ "["
        		+ "{\"id\":2,"
        		+ "\"name\":\"PRODUCT 01.01\","
        		+ "\"description\":\"DESCRIPTION OF PRODUCT 01.01\","
        		+ "\"parentProductId\":1,"
        		+ "\"images\":["
        		+ "{\"id\":3,"
        		+ "\"type\":\"PNG\"},"
        		+ "{\"id\":2,"
        		+ "\"type\":\"JPG\"}"
        		+ "]}]}";
        
        assertEquals(result.getBody(), expected);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        
        log.info("*** Resource:  /api/evaluation/products/1/product-and-or-image - Status: " +  result.getStatusCode());
	}
	
	@Test	
    public void testPostOneProduct() {
		
		String request = "{\"name\":\"NEW PROD\",\"description\":\"NEW PROD\",\"parentProductId\":\"6\",\"images\":[{\"type\":\"PNG\"},{\"type\":\"JPG\"}]}";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(request, httpHeaders);
		
        ResponseEntity<String> result = testRestTemplate.postForEntity("/api/evaluation/products",entity,  String.class );
        String idCreated = "{\"id\":7}";
        
        assertEquals(result.getBody(), idCreated);
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        
        log.info("*** Resource:  /api/evaluation/products - Status: " +  result.getStatusCode());
	}
}
