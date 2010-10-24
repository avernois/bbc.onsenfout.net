package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	
	@Required
	public String login;
	
	@Required
	public String password;
	
	public String nickname;
	public boolean isAdmin;
	
	public User(String login, String password, String nickname) {
		this.login = login;
		this.password = password;
		if (nickname == null) {
			this.nickname = this.login;
		} else {
			this.nickname = nickname;
		}
	}
	
    public static User connect(String login, String password) {
        return find("byLoginAndPassword", login, password).first();
    }
    
    public String toString() {
    	return login;
    }
    
    
}
