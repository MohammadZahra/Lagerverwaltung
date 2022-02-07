package torvalds.WarehouseManager.facades;

import java.util.Map;

import torvalds.WarehouseManager.factories.LogicFactory;
import torvalds.WarehouseManager.logic.interfaces.Address;
import torvalds.WarehouseManager.logic.interfaces.Article;
import torvalds.WarehouseManager.logic.interfaces.Category;
import torvalds.WarehouseManager.logic.interfaces.Warehouse;
import torvalds.WarehouseManager.utils.ExceptionHandlingUtil;

/**
 * @author Alissa, Pankraz, Zahra, Al Mostafa, Taraq Saeed, Al Abood
 *
 */
public class WarehouseManager {
	private static Warehouse currentWarehouse;
	private static DaoFacade daoFacade;
	private static WarehouseManager warehouseManager;
	
	private static final String CURRENT_WAREHOUSE_NULL = "The current warehouse shouldn't be null.";
	private static final String ADDRESS_NULL = "The given Address should not be null";
	private static final String WAREHOUSE_NUMBER_NOT_FOUND = "Could not find a warehouse with the given number in the database";
	private static final String WAREHOUSE_NOT_FOUND = "Could not find the given warehouse in the database";
	private static final String GIVE_WAREHOUSE_DIFFER_IN_DB = "The given warehouse is not identical with the one in the database. "
						+ "A possible cause is: the given warehouse was changed and not yet updated in the database";
	private static final String WAREHOUSE_EXISTS_ALREADY = "A warehouse with the same number exists already in the database";
	private static final String RECEIVING_WAREHOUSE_NOT_FOUND = "Could not find the receiving warehouse in the database";
	private static final String AMOUNT_BIGGER_CURRENT_STOCK = "The amount must be smaller or equal to the current stock of the given article";
	private static final String SEND_TO_SAME_WAREHOUSE = "You cannot send articles from the warehosue to itself";
	private static final String WAREHOUSE_NUMBER_DIFFER_IN_CATEGORY = "The category given to update doesnt belong to the current warehosue. "
						+ "Make Sure the category's warehouseNumber wasn't changed. ";
	private static final String GIVEN_CATEGORY_NULL = "The given category should not be null";

	private WarehouseManager() {
		daoFacade = DaoFacade.getInstance();
	}

	/**
	 * Returns the WarehouseManager-Instance.
	 * Note: this class implements the SingleTon-pattern
	 * 
	 */
	public static WarehouseManager getInstance() {
		if(warehouseManager == null) {
			warehouseManager = new WarehouseManager();
		}
		return warehouseManager;
	}
	
	/**
	 * Inserts the given Warehouse into the database.
	 * Note that a warehouse with the same warehouseNumber must not exist in the database
	 * 
	 */
	public void insertWarehouse(Warehouse warehouse) {
		ExceptionHandlingUtil.check(!daoFacade.containsWarehouse(warehouse), WAREHOUSE_EXISTS_ALREADY);
		daoFacade.insertAddress(warehouse.getAddress());
		daoFacade.insertWarehouse(warehouse);
	}
	
	/**
	 * Sets the given warehouse as the current one.
	 * The given warehouse must exist in the database and be up to date.
	 * or
	 * an exception will be thrown as it should first be added to the database then set as the currentWarehouse.
	 * 
	 */
	public void setCurrentWarehouse(Warehouse warehouse) {
		ExceptionHandlingUtil.check(warehouse != null, CURRENT_WAREHOUSE_NULL);
		ExceptionHandlingUtil.check(daoFacade.containsWarehouse(warehouse), WAREHOUSE_NOT_FOUND);
		Warehouse databaseWarehouse = daoFacade.findWarehouseByPrimaryKey(warehouse.getWarehouseNumber());
		ExceptionHandlingUtil.check(warehouse.equals(databaseWarehouse), GIVE_WAREHOUSE_DIFFER_IN_DB);
		currentWarehouse = warehouse;
	}

