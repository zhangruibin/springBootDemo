package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstSpringBootApplicationTests {
@LocalServerPort
public  int port;

public URL base;

@Autowired
private TestRestTemplate testRestTemplate;
	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}
	/*@Test
	public void contextLoads() {
	}*/
	@Test
	public void getHello() throws Exception {
		ResponseEntity<String> response = testRestTemplate.getForEntity(base.toString(),
				String.class);
		//assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
	}
}
