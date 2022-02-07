package torvalds.WarehouseManager.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import torvalds.WarehouseManager.dao.interfaces.WarehouseDao;
import torvalds.WarehouseManager.logic.interfaces.Warehouse;
import torvalds.WarehouseManager.logic.hibernate.WarehouseHibernateImpl;
import torvalds.WarehouseManager.utils.EntityManagerUtil;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
public class WarehouseDaoHibernateImpl implements WarehouseDao {

	@Override
	public Warehouse insertWarehouse(final Warehouse warehouse) {
		EntityManager entityManager = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(warehouse);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		return warehouse;
	}

	@Override
	public Warehouse deleteWarehouse(final int warehouseNumber) {
		final Warehouse warehouse = findWarehouseByPrimaryKey(warehouseNumber);
		return deleteWarehouse(warehouse);
	}

	@Override
	public Warehouse deleteWarehouse(final Warehouse warehouse) {
		EntityManager entityManager = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			final Warehouse warehouseToDelete = entityManager.
					find(WarehouseHibernateImpl.class, warehouse.getWarehouseNumber());
			if (warehouseToDelete != null) {
				entityManager.remove(warehouseToDelete);
			}
			entityManager.getTransaction().commit();
			return warehouse;
		}
		catch(Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		return warehouse;
	}

	@Override
	public Warehouse findWarehouseByPrimaryKey(final int warehouseNumber) {
		EntityManager entityManager = null; 
		
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			Warehouse warehouse = entityManager.
					find(WarehouseHibernateImpl.class, warehouseNumber);
			entityManager.getTransaction().commit();
			return warehouse;
		}
		catch(Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> findAllWarehouses() {
		final List<Warehouse> resultList;
		final String queryString = "SELECT w FROM WarehouseHibernateImpl w";

		EntityManager entityManager = null; 
		
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();

			Query query = entityManager.createQuery(queryString);
			resultList = query.getResultList();
			entityManager.getTransaction().commit();
			return resultList;
		}
		catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public int countWarehouses() {
		EntityManager entityManager = null; 
		
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();

			final String queryString = "SELECT COUNT(w) FROM "
					+ "WarehouseHibernateImpl w ORDER BY "
					+ "w.warehouseNumber";
			Query query = entityManager.createQuery(queryString);
			final int warehouseCount = (int) query.getSingleResult();
			return warehouseCount;
		}
		catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return 0;
		}
		finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public boolean containsWarehouse(final Warehouse warehouse) {
		return containsWarehouse(warehouse.getWarehouseNumber());
	}
	
	@Override
	public boolean containsWarehouse(final int warehouseNumber) {
		return findWarehouseByPrimaryKey(warehouseNumber) != null;
	}

}
