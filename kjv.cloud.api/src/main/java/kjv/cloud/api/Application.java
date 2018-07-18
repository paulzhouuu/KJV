package kjv.cloud.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kjv.cloud.api.integration.KjvEpubReader;
import nl.siegmann.epublib.domain.Book;

@SpringBootApplication
@RestController
public class Application {

	@RequestMapping("/")
    public String home() {
	    KjvEpubReader reader = new KjvEpubReader();
	    Book kjvBook = reader.openBook("test");
        return kjvBook.getTitle();
    }
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
