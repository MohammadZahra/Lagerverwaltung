package torvalds.WarehouseManager.logic.hibernate;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import torvalds.WarehouseManager.logic.classes.CategoryImpl;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */

@Entity
@Table(name = "category")
@IdClass(CategoryPK.class) 
public class CategoryHibernateImpl extends CategoryImpl {

	private CategoryPK categoryPK;
	private Long categoryId;
	
	public CategoryHibernateImpl() {
		categoryPK = new CategoryPK();
	}
	
	public CategoryHibernateImpl(String categoryName, int warehouseNumber) {
		setCategoryName(categoryName);
		setWarehouseNumber(warehouseNumber);
		categoryPK = new CategoryPK();
		categoryPK.setCategoryId(categoryId);
		categoryPK.setWarehouseNumber(warehouseNumber);
	}

	@Column(name="categoryName", nullable = false)
	@Override
	public String getCategoryName() {
		return super.getCategoryName();
	}

	@Id 
	@Column(name="warehouseNumber", nullable = false)
	@Override
	public int getWarehouseNumber() {
		return super.getWarehouseNumber();
	}

	@Override
	public void setWarehouseNumber(int warehouseNumber) {
		super.setWarehouseNumber(warehouseNumber);
		if(categoryPK == null) {
			categoryPK = new CategoryPK();
		}
		categoryPK.setWarehouseNumber(warehouseNumber);
	}
	
	@Transient
	public CategoryPK getCategoryPK() {
		return categoryPK;
	}

	public void setCategoryPK(CategoryPK categoryPK) {
		this.categoryPK = categoryPK;
	}
	
	/**
	 * Returns the automatically generated id for this category
	 * 
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryId", nullable = false)
	public Long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
		if(categoryPK == null) {
			categoryPK = new CategoryPK();
		}
		this.categoryPK.setCategoryId(categoryId);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@OneToMany(mappedBy="category")
	public Set<ArticleHibernateImpl> getArticlesHibernateImplSet() {
		return (Set) super.getArticlesSet();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setArticlesHibernateImplSet(Set<ArticleHibernateImpl> articlesHibernateImplSet) {
		super.setArticlesSet((Set) articlesHibernateImplSet);
	}
}
