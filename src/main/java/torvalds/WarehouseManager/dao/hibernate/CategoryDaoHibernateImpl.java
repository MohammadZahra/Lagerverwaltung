package torvalds.WarehouseManager.dao.hibernate;

import javax.persistence.EntityManager;

import torvalds.WarehouseManager.dao.interfaces.CategoryDao;
import torvalds.WarehouseManager.logic.hibernate.CategoryHibernateImpl;
import torvalds.WarehouseManager.logic.hibernate.CategoryPK;
import torvalds.WarehouseManager.logic.interfaces.Category;
import torvalds.WarehouseManager.utils.EntityManagerUtil;

/**
 * The Hibernate Implementaion of CategoryDao
 * 
 * @author Al Mostafa
 */
public class CategoryDaoHibernateImpl implements CategoryDao {

	@Override
	public Category insertCategory(final Category category) {
		EntityManager entityManager = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(category);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		return category;
	}

	@Override
	public Category deleteCategory(final Category category) {
		EntityManager entityManager = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			if(entityManager.contains(category)) {
				entityManager.remove(category);
				
			} else {
				entityManager.remove(entityManager.merge(category));
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		return category;
	}

	private Category findCategoryByPrimaryKey(final CategoryPK categoryPK) {
		EntityManager entityManager = null; 
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			Category category = entityManager.
					find(CategoryHibernateImpl.class, categoryPK);
			entityManager.getTransaction().commit();
			return category;
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

	@Override
	public Category updateCategory(final Category updatedCategory) {
		EntityManager entityManager = null;
		CategoryHibernateImpl updatedCategoryHibernateImpl = (CategoryHibernateImpl) updatedCategory;
		Category oldCategory = findCategoryByPrimaryKey(updatedCategoryHibernateImpl.getCategoryPK());
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			if(oldCategory != null) {
				oldCategory.setCategoryName(updatedCategory.getCategoryName());
				// Category-WarehouseNumber should not be changed as a category belongs to exactly one warehouse
				// and cannot be sent from a warehouse to another
				// oldCategory.setWarehouseNumber(updatedCategory.getWarehouseNumber());
				entityManager.merge(oldCategory);
			} else {
				throw new IllegalArgumentException("The given Category does not exist: #"
							+ updatedCategoryHibernateImpl.getCategoryId());
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		return oldCategory;
	}
}
