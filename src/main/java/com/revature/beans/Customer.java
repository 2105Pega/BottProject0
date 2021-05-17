package com.revature.beans;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {

	// for one persons login
	private String username = "";
	private String password = "";
	private String firstName = "";
	private String lastName = "";
	private int acctID;

	public Customer(String username, String password, String firstName, String lastName, int acctID) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.acctID = acctID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAcctID() {
		return acctID;
	}

	public void setAcctID(int acctID) {
		this.acctID = acctID;
	}

	public String toStringPassword() {
		return password;
	}

	public String toStringUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", acctID=" + acctID + "]";
	}
	
}
