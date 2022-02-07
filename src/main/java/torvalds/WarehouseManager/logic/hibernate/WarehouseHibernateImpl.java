package torvalds.WarehouseManager.logic.hibernate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import torvalds.WarehouseManager.logic.classes.WarehouseImpl;
import torvalds.WarehouseManager.logic.interfaces.Address;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
@Entity
@Table(name="warehouse")
public class WarehouseHibernateImpl extends WarehouseImpl {
	
	private Map<Integer, ArticleHibernateImpl> articlesHibernateImpl;
	@SuppressWarnings("unused")
	private Set<CategoryHibernateImpl> categoriesHibernateImpl;
	
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	private WarehouseHibernateImpl() {
		super();
		articlesHibernateImpl = (Map) super.getArticlesMap();
		categoriesHibernateImpl = (Set) super.getCategoriesSet();
	}
	
	public WarehouseHibernateImpl(int warehouseNumber, Address address) {
		super(warehouseNumber, address);
	}

	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "warehouseNumber")
	@MapKey(name = "articleNumber")
	public Map<Integer, ArticleHibernateImpl> getArticlesHibernateImplMap() {
		return articlesHibernateImpl;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setArticlesHibernateImplMap(Map<Integer, ArticleHibernateImpl> articles) {
		if(articles != null) {
			this.articlesHibernateImpl = articles;
			super.setArticlesMap((Map) articles);
		} else {
			this.articlesHibernateImpl  = new LinkedHashMap<>();
			super.setArticlesMap((Map) articlesHibernateImpl);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "warehouseNumber")
	public Set<CategoryHibernateImpl> getCategoriesHibernateImplSet() {
		return (Set) super.getCategoriesSet();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCategoriesHibernateImplSet(Set<CategoryHibernateImpl> categories) {
		this.categoriesHibernateImpl = categories;
		super.setCategoriesSet((Set) categories);
		
	}
	
	@Id
	@Column(name="warehouseNumber", nullable = false)
	@Override
	public int getWarehouseNumber() {
		return super.getWarehouseNumber();
	}

	@OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "addressId", referencedColumnName = "id", nullable = false)
	@Override
	public AddressHibernateImpl getAddress() {
		return (AddressHibernateImpl) super.getAddress();
	}
}