	/**
	 * Sets the warehouse with the given warehouseNumber as the current one.
	 * 
	 */
	public void setCurrentWarehouse(int warehouseNumber) {
		Warehouse warehouse = daoFacade.findWarehouseByPrimaryKey(warehouseNumber);
		ExceptionHandlingUtil.check(warehouse != null, WAREHOUSE_NUMBER_NOT_FOUND);
		currentWarehouse = warehouse;
	}
	
	/**
	 * Returns the current warehouse
	 * 
	 */
	public Warehouse getCurrentWarehouse() {
		ExceptionHandlingUtil.check(currentWarehouse != null, CURRENT_WAREHOUSE_NULL);
		return currentWarehouse;
	}
	
	/**
	 * Removes the current warehouse and its: Address, Categories and articles from the database
	 * 
	 */
	public void removeCurrentWarehouse() {
		ExceptionHandlingUtil.check(currentWarehouse != null, CURRENT_WAREHOUSE_NULL);
		for(Article article : currentWarehouse.getArticlesCollection()) {
			daoFacade.deleteArticle(article);
		}
		for(Category category : currentWarehouse.getCategoriesCollection()) {
			daoFacade.deleteCategory(category);
		}
		daoFacade.deleteAddress(currentWarehouse.getAddress());
		daoFacade.deleteWarehouse(currentWarehouse);
		currentWarehouse = null;
	}
	
	/**
	 * Inserts the given article into the current warehouse
	 * 
	 */
	public void insertArticleToCurrentWarehouse(Article article) {
		ExceptionHandlingUtil.check(currentWarehouse != null, CURRENT_WAREHOUSE_NULL);
		currentWarehouse.insertArticle(article);
		article.setWarehouseNumber(currentWarehouse.getWarehouseNumber());
		daoFacade.insertArticle(article);
	}
	
	/**
	 * Deletes the given Article from the current warehouse
	 * 
	 */
	public void deleteArticleFromCurrentWarehouse(Article article) {
		ExceptionHandlingUtil.check(currentWarehouse != null, CURRENT_WAREHOUSE_NULL);
		daoFacade.deleteArticle(article);
		currentWarehouse.deleteArticle(article);
	}
	
	/**
	 * Updates the article with the same given article's number in the current warehouse.
	 * 
	 */
	public void updateArticleInCurrentWarehouse(Article updatedArticle) {
		ExceptionHandlingUtil.check(currentWarehouse != null, CURRENT_WAREHOUSE_NULL);
		currentWarehouse.updateArticle(updatedArticle);
		daoFacade.updateArticle(updatedArticle);
	}
	
	/**
	 * Inserts the given category into the current warehouse
	 * 
	 */
	public void insertCategoryToCurrentWarehouse(Category category) {
		ExceptionHandlingUtil.check(currentWarehouse != null, CURRENT_WAREHOUSE_NULL);
		currentWarehouse.insertCategory(category);
		category.setWarehouseNumber(currentWarehouse.getWarehouseNumber());
		daoFacade.insertCategory(category);
	}

	/**
	 * Deletes the given category from the current warehouse
	 * 
	 */
	public void deleteCategoryFromCurrentWarehouse(Category category) {
		ExceptionHandlingUtil.check(currentWarehouse != null, CURRENT_WAREHOUSE_NULL);
		daoFacade.deleteCategory(category);
		currentWarehouse.deleteCategory(category);
	}
	
