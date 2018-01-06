package bike.customerRecords;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email implements Serializable {

	private static final long serialVersionUID = 1L;
	private String emailString;
	private String username;
	private String domain;
	
	public Email(String emailString) {
		if (isValidEmail(emailString)) {
			this.emailString =  emailString;
			String[] emailArray = emailString.split("@");
			username = emailArray[0];
			domain = emailArray[1];
		}
		else throw new IllegalEmailException(emailString);
	}
	
	public String getEmailAddress() {
		return emailString;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getDomain() {
		return domain;
	}
	
	/**
	 * Checks if a given email address is valid. To pass, an address must 
	 * contain only one '@' character and at least one '.' character.
	 * Also, the '.' character must follow the '@' character. 
	 * 
	 * @param emailString the email address to be checked.
	 * @return true if valid, false if not.
	 */
	public static boolean isValidEmail(String emailString) {
		String emailPattern = "([A-Za-z0-9]+)*@[A-Za-z0-9]+"
				+ "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z0-9]{2,3})";
		Pattern p = Pattern.compile(emailPattern);
		Matcher m = p.matcher(emailString);
		return m.matches();
	}
}
