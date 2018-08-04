package com.zhy;

import com.zhy.Tuling.Utils.TulingResponseConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class WeixinCxApplication {

	public static void main(String[] args) {

		SpringApplication.run(WeixinCxApplication.class, args);
	}


}
