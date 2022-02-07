package torvalds.WarehouseManager.logic.tests;

import org.junit.Test;

import torvalds.WarehouseManager.logic.classes.ArticleImpl;
/*
 * @author Alissa, Pankraz, Zahra
 *
 */
public class ArticleTest {

	private ArticleImpl articleImpl;
	
	@Test
	public void testAddArticle_validInput() {
		articleImpl = new ArticleImpl(4545, "IPhone 11", 5, 1200.00, 0);
	}
		
	/*
	 * The input is invalid because ArticleNumber is negative. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testAddArticle_invalidInput1() {
		articleImpl = new ArticleImpl(-44, "IPhone 11", 5, 1200.00, 0);
	}
	
	/*
	 * The input is invalid because the Description is empty. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
    public void testAddArticle_invalidInput2() {
		articleImpl = new ArticleImpl(4545, "", 5, 1200.00, 0);
	}
	
	/*
	 * The input is invalid because the currentStock is zero. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
    public void testAddArticle_invalidInput3() {
		articleImpl = new ArticleImpl(4545, "IPhone 11", -1, 1200.00, 0);
	}

	/*
	 * The input is invalid because the gross price is zero. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
    public void testAddArticle_invalidInput4() {
		articleImpl = new ArticleImpl(4545, "IPhone 11", 5, -3, 0);
	}
	
	/*
	 * The input is valid. Expecting a new Description "IPhone 11"
	 */
	public void testSetDescription_validInput() {
		articleImpl.setDescription("IPhone 11");
	}
	
	/*
	 * The input is invalid because the Description is empty. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetDescription_invalidInput() {
		articleImpl.setDescription("");
	}
	
	/*
	 * The input is valid. Expecting a new CurrentStock 5
	 */
	public void testSetCurrentStock_validInput() {
		articleImpl.setCurrentStock(5);
	}

	/*
	 * The input is invalid because the CurrentStock is zero. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetCurrentStock_invalidInput() {
		articleImpl.setCurrentStock(-2);
	}

	/*
	 * The input is valid. Expecting a new Price 1200.00
	 */
	public void testSetPrice_validInput() {
		articleImpl.setGrossPrice(1200.00);
	}
	
	/*
	 * The input is invalid because the Price is zero. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetPrice_invalidInput() {
		articleImpl.setGrossPrice(-5);
	}
	
	public void testSetArticleNumber_validInput() {
		articleImpl.setArticleNumber(10);
	}
	
	/*
	 * The input is invalid because the articleNumber is negative. Expecting an IllegalArgumentException.
	 */
	@Test(expected = RuntimeException.class)
	public void testSetArticleNumber_invalidInput() {
		articleImpl.setArticleNumber(-5);
	}
}
