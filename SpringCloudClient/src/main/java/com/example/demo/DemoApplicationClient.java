package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaServer
@SpringBootApplication
public class DemoApplicationClient {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplicationClient.class, args);
	}
	@Value("${server.port}")
	String port;
	@RequestMapping("/hi")
	public String home(@RequestParam String name) {
		return "hi "+name+",i am from port:" +port+"this is my first test eureka";
	}
	@RequestMapping("/shishi")
	public Object getNumber(){
		String s = "";
		for (int i=1;i<=9;i++){
			for (int j = 1;j<=i;j++){
				s+=i+"*"+j+"="+(i*j)+"\t";
				System.out.print(i+"*"+j+"="+(i*j)+"\t");
			}
			System.out.print("\n");
		}
		return s;
	}
}
