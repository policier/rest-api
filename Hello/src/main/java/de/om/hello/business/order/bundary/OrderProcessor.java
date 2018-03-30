package de.om.hello.business.order.bundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.resource.spi.IllegalStateException;

import de.om.hello.business.order.control.LegacyAuthenticator;
import de.om.hello.business.order.control.OrderHistory;
import de.om.hello.business.order.control.PaymentProcessor;
import de.om.hello.business.order.entity.Order;

@Stateless
public class OrderProcessor {
	@Inject
	LegacyAuthenticator authenticator; 
	@Inject
	PaymentProcessor payment;  
	@Inject
	OrderHistory history;
	public void order(String ordeID) throws IllegalStateException{
		if(!this.authenticator.authenticate()){
			throw new IllegalStateException("not authenticate");
		} 
		Order newOrder = new  Order(ordeID);
		this.payment.pay();
		this.history.save(newOrder);
	}
	
	
	public String getHello() {
		String name  = "Owen";
		String name2  = "Owen";  
		String name3  = "Owen";
		String result = name + name2+ name3;
		System.out.println(result);
		return result;
	}
}
