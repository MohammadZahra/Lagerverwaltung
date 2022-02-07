package torvalds.WarehouseManager.logic.hibernate;

import java.io.Serializable;
/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
public class CategoryPK implements Serializable {
	private static final long serialVersionUID = -3046614386905744280L;
	
	private Long categoryId;
	private int warehouseNumber; 
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
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
		CategoryPK other = (CategoryPK) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (warehouseNumber != other.warehouseNumber)
			return false;
		return true;
	}

}
