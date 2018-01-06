package bike.customerRecords;

import java.io.Serializable;

public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String streetAddress;
	private String city;
	private State state;
	private Country country;
	private String zipCode;
	
	public Address(String streetAddress, String city, State state,
			Country country, String zipCode) {
		setStreetAddress(streetAddress);
		setCity(city);
		this.state = state;
		this.country = country;
		setZipCode(zipCode);
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public String getCity() {
		return city;
	}

	public State getState() {
		return state;
	}

	public Country getCountry() {
		return country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public final void setStreetAddress(String streetAddress) {
		if (isValidStreet(streetAddress)) {
			this.streetAddress = streetAddress;
		}
		else throw new IllegalArgumentException("street");
	}

	public final void setCity(String city) {
		if (city.matches("[a-zA-Z]+")) {
			this.city = city;
		}
		else throw new IllegalArgumentException("city");
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public final void setZipCode(String zipCode) {
		if (zipCode.matches("[0-9]{5}")) {
			this.zipCode = zipCode;
		}
		else throw new IllegalArgumentException("zip code");
	}
	
	public boolean isValidStreet(String street) {
		return street.matches("([0-9]+)*\\s[a-zA-Z]+(\\s[a-zA-Z]+)*");
	}
}