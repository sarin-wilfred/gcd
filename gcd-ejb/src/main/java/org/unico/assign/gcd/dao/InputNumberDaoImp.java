package org.unico.assign.gcd.dao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.unico.assign.gcd.dao.exception.GcdServiceException;
import org.unico.assign.gcd.domain.InputNumber;

/**
 * @author Sarin
 * 
 */

@Stateless
public class InputNumberDaoImp implements InputNumberDao {

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
	 * This method will will be add 2 parameters to a the JMS queue.
	 * 
	 * @param num1
	 * @param num2
	 * @throws GcdServiceException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void push(Integer num1, Integer num2) throws GcdServiceException {
		InputNumber inputNumbers = new InputNumber();
		inputNumbers.setNum1(num1);
		inputNumbers.setNum2(num2);

		try {
			getEntityManager().persist(inputNumbers);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GcdServiceException(e.getMessage());
		}
	}

	/**
	 * This method will return a list of all the elements ever added to the
	 * queue from a database
	 * 
	 * @return Collection<InputNumber>
	 */
	public Collection<InputNumber> list() {
		Query query = getEntityManager().createQuery("SELECT i FROM InputNumber i");
		Collection<InputNumber> inputNumbers = (Collection<InputNumber>) query.getResultList();
		return inputNumbers;
	}

}
