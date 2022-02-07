package torvalds.WarehouseManager.dao.interfaces;

import torvalds.WarehouseManager.logic.interfaces.Category;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
public interface CategoryDao {
	/**
	 * Inserts the given Category to the Database
	 * 
	 */
	Category insertCategory(Category category);
	
	/**
	 * Deletes the given Category from the Database
	 * 
	 */
	Category deleteCategory(Category category);
	
	/**
	 * Updates the Category with the same identifier in the Database
	 * 
	 */
	Category updateCategory(Category updatedCategory);
}
