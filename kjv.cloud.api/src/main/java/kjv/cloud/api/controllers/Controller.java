package kjv.cloud.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kjv.cloud.api.integration.KjvEpubReader;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;

@RestController
public class Controller {
	@RequestMapping("/")
    public String home() {
	    KjvEpubReader reader = new KjvEpubReader();
	    reader.openBook("kjv.epub");
	    //String all = reader.readAll();
        String all = reader.readVerse("Genesis", 1, 1);
        return all;
    }
}
