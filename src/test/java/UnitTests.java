import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.revature.beans.Account;
import com.revature.beans.Menu;

class UnitTests {

	Account testAcct1 = new Account(35, 1500.35, "test1", "Test1");
	Account testAcct2 = new Account(36, .20, "test2", "Test2");
	Account testAcct3 = new Account(1500, 100, "test3", "Test3");
	Account testAcct4 = new Account(34, 1500.35, "test4", "Test4");
	Account testAcct5 = new Account(20, 25, "test5", "Test5");
	
	
	ArrayList<Account> accountsLoaded = new ArrayList<Account>();
	
	
	@Test
	void testWithdraw() {
		accountsLoaded.add(testAcct1);
		Menu.withdraw(35, 100.10, accountsLoaded);	
		assertEquals(1400.25, testAcct1.getBalance());

	}
	
	@Test
	void testDeposit() {
		accountsLoaded.add(testAcct2);
		Menu.deposit(35, 150.25, accountsLoaded);	
		assertEquals(150.45, testAcct1.getBalance());

	}

}
