package com.revature.driver;
import org.apache.log4j.BasicConfigurator;  
import org.apache.log4j.LogManager;  
import org.apache.log4j.Logger; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.Customer;
import com.revature.beans.Menu;

public class Driver {

	public static void main(String[] args) {

		// read in files?

		// creating scanner
		Scanner sc = new Scanner(System.in);

		/*
		 * try { FileOutputStream fout = new FileOutputStream("data.txt.");
		 * FileInputStream fin = new FileInputStream("data.txt."); } catch
		 * (FileNotFoundException e) { e.printStackTrace(); }
		 * 
		 */

		Account practice1 = new Account(1, 100.20, "James", "Bott");
		Account practice2 = new Account(2, 250.32, "Karen", "Jones");
		Account practice3 = new Account(3, 15.30, "Mike", "Smith");
		Account practice4 = new Account(4, 10000.15, "Dave", "Hardy");

		Customer prac1 = new Customer("james", "bott", "James", "Bott", 1);
		Customer prac2 = new Customer("karen", "jones", "Karen", "Jones", 2);
		Customer prac3 = new Customer("mike", "smith", "Mike", "Smith", 3);
		Customer prac4 = new Customer("dave", "hardy", "Dave", "Hardy", 4);

		ArrayList<Account> accountsLoaded = new ArrayList<Account>();
		ArrayList<Customer> customersLoaded = new ArrayList<Customer>();

		accountsLoaded.add(practice3);
		accountsLoaded.add(practice2);
		accountsLoaded.add(practice1);

		customersLoaded.add(prac3);
		customersLoaded.add(prac2);
		customersLoaded.add(prac1);

		// make map with username and acc IDs1
		
		// connecting accounts to customers
		Map<String, Integer> hm = new HashMap<String, Integer>();

		hm.put(prac1.getUsername(), practice1.getAccID());
		hm.put(prac2.getUsername(), practice2.getAccID());
		hm.put(prac3.getUsername(), practice3.getAccID());

		// prompt user to login, apply or exit
		int loginSelection = Menu.MenuStart(sc);

		while (loginSelection != 3) {

			// menu login or apply for acct
			if (loginSelection == 1) {

				String username = "";
				username = Menu.MenuLogin(sc, accountsLoaded, customersLoaded);

				Menu.listAccounts(username, hm, accountsLoaded);
				Menu.accountFeatures(sc, accountsLoaded);
				

				// successful login show accounts
				// list accounts

			} else if (loginSelection == 2) {
				Menu.MenuApplyForAcct(sc, customersLoaded);
			}
		
			
			loginSelection = Menu.MenuStart(sc);
			
			if (loginSelection == 3) {
				System.out.println("goodbye");
			}
		}

		// closing tools
		sc.close();
	}

}
