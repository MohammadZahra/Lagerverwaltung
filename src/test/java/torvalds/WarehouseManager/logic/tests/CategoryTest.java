package torvalds.WarehouseManager.logic.tests;

import java.util.HashSet;

import org.junit.Test;

import torvalds.WarehouseManager.logic.classes.CategoryImpl;

/*
 * @author Pankraz
 * 
 */
public class CategoryTest {

	private CategoryImpl categoryImpl;
	
	@Test
	public void testAddCategory_validInput() {
		categoryImpl = new CategoryImpl("Computers", 0);
	}
	
	/*
	 * The input is invalid because the categoryName is blank
	 */
	@Test(expected = RuntimeException.class)
	public void testAddCategory_invalidInput1() {
		categoryImpl = new CategoryImpl("", 0);
	}
	
	/*
	 * The input is invalid because the warehouseNumber is negative
	 */
	@Test(expected = RuntimeException.class)
	public void testAddCategory_invalidInput2() {
		categoryImpl = new CategoryImpl("Computers", -4);
	}
	
	@Test
	public void testSetCategoryName_validInput() {
		categoryImpl = new CategoryImpl();
		categoryImpl.setCategoryName("Chairs");
	}

	/*
	 * The input is invalid because the categoryName is blank
	 */
	@Test(expected = RuntimeException.class)
	public void testSetCategoryName_invalidInput() {
		categoryImpl.setCategoryName("");
	}
	
	@Test
	public void testSetWarehouseNumber_validInput() {
		categoryImpl = new CategoryImpl();
		categoryImpl.setWarehouseNumber(2);
	}

	/*
	 * The input is invalid because the warehouseNumber is negative
	 */
	@Test(expected = RuntimeException.class)
	public void testSetWarehouseNumber_invalidInput() {
		categoryImpl.setWarehouseNumber(-5);
	}
	
	@Test
	public void testSetArticlesSet_validInput1() {
		categoryImpl = new CategoryImpl();
		categoryImpl.setArticlesSet(null);
	}
	
	@Test
	public void testSetArticlesSet_validInput2() {
		categoryImpl = new CategoryImpl();
		categoryImpl.setArticlesSet(new HashSet<>());
	}
}
