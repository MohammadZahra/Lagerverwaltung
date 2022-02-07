package torvalds.WarehouseManager.logic.interfaces;

/**
 * @author Alissa, Pankraz, Zahra, Al Mostafa
 *
 */
public interface Article {

	/*
     * gives the description of the article
     * 
     */
	public String getDescription();
	
	/*
     * edits the description of the article
     * 
     */
	public void setDescription(String description);

	/*
     * gives the currentStock of the article
     * 
     */
	public int getCurrentStock();
	
	/*
     * edits the currentStock of the article
     * 
     */
	public void setCurrentStock(int currentStock);

	/*
     * gives the grossPrice of the article
     * 
     */
	public double getGrossPrice();

	/*
     * edits the grossPrice of the article
     * 
     */
	public void setGrossPrice(double grossPrice);

	/*
     * gives the articleNumber
     * 
     */
	public int getArticleNumber();
	
	/*
     * sets the given articleNumber. Should be use with caution as more than one article
     * using the same articleNumber cannot be added to the same warehouse..
     * 
     */
	public void setArticleNumber(int articleNumber);
	
	/**
	 * Calculates and Returns the VAT of this article
	 * 
	 */
	public double getVat();
	
	/**
	 * Calculates and Returns the net price of this article
	 * 
	 */
	public double getNetPrice();
	
	/**
	 * Calculates and Returns the total net price of this article
	 * 
	 */
	public double getTotalNetPrice();
	
	/**
	 * Calculates and Returns the total gross price of this article
	 * 
	 */
	public double getTotalGrossPrice();

	/**
	 * Returns the WarehouseNumber of the owning warehouse
	 * 
	 */
	int getWarehouseNumber();

	/**
	 * Sets the WarehouseNumber of the owning warehouse
	 * 
	 */
	void setWarehouseNumber(int warehouseNumber);
	
	/*
	 * adds the given amount to the currentStock
	 * 
	 */
	public void addStock(int amount);
	
	/*
	 * takes the given amount from the currentStock
	 * 
	 */
	public void removeStock(int amount);
	
	/*
	 * changes the price of the article by the given percentage
	 * 
	 */
	public void changePrice(double percentage);
	
	/**
	 * Retrieves the Category this article belongs to. 
	 * The Category is an optional field so it can be null.
	 * 
	 */
	public Category getCategory();
	
	/**
	 * Sets the Category of this article.
	 * null is allowed and represents no category.
	 * 
	 */
	public void setCategory(Category category);
}
