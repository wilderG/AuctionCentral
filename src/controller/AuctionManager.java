package controller;

import model.Auction;
import model.Bid;
import model.Item;
import model.NewAuctionRequest;
import model.NewBidRequest;
import model.NewItemRequest;
import model.NewUserRequest;
import model.User;

/**
 * Manages new object requests from the front-end and 
 * @author Jared
 *
 */
public class AuctionManager implements Manager {

	/** Generate Serial Version UID. */
	private static final long serialVersionUID = 4646613498773772086L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(String theUsername) {
		// TODO Auto-generated method stub
		return new User(theUsername, "Test User");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Auction submitNewAuctionRequest(NewAuctionRequest theAuctionRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User submitNewUser(NewUserRequest theUserRequest) {
		// TODO Auto-generated method stub
		return new User(theUserRequest.getMyUsername(), "Test User"); 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item submitNewItem(NewItemRequest theNewItemRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bid submitNewBid(NewBidRequest theNewBidRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
