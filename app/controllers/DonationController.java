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

}