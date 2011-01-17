package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Author extends Model implements Comparable<Author> {
	
	@Required
	public String name;
	public Integer score;
	
	private Author(String name) {
		this.name = name;
		this.score = Integer.valueOf(0);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
	public static Author findOrCreate(String name) {
		Author author = Author.find("byName", name).first();
		
		if (author == null) {
			author = new Author(name);
		}
		
		return author;
	}
	
	
	public void incScore() {
		System.out.println("Incrémente le score de " + this.name);
		System.out.println("Avant : " + this.score);
		this.score += 1;
		System.out.println("Après : " + this.score);
	}

	public void decScore() {
		System.out.println("Décrémente le score de " + this.name);
		System.out.println("Avant : " + this.score);
		this.score -= 1;
		System.out.println("Après : " + this.score);
	}
	
	@Override
	public int compareTo(Author author) {
		return name.compareTo(author.name);
	}	
}
