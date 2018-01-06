package bike.customerRecords;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

import bike.io.RecordsIO;

public abstract class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private int age;
	private String phone;
	private Email email;
	
	public Person(String firstName, String lastName, int age, String phone, Email email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phone = phone;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	public Email getEmail() {
		return email;
	}

	public void setFirstName(String firstName) {
		if (isValidName(firstName)) {
			this.firstName = firstName;
		}
		else throw new IllegalArgumentException("first name");
	}

	public void setLastName(String lastName) {
		if (isValidName(lastName)) {
			this.lastName = lastName;
		}
		else throw new IllegalArgumentException("last name");
	}

	public void setAge(int age) {
		if (isValidAge(age)) {
			this.age = age;
		}
		else throw new IllegalArgumentException("age");
	}

	public void setPhone(String phone) {
		if (isValidPhone(phone)) {
			this.phone = phone;
		}
		else throw new IllegalArgumentException("phone");
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	
	public File savePersonState(String directory) {
		return RecordsIO.serialize(this, directory + getEmail().getUsername());
	}
	
	public static Person loadPersonState(String directory, String emailUsername) throws FileNotFoundException {
		return (Person) RecordsIO.deserialize("Customer Records/" + emailUsername);
	}
	
	public static boolean isValidName(String name) {
		return name.matches("[a-zA-Z]+");
	}
	
	public static boolean isValidFullName(String name) {
		return name.matches("([a-zA-Z]+)*\\s([a-zA-Z]+)");
	}
	
	public static boolean isValidAge(int age) {
		return age > 6 && age < 110;
	}
	
	public static boolean isValidPhone(String phone) {
		return phone.matches("[0-9]{3}\\-[0-9]{3}\\-[0-9]{4}");
	}
}
