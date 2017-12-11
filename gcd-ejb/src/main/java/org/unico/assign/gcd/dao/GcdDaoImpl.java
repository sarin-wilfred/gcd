package org.unico.assign.gcd.dao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.unico.assign.gcd.dao.exception.GcdServiceException;
import org.unico.assign.gcd.domain.Gcd;

/**
 * 
 * @author Sarin
 *
 */

@Stateless
public class GcdDaoImpl implements GcdDao {

	@PersistenceContext(unitName = "unico.persistenceContext")
	EntityManager entityManager;

	/**
	 * 
	 * @return entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * This method will get the sum of gcd's saved
	 * 
	 * @return Integer
	 */
	@Override
	public Integer gcdSum() {
		Query query = getEntityManager().createQuery("SELECT sum(g.calculatedGcd) FROM Gcd g");
		Long gcdSumVal = (Long) query.getSingleResult();
		return gcdSumVal.intValue();
	}

	/**
	 * This method will add gcd's to DB
	 * 
	 * @param gcdValue
	 * @throws GcdServiceException
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveGcd(Integer gcdValue) throws GcdServiceException {
		Gcd gcd = new Gcd();
		gcd.setCalculatedGcd(gcdValue);
		
		try {
			getEntityManager().persist(gcd);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GcdServiceException(e.getMessage());
		}
	}

	/**
	 * This method will list the gcd's from DB
	 * 
	 * @return Collection<Gcd>
	 */
	@Override
	public Collection<Gcd> gcdList() {
		Query query = getEntityManager().createQuery("SELECT g FROM Gcd g");
		Collection<Gcd> gcds = (Collection<Gcd>) query.getResultList();
		return gcds;
	}

}
