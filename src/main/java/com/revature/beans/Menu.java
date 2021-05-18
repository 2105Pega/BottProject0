package com.revature.beans;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.driver.Driver;

public class Menu {
	// starting logger
	final static Logger logger = LogManager.getLogger(Driver.class);

	public static int MenuStart(Scanner sc) {

		// prompt user for application or login
		int menuSelection = 0;
		System.out.println("Hello, enter 1 to login, 2 for account application or 3 to exit.");
		menuSelection = sc.nextInt();

		// error checking user input
		while (menuSelection < 1 || menuSelection > 3) {
			System.out.println("Please enter 1 to login, 2 for account application or 3 to exit.");
			menuSelection = sc.nextInt();

		}

		return menuSelection;

	}

	// login process returns true when valid login
	public static String MenuLogin(Scanner sc, ArrayList<Account> accounts, ArrayList<Customer> customers) {

		String password = "";

		boolean validCredential = false;

		System.out.println("Enter username: ");
		sc.nextLine();
		String username = sc.nextLine();

		if (username.equals("admin")) {
			adminLogin(sc, accounts, customers);
			username = "";
		}

		validCredential = validUsername(username, customers);

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
			} else {
				username = "invalid";
			}

		}

		return username;
	}

	public static void MenuApplyForAcct(Scanner sc, ArrayList<Customer> customer) {

		// joint acc or solo
		System.out.println("Applying for a joint account (y/n)?");
		sc.nextLine();
		String jointAcctSelection = sc.nextLine();

		// applying for individuals account
		if (jointAcctSelection.toLowerCase().equals("n")) {
			// applying for individual account
			individualAcctApply(sc, customer);

		} else if (jointAcctSelection.toLowerCase().equals("y")) {
			jointAcctApply(sc, customer);
		}

	}

	public static void individualAcctApply(Scanner sc, ArrayList<Customer> customer) {

		String username = "";
		String password = "";
		String firstName = "";
		String lastName = "";

		System.out.println("Desired username: ");
		username = sc.nextLine();
		System.out.println("Desired Password: ");
		password = sc.nextLine();
		System.out.println("First Name: ");
		firstName = sc.nextLine();
		System.out.println("Last Name");
		lastName = sc.nextLine();

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
		System.out.println("Last Names");
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

	public static void accountFeatures(Scanner sc, ArrayList<Account> account, int accountID) {
		System.out.println("Enter 1 to deposit, 2 for withdraw, 3 to transfer or 4 to exit ");
		int userSelection = sc.nextInt();

		while (userSelection != 4) {
			if (userSelection == 1) {
				preDeposit(sc, account, accountID);
			} else if (userSelection == 2) {
				preWithdraw(sc, account, accountID);
			} else if (userSelection == 3) {
				preTransfer(sc, account, accountID);
			} else {
				System.out.println("Enter 1 to deposit, 2 for withdraw, 3 to transfer or 4 to exit ");
				userSelection = sc.nextInt();
			}
			System.out.println("Enter 1 to deposit, 2 for withdraw, 3 to transfer or 4 to exit ");
			userSelection = sc.nextInt();
		}
	}

	public static void preDeposit(Scanner sc, ArrayList<Account> account, int accountID) {

		System.out.println("Enter amount to deposit: ");
		double deposit = sc.nextDouble();

		while (deposit <= 0) {
			System.out.println("Please enter positive amount: ");
			deposit = sc.nextDouble();

		}
		deposit(accountID, deposit, account);

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

	public static void preWithdraw(Scanner sc, ArrayList<Account> account, int accountID) {

		System.out.println("Enter amount to withdraw: ");
		double withdraw = sc.nextDouble();

		while (withdraw <= 0) {
			System.out.println("Please enter positive amount: ");
			withdraw = sc.nextDouble();

		}

		withdraw(accountID, withdraw, account);
	}

	public static void withdraw(int acctID, double withdraw, ArrayList<Account> account) {

		double currentBalance = 0;

		for (int i = 0; i < account.size(); i++) {
			if (account.get(i).getAccID() == acctID) {
				currentBalance = account.get(i).getBalance();
				currentBalance = currentBalance - withdraw;
				account.get(i).setBalance(currentBalance);
				System.out.println("Withdrawl of: $" + withdraw + " completed, Account Number: "
						+ account.get(i).getAccID() + " balance: $" + account.get(i).getBalance());
			}
		}
	}

	public static void preTransfer(Scanner sc, ArrayList<Account> account, int accountID) {

		// implement error checking on valid acct ID's

		System.out.println("Enter Account ID to transfer to: ");
		int receiverID = sc.nextInt();
		System.out.println("Enter amount to transfer: ");
		double amount = sc.nextDouble();

		while (amount < 0) {
			System.out.println("Please enter positive amount: ");
			amount = sc.nextDouble();

		}
		transfer(accountID, receiverID, amount, account);
	}

	public static void transfer(int giverID, int receiverID, double amount, ArrayList<Account> account) {

		withdraw(giverID, amount, account);
		deposit(receiverID, amount, account);
		System.out.println("Transferred $" + amount + " from account ID " + giverID + ", Balance now: $"
				+ account.get(receiverID).getBalance() + " to account ID " + receiverID + " Balance now: $"
				+ account.get(giverID).getBalance());

		logger.trace("Transferred $" + amount + " from account ID " + giverID + ", Balance now: $"
				+ account.get(receiverID).getBalance() + " to account ID " + receiverID + " Balance now: $"
				+ account.get(giverID).getBalance());

	}

	public static void adminLogin(Scanner sc, ArrayList<Account> accounts, ArrayList<Customer> customers) {
		System.out.println("Logged in as an Administrator: ");
		System.out.println(
				"1. deposit, 2. withdraw, 3. transfer, 4. view pending accounts, 5 cancel accounts, 6 view all accounts, 7 to quit ");
		int selection = sc.nextInt();
		int acctID = -1;

		while (selection != 7) {

			if (selection == 1) {
				System.out.println("Enter account ID for deposit: ");
				acctID = sc.nextInt();
				preDeposit(sc, accounts, acctID);
			} else if (selection == 2) {
				System.out.println("Enter account ID for withdrawl: ");
				acctID = sc.nextInt();
				preWithdraw(sc, accounts, acctID);
			} else if (selection == 3) {
				System.out.println("Enter account ID to transfer from: ");
				acctID = sc.nextInt();
				preTransfer(sc, accounts, acctID);
			} else if (selection == 4) {

				int pendingAccounts = 0;
				for (int i = 0; i < customers.size(); i++) {
					if (customers.get(i).getAcctID() == 0) {
						System.out.println(customers.get(i).toString());
						pendingAccounts++;
					}
				}

				if (pendingAccounts == 0) {
					System.out.println("No pending accounts.");

				}
				if (pendingAccounts > 0) {
					System.out.println("Approve pending customer (y/n)? ");
					String approval = "";
					sc.nextLine();
					approval = sc.nextLine();
					if (approval.toLowerCase().equals("y")) {
						approveCustomer(sc, customers, accounts);

					} else if (approval.toLowerCase().equals("n")) {
						cancelAccount(sc, accounts, customers);
					}
				}

			} else if (selection == 5) {
				cancelAccount(sc, accounts, customers);

			} else if (selection == 6) {
				viewAccounts(accounts);
			} else {
				System.out.println(
						"1. deposit, 2. withdraw, 3. transfer, 4. view pending accounts, 5 cancel accounts, 6 view all accounts, 7 to quit ");
				selection = sc.nextInt();

			}
			System.out.println(
					"1. deposit, 2. withdraw, 3. transfer, 4. view pending accounts, 5 cancel accounts, 6 view all accounts, 7 to quit ");
			selection = sc.nextInt();
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

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getAcctID() == acct) {
				customers.remove(i);
			}
		}

	}

	public static void approveCustomer(Scanner sc, ArrayList<Customer> customers, ArrayList<Account> accounts) {
		System.out.println("Enter username to approve: ");
		String username = sc.nextLine();
		System.out.println("Assign an account number: ");
		int acctID = sc.nextInt();

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getUsername().equals(username)) {
				customers.get(i).setAcctID(acctID);
				Account temp = new Account(acctID, 0, customers.get(i).getFirstName(), customers.get(i).getLastName());
				accounts.add(temp);
			}
		}

	}
}
