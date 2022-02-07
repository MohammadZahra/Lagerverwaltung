package torvalds.WarehouseManager.facades;

import java.util.List;

import torvalds.WarehouseManager.dao.interfaces.AddressDao;
import torvalds.WarehouseManager.dao.interfaces.ArticleDao;
import torvalds.WarehouseManager.dao.interfaces.CategoryDao;
import torvalds.WarehouseManager.dao.interfaces.WarehouseDao;
import torvalds.WarehouseManager.factories.DaoFactory;
import torvalds.WarehouseManager.logic.interfaces.Address;
import torvalds.WarehouseManager.logic.interfaces.Article;
import torvalds.WarehouseManager.logic.interfaces.Category;
import torvalds.WarehouseManager.logic.interfaces.Warehouse;

/**
 * 
 * @author Al Mostafa
 */
public class DaoFacade {
	private AddressDao addressDao;
	private ArticleDao articleDao;
	private CategoryDao categoryDao;
	private WarehouseDao warehouseDao;
	private static DaoFacade daoFacade;

	private DaoFacade() {
		addressDao = DaoFactory.createAddressDao();
		articleDao = DaoFactory.createArticleDao();
		categoryDao = DaoFactory.createCategoryDao();
		warehouseDao = DaoFactory.createWarehouseDao();
	}

	/**
	 * Returns the single-Instance of this class
	 * 
	 */
	public static DaoFacade getInstance() {
		if (daoFacade == null) {
			daoFacade = new DaoFacade();
		}
		return daoFacade;
	}

	public Address insertAddress(Address address) {
		return addressDao.insertAddress(address);
	}

	public Address deleteAddress(Address address) {
		return addressDao.deleteAddress(address);
	}

	public Article insertArticle(Article article) {
		return articleDao.insertArticle(article);
	}

	public Article deleteArticle(Article article) {
		return articleDao.deleteArticle(article);
	}

	public Article updateArticle(Article updatedArticle) {
		return articleDao.updateArticle(updatedArticle);
	}

	public Category insertCategory(Category category) {
		return categoryDao.insertCategory(category);
	}

	public Category deleteCategory(Category category) {
		return categoryDao.deleteCategory(category);
	}
	
	public Category updateCategory(Category updatedCategory) {
		return categoryDao.updateCategory(updatedCategory);
	}

	public Warehouse insertWarehouse(Warehouse warehouse) {
		return warehouseDao.insertWarehouse(warehouse);
	}

	public Warehouse deleteWarehouse(int warehouseNumber) {
		return warehouseDao.deleteWarehouse(warehouseNumber);
	}

	public Warehouse deleteWarehouse(Warehouse warehouse) {
		return warehouseDao.deleteWarehouse(warehouse);
	}

	public Warehouse findWarehouseByPrimaryKey(int warehouseNumber) {
		return warehouseDao.findWarehouseByPrimaryKey(warehouseNumber);
	}

	public List<Warehouse> findAllWarehouses() {
		return warehouseDao.findAllWarehouses();
	}

	public int countWarehouses() {
		return warehouseDao.countWarehouses();
	}

	public boolean containsWarehouse(Warehouse warehouse) {
		return warehouseDao.containsWarehouse(warehouse);
	}

	public boolean containsWarehouse(int warehouseNumber) {
		return warehouseDao.containsWarehouse(warehouseNumber);
	}

	public Address updateAddress(Address address) {
		return addressDao.updateAddress(address);
	}
}
