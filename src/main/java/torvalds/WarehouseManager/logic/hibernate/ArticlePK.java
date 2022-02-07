package torvalds.WarehouseManager.logic.hibernate;

import java.io.Serializable;

/**
 * ArticlePK = Article Primary Key 
 * 
 * @author Al Mostafa
 */
public class ArticlePK implements Serializable {
	
	/**
	 * Auto Generated 
	 */
	private static final long serialVersionUID = -5457488183938157400L;
	private int articleNumber;
	private int warehouseNumber;
	
	public int getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}
	public int getWarehouseNumber() {
		return warehouseNumber;
	}
	public void setWarehouseNumber(int warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleNumber;
		result = prime * result + warehouseNumber;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticlePK other = (ArticlePK) obj;
		if (articleNumber != other.articleNumber)
			return false;
		if (warehouseNumber != other.warehouseNumber)
			return false;
		return true;
	}
}
