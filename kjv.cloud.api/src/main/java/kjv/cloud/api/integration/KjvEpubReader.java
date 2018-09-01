package kjv.cloud.api.integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import nl.siegmann.epublib.domain.*;
import nl.siegmann.epublib.epub.*;

public class KjvEpubReader {
	private static Book bookOpened = null;

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	public void openBook(String bookFileName) {
		final String BOOK_PATH_TEMPLATE = "classpath:%s";
		EpubReader epubReader = new EpubReader();
		try {
			org.springframework.core.io.Resource kjvEpub = resourceLoader.getResource(String.format(BOOK_PATH_TEMPLATE, bookFileName));
			
			bookOpened = epubReader.readEpub(kjvEpub.getInputStream());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String readInputStreamAsPlainText(InputStream is) {
		String plainText = "";

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			plainText += sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plainText;

	}
	private TOCReference findBookTocResource(String bookName) {
			List<TOCReference> refs = bookOpened.getTableOfContents().getTocReferences();

			return refs.stream().filter(o -> {
				return o.getTitle().equals(bookName);
			}).collect(Collectors.toList()).get(0);
			
	}
	
	private TOCReference findChapterTocResource(String bookName, int chapterNumber) {
		TOCReference foundBookTocReference = findBookTocResource(bookName);
		return foundBookTocReference.getChildren().stream().filter(o -> {
			return o.getTitle().equals(String.format("Chapter %s", chapterNumber));
		}).collect(Collectors.toList()).get(0);
			
	}

	public String readChapter(String bookName, int chapterNumber) {
		String htmltext = "";
		TOCReference foundTocReference = findChapterTocResource(bookName, chapterNumber);

	
		if (foundTocReference != null) {
			try {
				htmltext += readInputStreamAsPlainText(foundTocReference.getResource().getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return htmltext;
	}
	
	public String readVerse(String bookName, int chapterNumber, int verseNumber) {
		String regExpression = String.format("<p>%d\\..*</p>", verseNumber);
		String htmltext = this.readChapter(bookName, chapterNumber);
		
		Pattern p = Pattern.compile(regExpression);
		Matcher match = p.matcher(htmltext);
		
		while (match.find()) {
             return (match.group());
		}
		return "";
	}
	public String readBook(String bookName) {
	    String htmltext = "";
		TOCReference foundBookTocReference = findBookTocResource(bookName);

	
		if (foundBookTocReference != null) {
				for (TOCReference curChapterToc:foundBookTocReference.getChildren()) {
					try {
						htmltext += readInputStreamAsPlainText(curChapterToc.getResource().getInputStream());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
		}
		return htmltext;
	}

	public String readAll() {
		String htmltext = "";
		List<TOCReference> refs = bookOpened.getTableOfContents().getTocReferences();
		for (int i = 0; i < 10; i++) {
			InputStream is;
			try {
				SpineReference currentSpine = bookOpened.getSpine().getSpineReferences().get(i);
				TOCReference ref = refs.get(i);
				Resource currentResource = ref.getResource();

				is = currentResource.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				StringBuilder sb = new StringBuilder();
				String line = null;
				try {
					while ((line = reader.readLine()) != null) {
						sb.append(line + "\n");
					}

					htmltext += sb.toString();
					htmltext = htmltext.replaceAll("\\<.*?\\>", "");
					htmltext += String.format("----------%s--------------------------------------\n", ref.getTitle());

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return htmltext;
	}
}
