package torvalds.WarehouseManager.logic.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import torvalds.WarehouseManager.logic.classes.ArticleImpl;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
@Entity
@Table(name="article")
@IdClass(ArticlePK.class)
public class ArticleHibernateImpl extends ArticleImpl {

	private ArticlePK articlePK;
	
	public ArticleHibernateImpl(int articleNumber, String description, int currentStock, double grossPrice, int warehouseNumber){
		super(articleNumber, description, currentStock, grossPrice, warehouseNumber);
		articlePK = new ArticlePK();
		articlePK.setArticleNumber(articleNumber);
		articlePK.setWarehouseNumber(warehouseNumber);
	}
	
	public ArticleHibernateImpl() {
		super();
		articlePK = new ArticlePK();
	}
	
	@Transient
	public ArticlePK getArticlePK() {
		return articlePK;
	}
	
	public void setArticlePK(ArticlePK articlePK) {
		this.articlePK = articlePK;
	}
	
	@Override
	public void setArticleNumber(int articleNumber) {
		super.setArticleNumber(articleNumber);
		if(articlePK == null) {
			articlePK = new ArticlePK();
		}
		articlePK.setArticleNumber(articleNumber);
	}
	
	@Override
	public void setWarehouseNumber(int warehouseNumber) {
		super.setWarehouseNumber(warehouseNumber);
		if(articlePK == null) {
			articlePK = new ArticlePK();
		}
		articlePK.setWarehouseNumber(warehouseNumber);
	}
	
	@Id
	@Column(name="articleNumber", nullable = false)
	@Override
	public int getArticleNumber() {
		return super.getArticleNumber();
	}
	
	@Column(name="description", nullable = false)
	@Override
	public String getDescription() {
		return super.getDescription();
	}
	
	@Column(name="currentStock", nullable = false)
	@Override
	public int getCurrentStock() {
		return super.getCurrentStock();
	}
	
	@Column(name="grossPrice", nullable = false)
	@Override
	public double getGrossPrice() {
		return super.getGrossPrice();
	}
	
	@Id
	@Column(name="warehouseNumber", nullable = false)
	@Override
	public int getWarehouseNumber() {
		return super.getWarehouseNumber();
	}
	
	@ManyToOne
    @JoinColumns({
    	@JoinColumn(name="categoryId", referencedColumnName = "categoryId"),
    	@JoinColumn(name="categoryWarehouseNumber", referencedColumnName = "warehouseNumber")
    })
	@Override
	public CategoryHibernateImpl getCategory() {
		return (CategoryHibernateImpl) super.getCategory();
	}
}
