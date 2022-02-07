package torvalds.WarehouseManager.logic.interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author Alissa, Pankraz, Zahra, Al Mostafa,Al Abood, Tareq Saeed
 *
 */
public interface Warehouse {

	/*
	 * gives the address of a warehouse
	 * 
	 */
	public Address getAddress();

	/*
	 * sets the address of a warehouse
	 *
	 */
	public void setAddress(Address address);

	/*
	 * gives the number of a warehouse
	 * 
	 */
	public int getWarehouseNumber();

	/*
	 * sets the warehouseNumber. Use with caution. This number has to be unique for
	 * each warehouse.
	 *
	 */
	public void setWarehouseNumber(int warehouseNumber);

	/*
	 * gives a collection over the articles of warehouse
	 * 
	 */
	public Collection<Article> getArticlesCollection();

	/**
	 * Returns the articles-map used by this warehouse
	 * 
	 */
	public Map<Integer, Article> getArticlesMap();

	/**
	 * Sets the articles-map used by this warehouse
	 * 
	 */
	public void setArticlesMap(Map<Integer, Article> articles);

	/**
	 * Inserts the given article to this warehouse
	 */
	public void insertArticle(Article article);

	/**
	 * Deletes the given article from this warehouse
	 * 
	 */
	public void deleteArticle(Article article);

	/**
	 * Checks if the given article exists in this warehouse
	 * 
	 */
	public boolean containsArticle(Article article);

	/**
	 * Checks if the article with the given number exists in this warehouse
	 * 
	 */
	public boolean containsArticle(int articleNumber);

	/**
	 * Update the article in this warehouse with the same number as the given
	 * article
	 * 
	 */
	public void updateArticle(Article updatedArticle);

	/*
	 * gives a collection over the categories of warehouse
	 * 
	 */
	public Collection<Category> getCategoriesCollection();

	/**
	 * Returns the categories-Set used by this warehouse
	 * 
	 */
	public Set<Category> getCategoriesSet();

	/**
	 * Sets the categories-Set used by this warehouse
	 * 
	 */
	public void setCategoriesSet(Set<Category> categories);

	/**
	 * Inserts the given category to this warehouse
	 */
	public void insertCategory(Category category);
	
	/**
	 * Deletes the given category from this warehouse
	 * 
	 */
	public void deleteCategory(Category category);

	/**
	 * filter the article in warehouse
	 * 
	 */
	public List<Article> filter(Predicate<Article> predicate);
}
