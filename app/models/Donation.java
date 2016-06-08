package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Donation extends Model {
	public long received;
	public String methodDonated;

	@ManyToOne
	public User from;

	public Donation(User from, long recieved, String methodDonated) {
		this.received = recieved;
		this.methodDonated = methodDonated;
		this.from = from;
	}

}