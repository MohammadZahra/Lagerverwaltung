package torvalds.WarehouseManager.logic.classes;

import torvalds.WarehouseManager.logic.classes.AddressImpl;
import torvalds.WarehouseManager.logic.interfaces.Address;
import torvalds.WarehouseManager.utils.ExceptionHandlingUtil;
/*
 * @author Alissa, Pankraz, Zahra
 *
 */
public class AddressImpl implements Address {
	
	private String street;
	private String number;
	private String city;
	private String postcode;
	private String country;
	
	private static final String STREET_EMPTY = "You need to put in a street";
	private static final String NUMBER_EMPTY = " You need to put a number";
	private static final String CITY_EMPTY = " You need to put in a city";
	private static final String POSTCODE_EMPTY = "You need to put in a postcode";
	private static final String COUNTRY_EMPTY = "You need to put in a country";
	
   /*
   * Constructor Address with Street, Number, City, Postcode, Country
   * 
   * param 1 street		(not empty)
   * param 2 number		(not empty)
   * param 3 city		(not empty)
   * param 4 postcode	(not empty)
   * param 5 country	(not empty)
   */
	public AddressImpl(String street, String number, String city, String postcode, String country) {		
		setStreet(street);
		setNumber(number);
		setCity(city);
		setPostcode(postcode);
		setCountry(country);
	}
	
	/*
	 *	Constructor
	 */
	public AddressImpl() {
		super();
	}
	
	/*
     * gives the street of the address
     *  
     * return address
     */
	public String getStreet() {
		return street;
	}
	
	/*
     * edits the street of the address
     *  
     * param street
     */
	public void setStreet(String street) {
		ExceptionHandlingUtil.check(street != null && street != "", STREET_EMPTY);
		this.street = street;
	}

	/*
     * gives the number of the address
     *  
     * return number
     */
	public String getNumber() {
		return number;
	}
	
	/*
     * edits the number of the address
     *  
     * param number
     */
	public void setNumber(String number) {
		ExceptionHandlingUtil.check(number != null && number != "", NUMBER_EMPTY);
		this.number = number;
	}

	/*
     * gives the city of the address
     *  
     * return city
     */
	public String getCity() {
		return city;
	}
	
	/*
     * edits the city of the address
     *  
     * param city
     */
	public void setCity(String city) {
		ExceptionHandlingUtil.check(city != null && city != "", CITY_EMPTY);
		this.city = city;
	}
	
	/*
     * gives the postcode of the address
     *  
     * return postcode
     */
	public String getPostcode() {
		return postcode;
	}
	
	/*
     * edits the postcode of the address
     *  
     * param postcode
     */
	public void setPostcode(String postcode) {
		ExceptionHandlingUtil.check(postcode != null && postcode != "", POSTCODE_EMPTY);
		this.postcode = postcode;
	}
	
	/*
     * gives the country of the address
     *  
     * return country
     */
	public String getCountry() {
		return country;
	}

	/*
     * edits country of the address
     *  
     * param country
     */
	public void setCountry(String country) {
		ExceptionHandlingUtil.check(country != null && country != "", COUNTRY_EMPTY);
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}


	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressImpl other = (AddressImpl) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	// goal of this method is to show that you can view the address of 
	// the currently opened warehouse
	public String toString() {
		return String.format("%s, %s %s %s %s", getStreet(), getNumber(), getCity(), getPostcode(), getCountry());
	}
}