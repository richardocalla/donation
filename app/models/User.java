package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.OneToMany;

import play.db.jpa.Model;
import play.db.jpa.Blob;

@Entity
public class User extends Model {
	public String firstName;
	public String lastName;
	public boolean usCitizen;
	
	@OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
	List<Donation> donations = new ArrayList<Donation>();
	
	public String email;
	public String password;

	public User(String firstName, String lastName, boolean usaCitizen, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public static User findByEmail(String email) {
		return find("email", email).first();
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

}