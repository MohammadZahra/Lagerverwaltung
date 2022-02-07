package torvalds.WarehouseManager.factories;

import torvalds.WarehouseManager.logic.hibernate.AddressHibernateImpl;
import torvalds.WarehouseManager.logic.hibernate.ArticleHibernateImpl;
import torvalds.WarehouseManager.logic.hibernate.CategoryHibernateImpl;
import torvalds.WarehouseManager.logic.hibernate.WarehouseHibernateImpl;
import torvalds.WarehouseManager.logic.interfaces.Address;
import torvalds.WarehouseManager.logic.interfaces.Article;
import torvalds.WarehouseManager.logic.interfaces.Category;
import torvalds.WarehouseManager.logic.interfaces.Warehouse;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
public class LogicFactory {

	/**
	 * Creates and Returns an Address-Instance
	 * 
	 */
	public static Address createAddress() {
		return new AddressHibernateImpl();
	}

	/**
	 * Creates and Returns an Address-Instance with the given values
	 * 
	 */
	public static Address createAddress(String street, String number, String city, String postcode, String country) {
		return new AddressHibernateImpl(street, number, city, postcode, country);
	}

	/**
	 * Creates and returns a Warehouse-Instance with the given values
	 * 
	 */
	public static Warehouse createWarehouse(int warehouseNumber, Address address) {
		return new WarehouseHibernateImpl(warehouseNumber, address);
	}

	/**
	 * Creates and returns an Article-Instance
	 * 
	 */
	public static Article createArticle() {
		return new ArticleHibernateImpl();
	}

	/**
	 * Creates and returns an Article-Instance with the given parameters
	 * WarehouseNumber must belong to the owning warehouse of this article.
	 * 
	 */
	public static Article createArticle(int articleNumber, String description, int currentStock, double grossPrice,
			int warehouseNumber) {
		return new ArticleHibernateImpl(articleNumber, description, currentStock, grossPrice, warehouseNumber);
	}

	/**
	 * Creates and returns a Category-Instance
	 * 
	 */
	public static Category createCategory(String categoryName, int warehouseNumber) {
		return new CategoryHibernateImpl(categoryName, warehouseNumber);
	}
	
	/**
	 * Creates and returns a Category-Instance
	 * 
	 */
	public static Category createCategory() {
		return new CategoryHibernateImpl();
	}
}
