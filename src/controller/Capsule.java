package controller;

import java.io.Serializable;
import java.util.HashMap;

import model.AuctionCalendar;
import model.User;

public class Capsule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3002383126736586664L;
	public HashMap<String, User> users;
	
	public AuctionCalendar calendar;
	
	public Capsule(HashMap<String, User> theUser, AuctionCalendar theCalendar) {
		users = theUser;
		calendar = theCalendar;
	}
	
}
