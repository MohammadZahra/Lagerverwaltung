package torvalds.WarehouseManager.logic.classes;

import torvalds.WarehouseManager.logic.interfaces.Article;
import torvalds.WarehouseManager.logic.interfaces.Category;
import torvalds.WarehouseManager.utils.ExceptionHandlingUtil;

/**
 * @author Alissa, Pankraz, Zahra, Al Mostafa, Al Abood, Taraq Saeed
 *
 */
 public class ArticleImpl implements Article {
	
	private int articleNumber;
	private String description;
	private int currentStock;
	private double grossPrice;
	private int warehouseNumber;
	private Category category;
	
	private static final String ARTICLENUMBER_NEGATIVE = "You need to put four positive numbers";
	private static final String DESCRIPTION_EMPTY = " You need to put a description";
	private static final String CURRENTSTOCK_NEGATIVE = "You need to put a positive currentStock";
	private static final String GROSS_PRICE_NEGATIVE = " You need to put a postive grossPrice ";
	private static final String AMOUNT_GREATER_NULL = "You need to put a positive amount";
	private static final String CURRENT_STOCK_GREATER_AMOUNT = "You need to put an amount lower or equal to the currentStock";
	
	
	 /*
	   * Constructor Article with articleNumber, description, currentStock, grossPrice, warehouseNumber
	   * 
	   * param 1 articleNumber	>= 0
	   * param 2 description	(not empty)
	   * param 3 currentStock	>= 0
	   * param 4 grossPrice    	>= 0
	   * param 5 warehouseNumber belongs to the owning Warehouse
	   */
	public ArticleImpl(int articleNumber, String description, int currentStock, double grossPrice, int warehouseNumber){
		setArticleNumber(articleNumber);
		setDescription(description);
		setCurrentStock(currentStock);
		setGrossPrice(grossPrice);
		setWarehouseNumber(warehouseNumber);
	}
	
	public ArticleImpl() {
		super();
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public void setDescription(String description) {
		ExceptionHandlingUtil.check(description != null && !description.isBlank(), DESCRIPTION_EMPTY);
		this.description = description;
	}
	
	@Override
	public int getCurrentStock() {
		return currentStock;
	}
	
	@Override
	public void setCurrentStock(int currentStock) {
		ExceptionHandlingUtil.check(currentStock >= 0 , CURRENTSTOCK_NEGATIVE);
		this.currentStock = currentStock;
	}
	
	@Override
	public double getGrossPrice() {
		return round(grossPrice);
	}
	
	@Override
	public void setGrossPrice(double grossPrice) {
		ExceptionHandlingUtil.check(grossPrice >= 0 , GROSS_PRICE_NEGATIVE);
		this.grossPrice = grossPrice;
	}

	@Override
	public int getArticleNumber() {
		return articleNumber;
	}
	
	@Override
	public void setArticleNumber(int articleNumber) {
		ExceptionHandlingUtil.check(articleNumber >= 0, ARTICLENUMBER_NEGATIVE);
		this.articleNumber = articleNumber;
	}
	
	@Override
	public double getVat() {
		return round(grossPrice * 0.19);
	}
	
	@Override
	public double getNetPrice() {
		return round(grossPrice - getVat());
	}
	
	@Override
	public double getTotalNetPrice() {
		return round(getNetPrice() * currentStock);
	}
	
	@Override
	public double getTotalGrossPrice() {
		return round(getGrossPrice() * currentStock);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleNumber;
		result = prime * result + currentStock;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(grossPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ArticleImpl other = (ArticleImpl) obj;
		if (articleNumber != other.articleNumber)
			return false;
		if (currentStock != other.currentStock)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(grossPrice) != Double.doubleToLongBits(other.grossPrice))
			return false;
		return true;
	}

	@Override
	public int getWarehouseNumber() {
		return warehouseNumber;
	}

	@Override
	public void setWarehouseNumber(int warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}
	
	private static double round(double num) {
		return Math.round(num*100) / 100.0;
	}

	@Override
	public void addStock(int amount) {
		ExceptionHandlingUtil.check(amount > 0, AMOUNT_GREATER_NULL);
		currentStock += amount;		
	}

	@Override
	public void removeStock(int amount) {
		ExceptionHandlingUtil.check(amount > 0, AMOUNT_GREATER_NULL);
		ExceptionHandlingUtil.check(amount <= currentStock, CURRENT_STOCK_GREATER_AMOUNT);
		currentStock -= amount;		
	}

	@Override
	public void changePrice(double percentage) {
		double factor = (percentage /100.0) + 1;
		setGrossPrice(grossPrice * factor); 
	}
	
	@Override
	public Category getCategory() {
		return category;
	}

	@Override
	public void setCategory(Category category) {
		this.category= category; 
	}
}