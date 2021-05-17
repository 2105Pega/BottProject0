package com.revature.beans;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	public static int MenuStart(Scanner sc) {

		// prompt user for application or login
		int menuSelection = 0;
		System.out.println("Hello, enter 1 to login, 2 for accout application or 3 to exit.");
		menuSelection = sc.nextInt();

		// error checking user input
		while (menuSelection < 1 || menuSelection > 3) {
			System.out.println("Please enter 1 to login, 2 for accout application or 3 to exit.");
			menuSelection = sc.nextInt();

		}

		return menuSelection;

	}

	// login process returns true when valid login
	public static String MenuLogin(Scanner sc, ArrayList<Account> accounts, ArrayList<Customer> customers) {

		String password = "";

		int i = 1;
		boolean validCredential = false;

		System.out.println("Enter username: ");
		sc.nextLine();
		String username = sc.nextLine();

		while (i == 1 || username.equals("admin")) {

			if (username.equals("admin")) {
				adminLogin(sc, accounts, customers);
			}

			if (validUsername(username, customers)) {
				validCredential = validUsername(username, customers);
				System.out.println("Welcome back: " + username);

			} else {
				System.out.println("Enter username: ");

				sc.nextLine();
				username = sc.nextLine();

			}

			if (validCredential) {

				String expectedPassword = "";

				for (int j = 0; j < customers.size(); j++) {

					if (customers.get(j).getUsername().equals(username)) {

						expectedPassword = customers.get(j).getPassword();
					}

				}

				System.out.println("Enter password: ");
				password = sc.nextLine();

				validCredential = password.equals(expectedPassword);

				if (validCredential) {
					System.out.println("Login Success!");
					i = 0;
				} else {
					username = "invalid";
				}

			}
		}

		return username;
	}

	public static void MenuApplyForAcct(Scanner sc, ArrayList<Customer> customer) {

		// joint acc or solo
		System.out.println("Applying for a joint account (y/n)?");

		// applying for individuals account
		if (sc.nextLine().toLowerCase().equals("n")) {
			// applying for individual account
			individualAcctApply(sc, customer);

		} else {
			// applying for joint account
		}

	}

	public static void individualAcctApply(Scanner sc, ArrayList<Customer> customer) {

		String username = "";
		String password = "";
		String firstName = "";
		String lastName = "";

		System.out.println("Desired username: ");
		sc.nextLine();
		System.out.println("Desired Password: ");
		sc.nextLine();
		System.out.println("First Name: ");
		sc.nextLine();
		System.out.println("Last Name");
		sc.nextLine();

		Customer acctApplication = new Customer(username, password, firstName, lastName, 0);
		customer.add(acctApplication);

	}

	public static void jointAcctApply(Scanner sc, ArrayList<Customer> customer) {

		String username = "";
		String password = "";
		String firstName = "";
		String lastName = "";

		System.out.println("Desired username: ");
		sc.nextLine();
		System.out.println("Desired Password: ");
		sc.nextLine();
		System.out.println("First Names: ");
		sc.nextLine();
		System.out.println("Last Name");
		sc.nextLine();

		Customer acctApplication = new Customer(username, password, firstName, lastName, 0);
		customer.add(acctApplication);

	}

	public static boolean validUsername(String username, ArrayList<Customer> customer) {

		boolean usernameFound = false;

		// looping through customer checking usernames
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getUsername().equals(username)) {
				usernameFound = true;
				i = customer.size();
			}

		}

		return usernameFound;

	}

	public static void listAccounts(String username, Map<String, Integer> hm, ArrayList<Account> accounts) {

		int accountID = hm.get(username);
		for (int i = 0; i < accounts.size(); i++) {

			int j = accounts.get(i).getAccID();

			if (j == accountID) {
				System.out.println("Balance for account ID " + j + " is: " + accounts.get(i).getBalance());
			}

		}

	}

	public static void accountFeatures(Scanner sc, ArrayList<Account> account) {
		System.out.println("Enter 1 to deposit, 2 for withdraw and 3 to transfer");
		int userSelection = sc.nextInt();

		if (userSelection == 1) {
			preDeposit(sc, account);
		} else if (userSelection == 2) {
			preWithdraw(sc, account);
		} else if (userSelection == 3) {
			preTransfer(sc, account);
		}

	}

	public static void preDeposit(Scanner sc, ArrayList<Account> account) {

		System.out.println("Enter amount to deposit: ");
		double deposit = sc.nextDouble();

		while (deposit < 0) {
			System.out.println("Please enter positive amount: ");
			deposit = sc.nextDouble();

		}

		System.out.println("Enter Account ID to receive deposit");
		int acctID = sc.nextInt();

		deposit(acctID, deposit, account);

	}

	public static void deposit(int acctID, double deposit, ArrayList<Account> account) {

		double currentBalance = 0;
		for (int i = 0; i < account.size(); i++) {
			if (account.get(i).getAccID() == acctID) {
				currentBalance = account.get(i).getBalance();
				currentBalance = currentBalance + deposit;
				account.get(i).setBalance(currentBalance);
				System.out.println(
						"Deposit of: $" + deposit + " received, Account balance: $" + account.get(i).getBalance());

			}
		}

	}

	public static void preWithdraw(Scanner sc, ArrayList<Account> account) {

		System.out.println("Enter amount to withdraw: ");
		double withdraw = sc.nextDouble();

		while (withdraw < 0) {
			System.out.println("Please enter positive amount: ");
			withdraw = sc.nextDouble();

		}

		System.out.println("Enter Account ID to withdraw from");
		int acctID = sc.nextInt();

		withdraw(acctID, withdraw, account);
	}

	public static void withdraw(int acctID, double withdraw, ArrayList<Account> account) {

		double currentBalance = 0;

		for (int i = 0; i < account.size(); i++) {
			if (account.get(i).getAccID() == acctID) {
				currentBalance = account.get(i).getBalance();
				currentBalance = currentBalance - withdraw;
				account.get(i).setBalance(currentBalance);
				System.out.println(
						"Withdrawl of: $" + withdraw + " completed, Account Number: " + account.get(i).getAccID() + " balance: $" + account.get(i).getBalance());
			}
		}
	}

	public static void preTransfer(Scanner sc, ArrayList<Account> account) {

		System.out.println("Enter Account ID to transfer from: ");
		int giverID = sc.nextInt();
		System.out.println("Enter Account ID to transfer to: ");
		int receiverID = sc.nextInt();
		System.out.println("Enter amount to transfer: ");
		double amount = sc.nextDouble();

		while (amount < 0) {
			System.out.println("Please enter positive amount: ");
			amount = sc.nextDouble();

		}
		transfer(giverID, receiverID, amount, account);
	}

	public static void transfer(int giverID, int receiverID, double amount, ArrayList<Account> account) {

		withdraw(giverID, amount, account);
		deposit(receiverID, amount, account);
		System.out.println(
				"Transferred $" + amount + " from account ID " + giverID + ", Balance now: $" + account.get(receiverID).getBalance()
						+ " to account ID " + receiverID + " Balance now: $" + account.get(giverID).getBalance());

	}

	public static void adminLogin(Scanner sc, ArrayList<Account> accounts, ArrayList<Customer> customers) {
		System.out.println("Logged in as an Administrator: ");
		System.out.println(
				"1. deposit, 2. withdraw, 3. transfer, 4. view pending accounts, 5 cancel accounts, 6 view an accounts, 7 to quit ");
		int selection = sc.nextInt();

		if (selection == 1) {
			preDeposit(sc, accounts);
		} else if (selection == 2) {
			preWithdraw(sc, accounts);
		} else if (selection == 3) {
			preTransfer(sc, accounts);
		} else if (selection == 4) {

			for (int i = 0; i < customers.size(); i++) {
				if (customers.get(i).getAcctID() == 0) {
					System.out.println(customers.get(i).toString());
				}
			}

			System.out.println("Approve pending customer (y/n)? ");
			if (sc.nextLine().toLowerCase().equals("y")) {
				approveCustomer(sc, customers);

			}

		} else if (selection == 5) {
			cancelAccount(sc, accounts, customers);

		} else if (selection == 6) {
			viewAccounts(accounts);
		} else if (selection == 7) {
			// exit cond for potential loop
		}

	}

	public static void viewAccounts(ArrayList<Account> accounts) {
		for (int i = 0; i < accounts.size(); i++) {
			System.out.println(accounts.get(i).toString());

		}
	}

	public static void cancelAccount(Scanner sc, ArrayList<Account> accounts, ArrayList<Customer> customers) {
		System.out.println("Account ID to be removed: ");
		int acct = sc.nextInt();

		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccID() == acct) {
				accounts.remove(i);
			}
		}

		for (int i = 0; i < accounts.size(); i++) {
			if (customers.get(i).getAcctID() == acct) {
				accounts.remove(i);
			}
		}

	}

	public static void approveCustomer(Scanner sc, ArrayList<Customer> customers) {
		System.out.println("Enter username to approve: ");
		String username = sc.nextLine();
		System.out.println("Enter account number to approve: ");
		int acctID = sc.nextInt();

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getUsername().equals(username)) {
				customers.get(i).setAcctID(acctID);

			}
		}
	}
}
