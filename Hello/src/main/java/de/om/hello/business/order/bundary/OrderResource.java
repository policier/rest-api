package de.om.hello.business.order.bundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.resource.spi.IllegalStateException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@Path("orders")
public class OrderResource {
	@Inject
	OrderProcessor processor;
	
	@POST
	public void order(JsonObject order){
		try {
			String trackingNumber = order.getString("trakingNumber");
			processor.order(trackingNumber);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
