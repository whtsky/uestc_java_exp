class ReasonableException extends Exception {
	String reason;
	ReasonableException(String s) {
		this.reason = s;
	}
	
	void printWhy() {
		System.out.println("ReasonableException: " + this.reason);
	}
}

public class Exp {
	Exp() throws ReasonableException{
		throw new ReasonableException("unhappy");
	}
	public static void main (String[] args) {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("I reach finally");
		}
		
		try {
			new Exp();
			System.out.println("miaomiaomiao");
		} catch (ReasonableException e) {
			e.printWhy();
		}
	}
}
