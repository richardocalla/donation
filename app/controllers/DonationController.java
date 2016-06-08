package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class DonationController extends Controller {

	public static void index() {
		User user = Accounts.getCurrentUser();
		if (user == null) {
			Logger.info("Donation class : Unable to getCurrentUser");
			Accounts.login();
		} else {
			String prog = getPercentTargetAchieved();
			String progress = prog + "%";
			Logger.info("Donation controller : percent target achieved " + progress);
			render(user, progress);
		}
	}
    
	/**
     * Queries Donation model for a list of donations to render on progress bar.
     * @return The percentage of donation target achieved to date.
     */
	public static String getPercentTargetAchieved() {
		List<Donation> allDonations = Donation.findAll();
		long total = 0;
		for (Donation donation : allDonations) {
			total += donation.received;
		}
		long target = getDonationTarget();
		long percentachieved = (total * 100 / target);
		String progress = String.valueOf(percentachieved);
		return progress;
	}
    
	/**
	 * Records individual donations with donation method and amount.
	 * @param amountDonated The amount donated
	 * @param methodDonated The method of donation
	 */
	private static void addDonation(User user, long amountDonated, String methodDonated) {
		Donation bal = new Donation(user, amountDonated, methodDonated);
		bal.save();
	}

	/**
	 * Log and save to database amount donated and method of donation.
	 * @param amountDonated
	 *            Dollars donated
	 * @param methodDonated
	 *            Method used to donate (Paypal/Direct)
	 */
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
    
	/**
     * Hard codes an arbitrary donation target amount.
     * @return The target donation amount
     */
	public static long getDonationTarget() {
		return 20000;
	}
	
	public static void renderReport() {
		List<Donation> donations = Donation.findAll();
		render(donations);
	}
	
}