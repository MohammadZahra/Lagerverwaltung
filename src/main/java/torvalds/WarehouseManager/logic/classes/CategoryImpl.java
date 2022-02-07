package torvalds.WarehouseManager.logic.classes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import torvalds.WarehouseManager.logic.interfaces.Article;
import torvalds.WarehouseManager.logic.interfaces.Category;
import torvalds.WarehouseManager.utils.ExceptionHandlingUtil;

/**
 * @author Taraq Saeed, Al Mostafa, Pankraz
 *
 */

public class CategoryImpl implements Category {

	private String categoryName;
	private int warehouseNumber;
	private Set<Article> articlesSet;

	private static final String CATEGORY_NAME_EMPTY = "You need to put a non empty category name";
	private static final String WAREHOUSE_NUMBER_NEGATIVE = "You need to put a number >= 0";

	public CategoryImpl(String categoryName, int warehouseNumber) {
		this();
		setCategoryName(categoryName);
		setWarehouseNumber(warehouseNumber);
	}

	public CategoryImpl() {
		setArticlesSet(null);
	}

	@Override
	public void setCategoryName(String categoryName) {
		ExceptionHandlingUtil.check(categoryName != null && !categoryName.isBlank(), CATEGORY_NAME_EMPTY);
		this.categoryName = categoryName;
	}

	@Override
	public String getCategoryName() {
		return categoryName;
	}

	@Override
	public int getWarehouseNumber() {
		return warehouseNumber;
	}

	@Override
	public void setWarehouseNumber(int warehouseNumber) {
		ExceptionHandlingUtil.check(warehouseNumber >= 0, WAREHOUSE_NUMBER_NEGATIVE);
		this.warehouseNumber = warehouseNumber;
	}

	@Override
	public Collection<Article> getArticlesCollection() {
		return articlesSet;
	}

	@Override
	public Set<Article> getArticlesSet() {
		return articlesSet;
	}

	@Override
	public void setArticlesSet(Set<Article> articlesSet) {
		if(articlesSet == null) {
			this.articlesSet = new HashSet<>();
		} else {
			this.articlesSet = articlesSet;
		}
	}

}
