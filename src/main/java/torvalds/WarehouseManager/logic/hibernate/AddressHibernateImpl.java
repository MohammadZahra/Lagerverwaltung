package torvalds.WarehouseManager.logic.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import torvalds.WarehouseManager.logic.classes.AddressImpl;

/**
 * @author Taraq Saeed, Al Mostafa
 *
 */
@Entity
@Table(name = "address")
public class AddressHibernateImpl extends AddressImpl {

	public AddressHibernateImpl() {
		super();
	}

	public AddressHibernateImpl(String street, String number, String city, String postcode, String country) {
		super(street, number, city, postcode, country);
	}

    private Long id;

	
	@Override
	@Column(name = "street", nullable = false)
	public String getStreet() {
		return super.getStreet();
	}
	
	@Column(name = "number", nullable = false)
	@Override
	public String getNumber() {
		return super.getNumber();
	}
	
	@Column(name = "city", nullable = false)
	@Override
	public String getCity() {
		return super.getCity();
	}
	
	@Column(name = "postcode", nullable = false)
	@Override
	public String getPostcode() {
		return super.getPostcode();
	}
	
	@Column(name = "country", nullable = false)
	@Override
	public String getCountry() {
		return super.getCountry();
	}
	
	/**
	 * Returns the automatically generated id for this address
	 * 
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
