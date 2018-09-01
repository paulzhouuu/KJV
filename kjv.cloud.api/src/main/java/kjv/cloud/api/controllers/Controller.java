//all the glory to God and the Lamb on the throne
package kjv.cloud.api.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import kjv.cloud.api.domain.WordUtils;
import kjv.cloud.api.integration.KjvEpubReader;

@RestController
public class Controller {
	@RequestMapping(value = "books/{bookName}/chapters/{chapterNumber}/verses/{verseNumber}", method = RequestMethod.GET)
    public String readChapterVerse(@PathVariable("bookName") String bookName,
    								@PathVariable("chapterNumber") int chapterNumber,
    								@PathVariable("verseNumber") int verseNumber) {
	    KjvEpubReader reader = new KjvEpubReader();
	    reader.openBook("kjv.epub");
        String all = reader.readVerse(WordUtils.findBookName(bookName), chapterNumber, verseNumber);
        return all;
    }
	@RequestMapping(value = "books/{bookName}/chapters/{chapterNumber}", method = RequestMethod.GET)
    public String readChapter(@PathVariable("bookName") String bookName,
    								@PathVariable("chapterNumber") int chapterNumber) {
	    KjvEpubReader reader = new KjvEpubReader();
	    reader.openBook("kjv.epub");
        String all = reader.readChapter(WordUtils.findBookName(bookName), chapterNumber);
        return all;
    }
	@RequestMapping(value = "books/{bookName}", method = RequestMethod.GET)
    public String readBook(@PathVariable("bookName") String bookName) {
	    KjvEpubReader reader = new KjvEpubReader();
	    reader.openBook("kjv.epub");
        String all = reader.readBook(WordUtils.findBookName(bookName));
        return all;
    }
	@RequestMapping("/")
	public String home() {
        return "the never failing KJV";
    }
}
