package torvalds.WarehouseManager.logic.tests;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;

import torvalds.WarehouseManager.logic.classes.AddressImpl;
import torvalds.WarehouseManager.logic.classes.ArticleImpl;
import torvalds.WarehouseManager.logic.classes.CategoryImpl;
import torvalds.WarehouseManager.logic.classes.WarehouseImpl;
import torvalds.WarehouseManager.logic.interfaces.Article;

public class WarehouseTest {
	private WarehouseImpl warehouse;	
	
	/*
	 * The input is valid. Expecting a warehouse with number zero and with the given address.
	 */
	@Test
    public void testAddWarehouse_validInput() {
        warehouse = new WarehouseImpl(0, new AddressImpl("London Street", "2a", "N566L", "London", "UK"));
	}
	
	/*
	 * The input is invalid because the warehouseNumber is negative. Expecting an IllegalArgumentException.
	 */
    @Test(expected = RuntimeException.class)
    public void testAddWarehouse_invalidInput1() {
        warehouse = new WarehouseImpl(-1, new AddressImpl("London Street", "2a", "N566L", "London", "UK"));
    }

	/*
	 * The input is invalid because the address is not correct. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testAddWarehouse_invalidInput2() {
		warehouse = new WarehouseImpl(0, new AddressImpl("", "2a", "N566L", "London", "UK"));
	}
	
	/*
	 * The input is valid. Expecting a new Address in N566L London, London Street "2a", in the UK..
	 */
	public void testSetAddress_validInput() {
		warehouse.setAddress(new AddressImpl("London Street", "2a", "N566L", "London", "UK"));
	}
	
	/*
	 * The input is invalid because the address is not correct. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetAddress_invalidInput1() {
		warehouse.setAddress(new AddressImpl("", "2a", "N566L", "London", "UK"));
	}
	
	@Test
	public void testSetWarehouseNumber_validInput() {
		warehouse = new WarehouseImpl(0, new AddressImpl());
		warehouse.setWarehouseNumber(1);
	}
	
	/*
	 * The input is invalid because the warehouseNumber is negative. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetWarehouseNumber_invalidInput() {
		warehouse.setWarehouseNumber(-4);
	}
	
	@Test
	public void testSetArticlesMap_validInput() {
		warehouse = new WarehouseImpl(0, new AddressImpl());
		Map<Integer, Article> articles = new HashMap<>();
		articles.put(0, (Article) new ArticleImpl(0, "Laptop", 0, 2, 0));
		warehouse.setArticlesMap(articles);
	}
	
	/*
	 * The input is invalid because the articles is null. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetArticlesMap_invalidInput() {
		warehouse.setArticlesMap(null);
	}
	
	@Test
	public void testInsertArticle_validInput() {
		warehouse = new WarehouseImpl(0, new AddressImpl());
		warehouse.insertArticle(new ArticleImpl(0, "Chair", 0, 50, 0));
	}
	
	/*
	 * The input is invalid because the article is null
	 */
	@Test(expected = RuntimeException.class)
	public void testInsertArticle_invalidInput() {
		warehouse.insertArticle(null);
	}
	
	@Test
	public void testDeleteArticle_validInput() {
		warehouse = new WarehouseImpl(0, new AddressImpl());
		Map<Integer, Article> articles = new HashMap<>();
		articles.put(0, new ArticleImpl(0, "Chair", 0, 50, 0));
		warehouse.setArticlesMap(articles);
		warehouse.deleteArticle(new ArticleImpl(0, "Chair", 0, 50, 0));
	}
	
	/*
	 * The input is invalid because the article is null
	 */
	@Test(expected = RuntimeException.class)
	public void testDeleteArticle_invalidInput() {
		warehouse.deleteArticle(null);
	}
	
	@Test
	public void testContainsArticle1_validInput() {
		warehouse = new WarehouseImpl(0, new AddressImpl());
		warehouse.containsArticle(new ArticleImpl(0, "Chair", 0, 50, 0));
	}
	
	/*
	 * The input is invalid because the article is null
	 */
	@Test(expected = RuntimeException.class)
	public void testContainsArticle1_invalidInput() {
		warehouse.containsArticle(null);
	}
	
	@Test
	public void testContainsArticle2_validInput() {
		warehouse = new WarehouseImpl(0, new AddressImpl());
		warehouse.containsArticle(0);
	}
	
	/*
	 * The input is invalid because the articleNumber is negative
	 */
	@Test(expected = RuntimeException.class)
	public void testContainsArticle2_invalidInput() {
		warehouse.containsArticle(-2);
	}
	
	@Test
	public void testUpdateArticle_validInput() {
		warehouse = new WarehouseImpl(0, new AddressImpl());
		Map<Integer, Article> articles = new HashMap<>();
		articles.put(0, new ArticleImpl(0, "Table", 0, 45, 0));
		warehouse.setArticlesMap(articles);
		warehouse.updateArticle(new ArticleImpl(0, "Chair", 0, 50, 0));
	}
	
	/*
	 * The input is invalid because the article is null
	 */
	@Test(expected = RuntimeException.class)
	public void testUpdateArticle_invalidInput() {
		warehouse.updateArticle(null);
	}
	
	@Test
	public void testSetCategoriesSet_validInput() {
		warehouse = new WarehouseImpl(0, new AddressImpl());
		warehouse.setCategoriesSet(new HashSet<>());;
	}
	
	/*
	 * The input is invalid because the category is null
	 */
	@Test(expected = RuntimeException.class)
	public void testSetCategoriesSet_invalidInput() {
		warehouse.setCategoriesSet(null);
	}
	
	@Test
	public void testInsertCategory_validInput() {
		warehouse = new WarehouseImpl(0, new AddressImpl());
		warehouse.insertCategory(new CategoryImpl());
	}
	
	/*
	 * The input is invalid because the category is null
	 */
	@Test(expected = RuntimeException.class)
	public void testInsertCategory_invalidInput() {
		warehouse.insertCategory(null);
	}
	
	@Test
	public void testDeleteCategory_validInput() {
		warehouse = new WarehouseImpl(0, new AddressImpl());
		warehouse.deleteCategory(new CategoryImpl());
	}
	
	/*
	 * The input is invalid because the category is null
	 */
	@Test(expected = RuntimeException.class)
	public void testDeleteCategory_invalidInput() {
		warehouse.deleteCategory(null);
	}
}
