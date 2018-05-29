package gui;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Static class contains formatting methods for date and currency values.
 * @author Jared Malone
 * @version 1.0 5/28/2018
 */
public final class Formatter {
	private Formatter() {}
	
	/**
	 * Formats a LocalDate to the following month/day/year format:
	 *   "January 12, 2000" 
	 * @param theDate must not be null
	 * @return a formatted String
	 */
	public static String formatDate(final LocalDate theDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, u");
		return theDate.format(formatter);
	}
	
	/**
	 * Formats a LocalDate to the following month/year format:
	 *   "January 2000"
	 * @param theDate must not be null
	 * @return a formatted String
	 */
	public static String formatDateMonthYear(final LocalDate theDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM u");
		return theDate.format(formatter);
	}
	
	/**
	 * Returns a string formatted currency value. If the cents are not zero
	 * then the cents are displayed, otherwise the cents are truncated.
	 * @param theAmount must not be null
	 * @return a formatted String
	 */
	public static String getMoneyFormat(final BigDecimal theAmount) {
		String result;
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
		
		if (theAmount.stripTrailingZeros().scale() <= 0) {
			formatter.setMaximumFractionDigits(0);
			result = formatter.format(theAmount);
		} else {
			result = NumberFormat.getCurrencyInstance(Locale.getDefault()).
					format(theAmount);
		}
		return result; 
	}
	
}
