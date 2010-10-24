package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Phrase extends Model {
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
