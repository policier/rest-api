package de.om.hello.business.order;

import org.junit.Rule;
import org.junit.Test;

import de.om.hello.business.order.entity.Order;

public class OrderIT {
	@Rule
	public EntityManagerProvider provider = EntityManagerProvider.withUnit("it");
	
	@Test
	public void verifyMappings(){
		provider.begin();
		provider.getEm().merge(new Order("42"));
		provider.commit();
		
	}

}  
