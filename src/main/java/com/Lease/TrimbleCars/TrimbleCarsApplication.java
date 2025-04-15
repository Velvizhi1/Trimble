package com.Lease.TrimbleCars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TrimbleCarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrimbleCarsApplication.class, args);
	}

}
