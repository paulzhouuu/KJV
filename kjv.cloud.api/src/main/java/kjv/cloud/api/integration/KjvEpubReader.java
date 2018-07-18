package kjv.cloud.api.integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.util.ResourceUtils;

import nl.siegmann.epublib.domain.*;
import nl.siegmann.epublib.epub.*;
public class KjvEpubReader {
    public Book openBook(String bookName) {
    	EpubReader epubReader = new EpubReader();
    	try {
    		File kjvEpub = ResourceUtils.getFile("classpath:kjv.epub");
    		FileInputStream fis = new FileInputStream(kjvEpub);
			Book book = epubReader.readEpub(fis);
		    return book;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	return null;
    }
}
