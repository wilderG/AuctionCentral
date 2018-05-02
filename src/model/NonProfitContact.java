/**
 * 
 */
package model;

/**
 * Class represents a contact person for a non profit organization.
 * @author Jim Rosales
 */
public class NonProfitContact extends User {
	AuctionDate myDate = new AuctionDate();


	/**
	 * {@inheritDoc}
	 */
	public NonProfitContact(String theUsername, String theDisplayName) {
		super(theUsername, theDisplayName);
	}
	
	/**
	 * cheking if the priorExist or not.
	 * @return
	 */
	public boolean isPriorExist() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public AuctionDate getPriorDate() {
		return myDate;
	}
	

}
