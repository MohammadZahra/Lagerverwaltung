package torvalds.WarehouseManager.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
public class EntityManagerUtil {
	private static final EntityManagerFactory entityManagerFactory;
	private static final String NAME_OF_PERSISTANCE_UNIT = "hibernate-persistence";
	
	static {
		entityManagerFactory = Persistence.
				createEntityManagerFactory(NAME_OF_PERSISTANCE_UNIT);
	}
	
	/**
	 * Creates and Returns an EntityManager-Instance
	 * 
	 */
	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	/**
	 * Closes the given EntityManager
	 * 
	 */
	public static void closeEntityManager(final EntityManager entityManager) {
		entityManager.close();
	}
}
