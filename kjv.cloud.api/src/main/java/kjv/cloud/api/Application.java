package kjv.cloud.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kjv.cloud.api.controllers.Controller;
import kjv.cloud.api.integration.KjvEpubReader;
import nl.siegmann.epublib.domain.Book;

@SpringBootApplication
@ComponentScan(basePackageClasses = Controller.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
