package torvalds.WarehouseManager.factories;

import torvalds.WarehouseManager.dao.hibernate.AddressDaoHibernateImpl;
import torvalds.WarehouseManager.dao.hibernate.ArticleDaoHibernateImpl;
import torvalds.WarehouseManager.dao.hibernate.CategoryDaoHibernateImpl;
import torvalds.WarehouseManager.dao.hibernate.WarehouseDaoHibernateImpl;
import torvalds.WarehouseManager.dao.interfaces.AddressDao;
import torvalds.WarehouseManager.dao.interfaces.ArticleDao;
import torvalds.WarehouseManager.dao.interfaces.CategoryDao;
import torvalds.WarehouseManager.dao.interfaces.WarehouseDao;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
public class DaoFactory {
	
	/**
	 * Creates and Returns a WarehouseDao-Instance
	 * 
	 */
	public static WarehouseDao createWarehouseDao() {
		return new WarehouseDaoHibernateImpl();
	}
	
	/**
	 * Creates and Returns a AddressDao-Instance
	 * 
	 */
	public static AddressDao createAddressDao() {
		return new AddressDaoHibernateImpl();
	}
	
	/**
	 * Creates and Returns a ArticleDao-Instance
	 * 
	 */
	public static ArticleDao createArticleDao() {
		return new ArticleDaoHibernateImpl();
	}

	/**
	 * Creates and Returns a CategoryDao-Instance
	 * 
	 */
	public static CategoryDao createCategoryDao() {
		return new CategoryDaoHibernateImpl();
	}
}
