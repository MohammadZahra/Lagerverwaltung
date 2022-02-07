package torvalds.WarehouseManager.dao.interfaces;

import java.util.List;

import torvalds.WarehouseManager.logic.interfaces.Warehouse;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
public interface WarehouseDao {
	
	/**
	 * Inserts a given Warehouse to the Database
	 * 
	 */
	Warehouse insertWarehouse(Warehouse warehouse);
	
	/**
	 * Deletes the Warehouse with the given Number from the Database
	 * 
	 */
	Warehouse deleteWarehouse(int warehouseNumber);
	
	/**
	 * Deletes the given Warehouse from the Database
	 * 
	 */
	Warehouse deleteWarehouse(Warehouse warehouse);
	
	/**
	 * Finds and returns the Warehouse with the given Number
	 * 
	 */
	Warehouse findWarehouseByPrimaryKey(int warehouseNumber);
	
	/**
	 * Returns all Warehouses in the Database
	 * 
	 */
	List<Warehouse> findAllWarehouses();

	/**
	 * Returns the number of Warehouses stored in the Database
	 * 
	 */
	int countWarehouses();
	
	/**
	 * Returns whether the given warehouse exits in the database or not
	 * 
	 */
	boolean containsWarehouse(Warehouse warehouse);
	
	/**
	 * Returns whether a warehouse with given number exits or not
	 * 
	 */
	boolean containsWarehouse(int warehouseNumber);
}
