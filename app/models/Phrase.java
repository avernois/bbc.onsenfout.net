package models;

import java.util.Date;

public class Phrase {
	public String phrase;
	public Date postedAt;
	public String author;
	public String postBy;
	
	
	public Phrase(String author, String postBy, String phrase ) {
		this.author = author;
		this.postBy = postBy;
		this.phrase = phrase;
		this.postedAt = new Date();
	}
}
