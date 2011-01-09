package models;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
	
	@Required
	@ManyToOne
	public User postBy;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	public Set<Author> authors;
	
	
	public Integer score;
	
	public Phrase(User postBy, String phrase ) {
		this.postBy = postBy;
		this.phrase = phrase;
		this.postedAt = new Date();
		this.authors = new TreeSet<Author>();
		this.score = Integer.valueOf(0);
	}
	
	
	public Phrase addAuthor(String name) {
		Author author = Author.findOrCreate(name);
		if (!this.authors.contains(author)) {
			this.authors.add(Author.findOrCreate(name));
		}
		
		author.incScore();
		author.save();
		
		return this;
	}
	
	public Phrase removeAllAuthor() {
		this.authors.clear();
		return this;
	}
	
	public void incScore() {
		this.score += 1;
	}
	
	public static List<Phrase> findByAuthor(String name) {
		return Phrase.find("select distinct p from Phrase p join p.authors as a where a.name = ? order by postedAt desc", name).fetch();
	}
	
	public String toString() {
		return " posted by " + postBy.nickname;
	}
}
