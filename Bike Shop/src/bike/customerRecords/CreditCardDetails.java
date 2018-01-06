package bike.customerRecords;

import java.io.Serializable;
import java.util.Calendar;

public class CreditCardDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cardNumber;
	private int expirationMonth;
	private int expirationYear;
	
	public CreditCardDetails(String cardNumber, int month, int year) {
		if (true) {
			this.cardNumber = cardNumber;
		}
		expirationMonth = month;
		expirationYear = year;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public String getHiddenCardNumber() {
		return "xxxxxxxx" + cardNumber.substring(8, 12);
	}
	
	public int getExpirationMonth() {
		return expirationMonth;
	}
	
	public int getExpirationYear() {
		return expirationYear;
	}
	
	public static boolean isValidNumber(String cardNumber) {
		return cardNumber.matches("^4[0-9]{11}");
	}

	public boolean isExpired() {
		Calendar cal = Calendar.getInstance();
		int currentMonth = cal.get(Calendar.MONTH);
		int currentYear = cal.get(Calendar.YEAR);
		return currentYear == expirationYear && currentMonth >= expirationMonth;
	}
}