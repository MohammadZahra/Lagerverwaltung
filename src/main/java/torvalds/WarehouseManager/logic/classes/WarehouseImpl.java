package torvalds.WarehouseManager.logic.classes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import torvalds.WarehouseManager.logic.interfaces.Address;
import torvalds.WarehouseManager.logic.interfaces.Article;
import torvalds.WarehouseManager.logic.interfaces.Category;
import torvalds.WarehouseManager.logic.interfaces.Warehouse;
import torvalds.WarehouseManager.utils.ExceptionHandlingUtil;

/**
 * @author Alissa, Pankraz, Zahra, Al Mostafa
 *
 */
public class WarehouseImpl implements Warehouse {
	private static final String WAREHOUSENUMBER_NEGATIVE = "You need to put a positive warehousenumber";
	private static final String ADDRESS_EMPTY = "You need to put in an address";
	private static final String ARTICLE_NULL = "The given Article shouldn't be null";
	private static final String ARTICLE_ALREADY_INSERTED = "The given Article was previosly inserted";
	private static final String ARTICLE_NOT_FOUND = "The given Article cannot be found";
	private static final String CATEGORY_NULL = "The given Category shouldn't be null";
	private static final String ARTICLES_MAP_NULL = "The Articles-Map must not be null";

	private int warehouseNumber;
	private Address address;
	private Map<Integer, Article> articles;
	private Set<Category> categories;

	public WarehouseImpl() {

	}

	public WarehouseImpl(int warehouseNumber, Address address) {
		setWarehouseNumber(warehouseNumber);
		setAddress(address);
		articles = new LinkedHashMap<>();
		categories = new LinkedHashSet<>();
	}

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public void setAddress(Address address) {
		ExceptionHandlingUtil.check(address != null, ADDRESS_EMPTY);
		this.address = address;
	}

	@Override
	public int getWarehouseNumber() {
		return warehouseNumber;
	}

	@Override
	public void setWarehouseNumber(int warehouseNumber) {
		ExceptionHandlingUtil.check(warehouseNumber >= 0, WAREHOUSENUMBER_NEGATIVE);
		this.warehouseNumber = warehouseNumber;
	}

	@Override
	public Collection<Article> getArticlesCollection() {
		return articles.values();
	}

	@Override
	public Map<Integer, Article> getArticlesMap() {
		return articles;
	}

	@Override
	public void setArticlesMap(Map<Integer, Article> articles) {
		ExceptionHandlingUtil.check(articles != null, ARTICLES_MAP_NULL);
		this.articles = articles;
	}

	@Override
	public void insertArticle(Article article) {
		ExceptionHandlingUtil.check(article != null, ARTICLE_NULL);
		ExceptionHandlingUtil.check(!articles.containsKey(article.getArticleNumber()), ARTICLE_ALREADY_INSERTED);
		articles.put(article.getArticleNumber(), article);
	}

	@Override
	public void deleteArticle(Article article) {
		ExceptionHandlingUtil.check(article != null, ARTICLE_NULL);
		ExceptionHandlingUtil.check(containsArticle(article), ARTICLE_NOT_FOUND);
		articles.remove(article.getArticleNumber());
	}

	@Override
	public boolean containsArticle(Article article) {
		if (article != null) {
			return containsArticle(article.getArticleNumber());
		} else {
			return false;
		}
	}

	@Override
	public boolean containsArticle(int articleNumber) {
		return articles.containsKey(articleNumber);
	}

	@Override
	public void updateArticle(Article updatedArticle) {
		ExceptionHandlingUtil.check(updatedArticle != null, ARTICLE_NULL);
		ExceptionHandlingUtil.check(containsArticle(updatedArticle), ARTICLE_NOT_FOUND);
		Article oldArticle = articles.get(updatedArticle.getArticleNumber());
		oldArticle.setCurrentStock(updatedArticle.getCurrentStock());
		oldArticle.setDescription(updatedArticle.getDescription());
		oldArticle.setGrossPrice(updatedArticle.getGrossPrice());
		oldArticle.setWarehouseNumber(updatedArticle.getWarehouseNumber());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((articles == null) ? 0 : articles.hashCode());
		result = prime * result + warehouseNumber;
		return result;
	}

	/*
	 * compares two warehouses. are they equal?
	 * 
	 * param object return true/false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WarehouseImpl other = (WarehouseImpl) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (articles == null) {
			if (other.articles != null)
				return false;
		} else if (!articles.equals(other.articles))
			return false;
		if (warehouseNumber != other.warehouseNumber)
			return false;
		return true;
	}

	@Override
	public Collection<Category> getCategoriesCollection() {		
		return categories;
	}

	@Override
	public Set<Category> getCategoriesSet() {
		return categories;
	}

	@Override
	public void setCategoriesSet(Set<Category> categories) {
		ExceptionHandlingUtil.check(categories != null, CATEGORY_NULL);
		this.categories=categories; 		
	}

	@Override
	public void insertCategory(Category category) {
		ExceptionHandlingUtil.check(category != null, CATEGORY_NULL);
		categories.add(category); 
		
	}

	@Override
	public void deleteCategory(Category category) {
		ExceptionHandlingUtil.check(category != null, CATEGORY_NULL);
		categories.remove(category); 		
	}

	@Override
	public List<Article> filter(Predicate<Article> predicate) {
		return articles.values().parallelStream().filter(predicate).collect(Collectors.toList());
	}
}