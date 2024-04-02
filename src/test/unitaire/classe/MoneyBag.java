package test.unitaire.classe;

import java.util.Objects;
import java.util.Vector;

public class MoneyBag implements IMoney {
	private Vector<Money> fMonies = new Vector<Money>();
	
	
	
	public MoneyBag(Money m) {
		appendMoney(m);
	}
	
	MoneyBag(Money m1, Money m2) {
		appendMoney(m2);
		appendMoney(m1);

	}
	
	MoneyBag(Money m1, MoneyBag m2) {
		appendMoney(m1);
		appendMoney(m2);
	}
	
	MoneyBag(MoneyBag mb) {
		for (Money money : mb.fMonies) {
			appendMoney(money);
		}
	}

	MoneyBag(Money bag[]) {
		for (Money obj : bag) {
			appendMoney(obj);
		}
	}

	public Vector<Money> getMoneyBagVal() {
		return fMonies;
	}
	
	private void appendMoney(Money m) {
		if (fMonies.isEmpty()) {
			fMonies.add(m);
		} else {
			int i = 0;
			while ((i < fMonies.size()) && (!(fMonies.get(i)
					.currency().equals(m.currency()))))
				i++;
			if (i >= fMonies.size()) {
				fMonies.add(m);
			} else {
				fMonies.set(i, new Money(fMonies.get(i)
						.amount() + m.amount(), m.currency()));
			}
		}
	}
	
	private void appendMoney(IMoney m) {
		if (m instanceof Money) {
			appendMoney(m);
		}
		else {
			MoneyBag moneyBag = (MoneyBag) m;
			for (Money money : moneyBag.fMonies) {
				appendMoney(money);
			}
		}
	}

	@Override
	public boolean equals(Object compare) {
		if (this == compare) return true;
		if (compare == null || getClass() != compare.getClass()) return false;	
		MoneyBag moneyBag = (MoneyBag) compare;
		return Objects.equals(fMonies, moneyBag.fMonies);
		 
	}

	@Override
	public IMoney add(IMoney m) {
		// TODO Auto-generated method stub
		return m.addMoneyBag(this);
	}

	@Override
	public IMoney addMoney(Money m) {
		// TODO Auto-generated method stub
		MoneyBag moneyBag = new MoneyBag(this);
		return moneyBag.add(m);
	}

	@Override
	public IMoney addMoneyBag(MoneyBag m) {
		// TODO Auto-generated method stub
		return null;
	}
}
