package bike.customerRecords;

public class IllegalEmailException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	private String email;

	public IllegalEmailException(String email) {
		super("The given email is invalid: " + email);
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
}
