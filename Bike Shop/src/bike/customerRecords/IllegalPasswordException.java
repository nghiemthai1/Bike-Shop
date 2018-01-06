package bike.customerRecords;

public class IllegalPasswordException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	private String password;
	
	public IllegalPasswordException(String password) {
		super("A given password must be greater than 6 characters.");
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
}
