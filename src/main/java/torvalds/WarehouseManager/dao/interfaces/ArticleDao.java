package torvalds.WarehouseManager.dao.interfaces;

import torvalds.WarehouseManager.logic.interfaces.Article;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
public interface ArticleDao {
	/**
	 * Inserts the given Article to the Database
	 * 
	 */
	Article insertArticle(Article article);
	
	/**
	 * Deletes the given Article from the Database
	 * 
	 */
	Article deleteArticle(Article article);
	
	/**
	 * Updates the given Article
	 * 
	 */
	Article updateArticle(Article updatedArticle);
}
