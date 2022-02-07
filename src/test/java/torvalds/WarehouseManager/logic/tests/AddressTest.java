package torvalds.WarehouseManager.logic.tests;

import org.junit.Test;

import torvalds.WarehouseManager.logic.classes.AddressImpl;

public class AddressTest {
	private AddressImpl addressImpl;
	
	
	/*
	 * The input is valid. Expecting a address in N566L London, London Street "2a", in the UK.
	 */
	@Test
    public void testAddAdress_validInput() {
        addressImpl = new AddressImpl("London Street", "2a", "N566L", "London", "UK");
	}
	
	/*
	 * The input is invalid because the street is empty. Expecting an IllegalArgumentException.
	 */
    @Test(expected = RuntimeException.class)
    public void testAddAdress_invalidInput1() {
    	addressImpl = new AddressImpl("", "2a", "N566L", "London", "UK");
    }
    
    /*
	 * The input is invalid because the number is empty. Expecting an IllegalArgumentException.
	 */
    @Test(expected = RuntimeException.class)
    public void testAddAddress_invalidInput2() {
    	addressImpl = new AddressImpl("London Street", "", "N566L", "London", "UK");
    }
    
    /*
	 * The input is invalid because the postcode is empty. Expecting an IllegalArgumentException.
	 */
    @Test(expected = RuntimeException.class)
    public void testAddAddress_invalidInput3() {
    	addressImpl = new AddressImpl("London Street", "2a", "", "London", "UK");
    }
    
    /*
	 * The input is invalid because the city is empty. Expecting an IllegalArgumentException.
	 */
    @Test(expected = RuntimeException.class)
    public void testAddAddress_invalidInput4() {
    	addressImpl = new AddressImpl("London Street", "2a", "N566L", "", "UK");
    }
    
    /*
	 * The input is invalid because the country is empty. Expecting an IllegalArgumentException.
	 */
    @Test(expected = RuntimeException.class)
    public void testAddAddress_invalidInput5() {
    	addressImpl = new AddressImpl("London Street", "2a", "N566L", "London", "");
    }
    
    /*
	 * The input is valid. Expecting a new street, London Street.
	 */
	public void testSetStreet_validInput() {
		addressImpl.setStreet("London Street");
	}
	
	/*
	 * The input is invalid because the street is empty. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetStreet_invalidInput1() {
		addressImpl.setStreet("");
	}
	
	/*
	 * The input is valid. Expecting a new number, 3a.
	 */
	public void testSetNumber_validInput() {
		addressImpl.setNumber("3a");
	}
	
	/*
	 * The input is invalid because the number is empty. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetNumber_invalidInput1() {
		addressImpl.setNumber("");
	}
	
	/*
	 * The input is valid. Expecting a new postcode, M661L.
	 */
	public void testSetPostcode_validInput() {
		addressImpl.setPostcode("M661L");
	}
	
	/*
	 * The input is invalid because the postcode is empty. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetPostcode_invalidInput1() {
		addressImpl.setPostcode("");
	}
	
	/*
	 * The input is valid. Expecting a new city, Trier.
	 */
	public void testSetCity_validInput() {
		addressImpl.setCity("Trier");
	}
	
	/*
	 * The input is invalid because the city is empty. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetCity_invalidInput1() {
		addressImpl.setCity("");
	}
	
	/*
	 * The input is valid. Expecting a new country, Germany.
	 */
	public void testSetCountry_validInput() {
		addressImpl.setCountry("Germany");
	}
	
	/*
	 * The input is invalid because the country is empty. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetCountry_invalidInput1() {
		addressImpl.setCountry("");
	}
	
}
