package de.om.hello.business.order.control;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.om.hello.business.order.entity.Order;

public class OrderHistory {
	
	@PersistenceContext(unitName = "acaPlcTest")
	EntityManager em;
	
	public void save(Order  order){
		em.merge(order);
	}
}
