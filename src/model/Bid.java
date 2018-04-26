/**
 * 
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class represents a bid that can be placed on an item.
 * @author 
 *
 */
public class Bid implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 7814537937865789379L;

	/**
	 * The value amount for the bid.
	 */
	private BigDecimal theValue;
	
	/**
	 * The bidder who placed the respective bid.
	 */
	private Bidder theBidder;
	
}
