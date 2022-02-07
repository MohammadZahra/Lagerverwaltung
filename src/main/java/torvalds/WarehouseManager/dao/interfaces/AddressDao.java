package torvalds.WarehouseManager.dao.interfaces;

import torvalds.WarehouseManager.logic.interfaces.Address;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
public interface AddressDao {
	/**
	 * Inserts a given Address to the Database
	 * 
	 */
	Address insertAddress(Address address);

	/**
	 * Deletes the given Address from the Database
	 * 
	 */
	Address deleteAddress(Address address);

	/**
	 * Updates the address with the same id as the given one
	 * 
	 */
	Address updateAddress(Address updatedAddress);
}
