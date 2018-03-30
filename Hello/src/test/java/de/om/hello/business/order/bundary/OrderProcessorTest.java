package de.om.hello.business.order.bundary;

import javax.resource.spi.IllegalStateException;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.mockito.Mockito.*;

import de.om.hello.business.order.bundary.OrderProcessor;
import de.om.hello.business.order.control.LegacyAuthenticator;
import de.om.hello.business.order.control.OrderHistory;
import de.om.hello.business.order.control.PaymentProcessor;
import de.om.hello.business.order.entity.Order;

public class OrderProcessorTest {
	OrderProcessor cut;

	@Before
	public void init(){
		this.cut = new OrderProcessor();
		this.cut.payment = mock(PaymentProcessor.class);
		this.cut.authenticator = mock(LegacyAuthenticator.class);
		this.cut.history = mock(OrderHistory.class);
	}
	
	@Test
	public void successfulOrder() throws IllegalStateException{
		when(this.cut.authenticator.authenticate()).thenReturn(true);
		when(this.cut.authenticator.authenticate()).thenReturn(true);
		String expected = "42";
		this.cut.order(expected);  
		verify(this.cut.payment, times(1)).pay();
		verify(this.cut.history).save(Matchers.argThat(new BaseMatcher<Order>() {

			@Override
			public boolean matches(Object item) {
				if(!(item instanceof Order)){
					return false;
				}
				Order order =(Order)item;
				return expected.equalsIgnoreCase(order.getTrakingNumber());
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Should be:" +expected);
			}
		}));
	}
	
	@Test(expected = IllegalStateException.class)
	public void invalidUser() throws IllegalStateException{
		when(this.cut.authenticator.authenticate()).thenReturn(false);
		this.cut.order("42"); 
	}  
	
	@After  
	public void destroy(){
		
	}
}
