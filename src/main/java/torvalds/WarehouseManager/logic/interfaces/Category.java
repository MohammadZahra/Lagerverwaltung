package torvalds.WarehouseManager.logic.interfaces;

import java.util.Collection;
import java.util.Set;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
public interface Category {

	/**
	 * Returns the Category-Name
	 */
	public String getCategoryName();

	/**
	 * Sets the Category-Name
	 */
	public void setCategoryName(String categoryName);
	
	/**
	 * Returns the Warehouse Number
	 */
	public int getWarehouseNumber();

	/**
	 * Sets the Warehouse Number
	 */
	public void setWarehouseNumber(int warehouseNumber);
	
	/**
	 * Returns a collection over all articles that belong to this category
	 * 	
	 */
	public Collection<Article> getArticlesCollection();
	
	/**
	 * Returns the Set of articles that belong to this category
	 * 
	 */
	public Set<Article> getArticlesSet();
	
	/**
	 * Sets the given articles as the articles that belong to this category.
	 * null is allowed and represents an empty set.
	 * 
	 */
	public void setArticlesSet(Set<Article> articlesSet); 
}
