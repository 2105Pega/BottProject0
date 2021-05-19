package com.revature.driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.Customer;
import com.revature.beans.Menu;

public class Driver {

	public static void main(String[] args) throws IOException {

		// testing variables
//		Account practice1 = new Account(1, 100.20, "James", "Bott");
//		Account practice2 = new Account(2, 250.32, "Karen", "Jones");
//		Account practice3 = new Account(3, 15.30, "Mike", "Smith");
//
//		Customer prac1 = new Customer("james", "bott", "James", "Bott", 1);
//		Customer prac2 = new Customer("karen", "jones", "Karen", "Jones", 2);
//		Customer prac3 = new Customer("mike", "smith", "Mike", "Smith", 3);

		ArrayList<Account> accountsLoaded = new ArrayList<Account>();
		ArrayList<Customer> customersLoaded = new ArrayList<Customer>();

		// testing variables
//		accountsLoaded.add(practice3);
//		accountsLoaded.add(practice2);
//		accountsLoaded.add(practice1);
//
//		customersLoaded.add(prac3);
//		customersLoaded.add(prac2);
//		customersLoaded.add(prac1);

		// making scanner and logger

		Scanner sc = new Scanner(System.in);

		String filePath = "accounts.txt";

		// reading in accounts
		try {
			FileInputStream fin = new FileInputStream(filePath);
			ObjectInputStream ois = new ObjectInputStream(fin);

			accountsLoaded = (ArrayList<Account>) ois.readObject();

			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// reading in customers
		filePath = "customers.txt.";
		try {
			FileInputStream fin = new FileInputStream(filePath);
			ObjectInputStream ois = new ObjectInputStream(fin);
			customersLoaded = (ArrayList<Customer>) ois.readObject();

			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// make map with username and acc IDs

		// connecting accounts to customers
		Map<String, Integer> hm = new HashMap<String, Integer>();

		for (int i = 0; i < accountsLoaded.size(); i++) {

			for (int j = 0; j < customersLoaded.size(); j++) {

				if (accountsLoaded.get(i).getAccID() == customersLoaded.get(j).getAcctID()) {
					hm.put(customersLoaded.get(j).getUsername(), accountsLoaded.get(i).getAccID());

				}

			}

		}

		// testing variables
//		hm.put(prac1.getUsername(), practice1.getAccID());
//		hm.put(prac2.getUsername(), practice2.getAccID());
//		hm.put(prac3.getUsername(), practice3.getAccID());

		// prompt user to login, apply or exit
		int loginSelection = 0;

		while (loginSelection != 3) {

			loginSelection = Menu.MenuStart(sc);

			// menu login or apply for acct
			// 1-3 meet if criteria else login again
			if (loginSelection == 1) {

				String username = "";
				// getting account ID
				username = Menu.MenuLogin(sc, accountsLoaded, customersLoaded);
				// getting account ID

				if (!username.isEmpty()) {

					int acctID = hm.get(username);

					Menu.listAccounts(username, hm, accountsLoaded);

					Menu.accountFeatures(sc, accountsLoaded, acctID);

				}

			} else if (loginSelection == 2) {
				Menu.MenuApplyForAcct(sc, customersLoaded);
			} else if (loginSelection == 3) {
				System.out.println("goodbye");
			} else {
				loginSelection = Menu.MenuStart(sc);
			}
		}

		FileOutputStream fout = new FileOutputStream("customers.txt.");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(customersLoaded);
		oos.close();

		FileOutputStream fos = new FileOutputStream("accounts.txt.");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos);
		oos2.writeObject(accountsLoaded);
		oos2.close();

		// closing tools
		sc.close();
	}

}
