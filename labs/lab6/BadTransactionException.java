

public class BadTransactionException extends Exception {
	public int invalidAmount;  // The invalid amount.
	public BadTransactionException(int am) {
		 super("Invalid amoune: " + am);
		 invalidAmount=am;
	}
}
