package test.unitaire.classe;

public class Money implements IMoney {
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
	
	@Override
	public boolean equals(Object compare) {
		if (this == compare) return true;
		if (compare == null || getClass() != compare.getClass()) return false;
		Money money = (Money) compare;
		return fAmount == money.fAmount && fCurrency.equals(money.fCurrency);
		
	}

	@Override
	public IMoney add(IMoney m) {
		// TODO Auto-generated method stub
		return m.addMoney(this);
	}

	@Override
	public IMoney addMoney(Money m) {
		// TODO Auto-generated method stub
		if (m.currency().equals(currency())) {
			return new Money(amount() + m.amount(), currency());
		} else {
			return new MoneyBag(this, m);
		}
	}

	@Override
	public IMoney addMoneyBag(MoneyBag m) {
		// TODO Auto-generated method stub
		MoneyBag moneyBag = new MoneyBag(this);
		return moneyBag.add(m);
	}
}
