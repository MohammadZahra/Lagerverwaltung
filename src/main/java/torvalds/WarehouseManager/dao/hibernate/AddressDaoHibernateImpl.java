package torvalds.WarehouseManager.dao.hibernate;

import javax.persistence.EntityManager;

import torvalds.WarehouseManager.dao.interfaces.AddressDao;
import torvalds.WarehouseManager.logic.hibernate.AddressHibernateImpl;
import torvalds.WarehouseManager.logic.interfaces.Address;
import torvalds.WarehouseManager.utils.EntityManagerUtil;

/**
 * The Hibernate Implementation of AddressDao
 * 
 * @author Al Mostafa
 *
 */
public class AddressDaoHibernateImpl implements AddressDao {

	@Override
	public Address insertAddress(final Address address) {
		EntityManager entityManager = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(address);
			entityManager.getTransaction().commit();
			/* OLBERTZ Es ist immer eine schlechte Idee die Oberklasse Exception abzufangen, weil man damit jede
			 * Exception erwischt - auch diejenigen, die man gar nicht erwischen moechte. Beim catch sollte man
			 * so speziell wie moeglich werden.
			 */
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		return address;
	}

	@Override
	public Address deleteAddress(final Address address) {
		EntityManager entityManager = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(address);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		return address;
	}

	@Override
	public Address updateAddress(final Address updatedAddress) {
		EntityManager entityManager = null;
		AddressHibernateImpl updatedAddressHibernateImpl = (AddressHibernateImpl) updatedAddress;
		Address oldAddress = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			oldAddress = entityManager.find(AddressHibernateImpl.class, updatedAddressHibernateImpl.getId());
			if(oldAddress != null) {
				oldAddress.setCity(updatedAddress.getCity());
				oldAddress.setCountry(updatedAddress.getCountry());
				oldAddress.setNumber(updatedAddress.getNumber());
				oldAddress.setPostcode(updatedAddress.getPostcode());
				oldAddress.setStreet(updatedAddress.getStreet());
				entityManager.merge(oldAddress);
			} else {
				throw new IllegalArgumentException("The given Address does not exist: #"
							+ updatedAddressHibernateImpl.getId());
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		return oldAddress;
	}
}
