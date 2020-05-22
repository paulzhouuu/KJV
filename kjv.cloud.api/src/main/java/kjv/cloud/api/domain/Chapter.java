package kjv.cloud.api.domain;

import java.util.List;

public class Chapter {
	private Book inBook;
	private int number;
	private List<Verse> verses;

	public List<Verse> getVerses() {
		return verses;
	}

	public void setVerses(List<Verse> verses) {
		this.verses = verses;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Book getInBook() {
		return inBook;
	}

	public void setInBook(Book inBook) {
		this.inBook = inBook;
	}


}