	/**
	 * Updates the category with the same given categoryId the current warehouse.
	 * 
	 */
	public void updateCategoryInCurrentWarehouse(Category category) {
		/*
		 * OLBERTZ Auf keinen Fall mehr die check-Methode von Herrn Folz benutzen! Ich sage in den
		 * Uebungen ja immer: Ab der fuenften Uebung sollte das Teil nicht mehr verwendet werden. 
		 * Die Methode wirft immer die gleiche Exception fuer alle Fehler. Das ist nicht praktikabel,
		 * bei grossen Programmen sogar gefaehrlich, weil die Auswahl der richtigen Exception wirklich
		 * wichtig werden kann. Auch eigene Exceptions sollte man verwenden. All das geht mit dieser
		 * doofen check-Methode nicht. 
		 * 
		 * Zudem ist sie schwer lesbar, weil man sich an umgekehrtes Lesen gewoehnen muss. Eine Ausnahme
		 * wird geworfen, aber die Bedingung ist die fuer den Fall, dass keine Ausnahme geworfen wird. Das
		 * verringert die Lesbarkeit sehr. 
		 * 
		 * Besser eine if-Anweisung schreiben. Das ist ein bisschen Code mehr, aber man kann sie gut lesen
		 * und sofort verstehen und man kann jede Exception werfen. Man kann sich auch Validatorklassen
		 * schreiben, die im Prinzip aehnlich funktionieren wie die check-Methode, wenn man die if-Anweisungen
		 * auslagern moechte. 
		 * 
		 *  Aber auf keinen Fall die check-Methode verwenden!
		 */
		ExceptionHandlingUtil.check(currentWarehouse != null, CURRENT_WAREHOUSE_NULL);
		ExceptionHandlingUtil.check(category != null, GIVEN_CATEGORY_NULL);
		ExceptionHandlingUtil.check(category.getWarehouseNumber() == currentWarehouse.getWarehouseNumber(),
							WAREHOUSE_NUMBER_DIFFER_IN_CATEGORY);
		daoFacade.updateCategory(category);
	}

	/**
	 * Updates the address with the same given addressId the current warehouse.
	 * 
	 */
	public void updateAddress(Address address) {
		ExceptionHandlingUtil.check(address != null, ADDRESS_NULL);
		daoFacade.updateAddress(address);
	}

	/**
	 * Sends the given amount of the given article from the current warehouse to the warehouse
	 * with the given receivingWarehouseNumber.
	 * 
	 * @param receivingWarehouseNumber must belong to an existing warehouse in the database
	 * @param article
	 * @param amount must not be more than the articles currentStock
	 */
	public void sendArticlesFromCurrentWarehouseTo(int receivingWarehouseNumber, Article article, int amount) {
		ExceptionHandlingUtil.check(currentWarehouse != null, CURRENT_WAREHOUSE_NULL);
		ExceptionHandlingUtil.check(receivingWarehouseNumber != currentWarehouse.getWarehouseNumber(), SEND_TO_SAME_WAREHOUSE);
		Warehouse receivingWarehouse = daoFacade.findWarehouseByPrimaryKey(receivingWarehouseNumber);
		ExceptionHandlingUtil.check(receivingWarehouse != null, RECEIVING_WAREHOUSE_NOT_FOUND);
		ExceptionHandlingUtil.check(article.getCurrentStock() >= amount, AMOUNT_BIGGER_CURRENT_STOCK);
		Map<Integer, Article> articlesMap = receivingWarehouse.getArticlesMap();
		boolean articleExistsInReceivingWarehouse = articlesMap.containsKey(article.getArticleNumber());
		
		/* OLBERTZ Ruhig mal Leerzeilen einbauen, damit die ganze Sache besser lesbar wird. Gerade vor 
		 * groesseren Bloecken wie if-Anweisungen erhoeht eine Leerzeile die Lesbarkeit. Ich hatte
		 * das in der Vorlesung vertikalen Abstand genannt.  
		 */
		if(articleExistsInReceivingWarehouse) {
			Article receivingArticle = articlesMap.get(article.getArticleNumber());
			receivingArticle.addStock(amount);
			article.removeStock(amount);
			daoFacade.updateArticle(receivingArticle);
		} else {
			article.removeStock(amount);
			Article receivingArticle = LogicFactory.createArticle(article.getArticleNumber(), article.getDescription(),
					amount, article.getGrossPrice(), receivingWarehouseNumber);
			daoFacade.insertArticle(receivingArticle);			
		}
		daoFacade.updateArticle(article);
	}
}
