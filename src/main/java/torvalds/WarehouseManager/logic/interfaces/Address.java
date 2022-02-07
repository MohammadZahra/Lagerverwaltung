package torvalds.WarehouseManager.logic.interfaces;

/**
 * @author Alissa, Pankraz, Zahra
 *
 */
public interface Address {
	
	/*
     * gives the street of the address
     * 
     */
	public String getStreet();

	/*
     * edits the street of the address
     * 
     */
	public void setStreet(String street);
	
	/*
     * gives the number of the address
     * 
     */
	public String getNumber();
	
	/*
     * edits the number of the address
     * 
     */
	public void setNumber(String number);
	
	/*
     * gives the city of the address
     * 
     */
	public String getCity();
	
	/*
     * edits the city of the address
     * 
     */
	public void setCity(String city);
	
	/*
     * gives the postcode of the address
     * 
     */
	public String getPostcode();
	
	/*
     * edits the postcode of the address
     * 
     */
	public void setPostcode(String postcode);
	
	/*
     * edits country of the address
     * 
     */
	public String getCountry();
	
	/*
     * edits country of the address
     * 
     */
	public void setCountry(String country);
}
