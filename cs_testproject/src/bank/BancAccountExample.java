package bank;

public class BancAccountExample {

	public static void main(String[] args) {
		BankAccount acc = new BankAccount();
		acc.deposit(500);
		acc.withdraw(100);
		System.out.println(acc.getBalance());
	}

}
