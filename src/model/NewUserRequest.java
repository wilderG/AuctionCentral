package model;

/**
 * Class represents requests for any new users.
 * @author Jim Rosales
 */
public class NewUserRequest {
	
	/**
	 * The username that will be associated with the new user.
	 */
	private String myUsername;
	
	/**
	 * The legal name for the user. ex: John Smith
	 */
	private String myName;
	
	/**
	 * The boolean that will determine whether the new user can place bids.
	 */
	private Boolean myCanBid;
	
	/**
	 * Boolean determines whether the user can sponsor auctions.
	 */
	private Boolean myCanSponsorAuction;
	
	/**
	 * Constructor for requests for a new user.
	 * @param theUsername that will be used for the new user.
	 * @param theName of the new user.
	 * @param theCanBid which will determine whether the new user can bid.
	 * @param theCanSponsorAuction which will determine whether the new user can sponsor auctions.
	 */
	public NewUserRequest(String theUsername, String theName, Boolean theCanBid, Boolean theCanSponsorAuction) {
		myUsername = theUsername;
		myName = theName;
		myCanBid = theCanBid;
		myCanSponsorAuction = theCanSponsorAuction;
	}
	
	/**
	 * Getter for the username for the newly requested user.
	 * @return The username for the requested user.
	 */
	public String getMyUsername() {
		return myUsername;
	}
	
	/**
	 * Getter for the name of the newly requested user.
	 * @return The name of the requested user.
	 */
	public String getMyName() {
		return myName;
	}
	
	/**
	 * Getter for the users can bid which determines whether they can place bids.
	 * @return True if the user can place bids, false otherwise.
	 */
	public Boolean getMyCanBid() {
		return myCanBid;
	}
	
	/**
	 * Getter for the users can sponsor which determines whether they can sponsor auctions.
	 * @return True if the user can sponsor auctions, false otherwise.
	 */
	public Boolean getMyCanSponsorAuction() {
		return myCanSponsorAuction;
	}
	
}
