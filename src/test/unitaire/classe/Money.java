package test.unitaire.classe;

public class Money {
	private int fAmount;
	private String fCurrency;
	
	public Money(int amount, String currency) {
		fAmount = amount;
		fCurrency = currency;
	}
	
	public int amount() {
		return fAmount;
	}
	
	public String currency() {
		return fCurrency;
	}
	
	public Money add(Money m) {
		return new Money(amount() + m.amount(), currency());
	}
	
	@Override
	public boolean equals(Object compare) {
		if (this == compare) return true;
		if (compare == null || getClass() != compare.getClass()) return false;
		Money money = (Money) compare;
		return fAmount == money.fAmount && fCurrency.equals(money.fCurrency);
		
	}
}
