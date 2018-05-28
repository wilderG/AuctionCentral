package gui;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatter {

	public static String formatDate(final LocalDate theDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, u");
		return theDate.format(formatter);
	}
	
	public static String formatDateMonthYear(final LocalDate theDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM u");
		return theDate.format(formatter);
	}
	
	/**
	 * Returns a string formatted currency value. If the amount is less than
	 * $1.00 then cents are displayed.
	 * @param theAmount
	 * @return
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
