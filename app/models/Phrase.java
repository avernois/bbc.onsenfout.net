package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Phrase extends Model {
	
	@Required
	@MaxSize(1000)
	@Lob
	public String phrase;
	public Date postedAt;
	public String author;
	
	@Required
	@ManyToOne
	public User postBy;
	
	
	public Phrase(String author, User postBy, String phrase ) {
		this.author = author;
		this.postBy = postBy;
		this.phrase = phrase;
		this.postedAt = new Date();
	}
	
	public String toString() {
		return "by " + author + " posted by " + postBy.nickname;
	}
}
