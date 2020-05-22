package kjv.cloud.api.domain;

public class Verse {
	private Chapter inChapter;
	private int number;
	private String text;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Chapter getInChapter() {
		return inChapter;
	}
	public void setInChapter(Chapter inChapter) {
		this.inChapter = inChapter;
	}

}
