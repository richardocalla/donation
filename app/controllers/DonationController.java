package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class DonationController extends Controller {

	public static void index() {
		Logger.info("Landed in DonationController class");
		render();
	}

	private static void addDonation(User user, long amountDonated, String methodDonated) {
		Donation bal = new Donation(user, amountDonated, methodDonated);
		bal.save();
	}

	public static void donate(long amountDonated, String methodDonated) {
		Logger.info("amount donated " + amountDonated + " " + "methodDonated " + methodDonated);

		User user = Accounts.getCurrentUser();
		if (user == null) {
			Logger.info("Donation class : Unable to getCurrentuser");
			Accounts.login();
		} else {
			addDonation(user, amountDonated, methodDonated);
		}
		index();
	}

}