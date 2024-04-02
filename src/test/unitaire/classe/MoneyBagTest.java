package test.unitaire.classe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyBagTest {

	Money f12CHF;
	Money f14CHF;
	Money f7USD;
	Money f21USD;
	MoneyBag fMB1;
	MoneyBag fMB2;
	
	@Before
	public void setUp() {
		f12CHF = new Money(12, "CHF");
		f14CHF = new Money(14, "CHF");
		f7USD = new Money(7, "USD");
		f21USD = new Money(21, "USD");
		fMB1 = new MoneyBag(f12CHF, f7USD);
		fMB2 = new MoneyBag(f14CHF, f21USD);
		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testBagEquals() {
		assertTrue(!fMB1.equals(null));
		assertEquals(fMB1, fMB1);
		assertTrue(!fMB1.equals(f12CHF));
		assertTrue(!f12CHF.equals(fMB1));
		assertTrue(!fMB1.equals(fMB2));
	}
	
	@Test
	public void TestMixedSimpleAdd() {
		// [12 CHF] + [7 USD] = {[12CHF][7USD]}
		Money bag[] = { f12CHF, f7USD };
		MoneyBag expected = new MoneyBag(bag);
		assertEquals(expected, f12CHF.add(f7USD));
	}
	
	@Test
	public void testBagSimpleAdd() {
	    // [12 CHF] + {[7 USD]} == {[12 CHF][7 USD]}
	    Money expected[] = {f12CHF, f7USD};
	    MoneyBag expectedBag = new MoneyBag(expected);
	    
	    assertEquals(expectedBag, f12CHF.add(new Money(7, "USD")));
	}

	@Test
	public void testSimpleBagAdd() {
	    // [12 CHF] + {[14 CHF][21 USD]} == {[26 CHF][21 USD]}
	    Money expected[] = {new Money(26, "CHF"), f21USD};
	    MoneyBag expectedBag = new MoneyBag(expected);
	    
	    assertEquals(expectedBag, fMB2.add(f12CHF));
	}

	@Test
	public void testBagBagAdd() {
	    // {[12 CHF][7 USD]} + {[14 CHF][21 USD]} == {[26 CHF][28 USD]}
	    Money expected[] = {new Money(26, "CHF"), new Money(28, "USD")};
	    MoneyBag expectedBag = new MoneyBag(expected);
	    
	    assertEquals(expectedBag, fMB1.add(fMB2));
	}
	
	@Test
	public void testRemoveMoney() {
	    // [-12 CHF] + {[12 CHF][7 USD]} == {[7 USD]}
	    Money expected[] = { f7USD };
	    MoneyBag expectedBag = new MoneyBag(expected);
	    
	    assertEquals(expectedBag, fMB1.add(new Money(-12, "CHF")));
	}
	
	@Test
	public void testSimplifyMoneyBag() {
		// [-14 CHF] + {[14 CHF][21USD]} == [21 USD]
		Money expected = f21USD;
		Money f_14CHF = new Money(-14, "CHF");
		
		MoneyBag result = (MoneyBag) fMB2.add(f_14CHF);
		assertEquals(expected, result.simplifyMoneyBag());
	}
	
	@Test (expected = IllegalStateException.class)
	public void testSimplifyMoneyBagMultipleValues() {
		// conversion de fMB2 en Money impossible
		fMB2.simplifyMoneyBag();
	}

}
