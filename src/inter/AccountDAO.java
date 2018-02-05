package inter;

import java.sql.Connection;
import java.util.ArrayList;

import AccountFlow.Account;

public interface AccountDAO {
	public int add(Account a);
	public ArrayList<Account> List(int number);
}
