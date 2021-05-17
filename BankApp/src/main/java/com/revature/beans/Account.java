package com.revature.beans;

import java.util.ArrayList;

public class Account {

	private double balance;
	private int accID;
	private String firstName;
	private String lastName;

	public Account(int accID, double balance, String firstName, String lastName) {
		this.accID = accID;
		this.balance = balance;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccID() {
		return accID;
	}

	public void setAccID(int accID) {
		this.accID = accID;
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

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", accID=" + accID + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

}
