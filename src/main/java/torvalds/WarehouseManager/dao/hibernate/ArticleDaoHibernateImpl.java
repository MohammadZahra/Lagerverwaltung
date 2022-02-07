package torvalds.WarehouseManager.dao.hibernate;

import javax.persistence.EntityManager;

import torvalds.WarehouseManager.dao.interfaces.ArticleDao;
import torvalds.WarehouseManager.logic.hibernate.ArticleHibernateImpl;
import torvalds.WarehouseManager.logic.hibernate.ArticlePK;
import torvalds.WarehouseManager.logic.interfaces.Article;
import torvalds.WarehouseManager.utils.EntityManagerUtil;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
public class ArticleDaoHibernateImpl implements ArticleDao {

	@Override
	public Article insertArticle(final Article article) {
		EntityManager entityManager = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(article);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		return article;
	}

	@Override
	public Article deleteArticle(final Article article) {
		EntityManager entityManager = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			if(entityManager.contains(article)) {
				entityManager.remove(article);
				
			} else {
				entityManager.remove(entityManager.merge(article));
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		return article;
	}

	private Article findArticleByPrimaryKey(final ArticlePK articlePK) {
		EntityManager entityManager = null; 
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			Article article = entityManager.
					find(ArticleHibernateImpl.class, articlePK);
			entityManager.getTransaction().commit();
			return article;
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
	public Article updateArticle(final Article updatedArticle) {
		EntityManager entityManager = null;
		ArticleHibernateImpl updatedArticleHibernateImpl = (ArticleHibernateImpl) updatedArticle;
		Article oldArticle = findArticleByPrimaryKey(updatedArticleHibernateImpl.getArticlePK());
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			if(oldArticle != null) {
				oldArticle.setCurrentStock(updatedArticle.getCurrentStock());
				oldArticle.setDescription(updatedArticle.getDescription());
				oldArticle.setGrossPrice(updatedArticle.getGrossPrice());
				oldArticle.setWarehouseNumber(updatedArticle.getWarehouseNumber());
				entityManager.merge(oldArticle);
			} else {
				throw new IllegalArgumentException("The given Article does not exist: #"
							+ updatedArticle.getArticleNumber());
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		return oldArticle;
	}
}
