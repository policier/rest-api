package de.om.hello.business.order.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name ="T_ORDER")
@Entity
public class Order {
	@Id
	@GeneratedValue
	private long id;
	
	private String trakingNumber;

	public Order(String trakingNumber) {
		this.trakingNumber = trakingNumber;
	}
	
	public Order(){
		
	}

	public String getTrakingNumber() {
		return trakingNumber;
	}

	public void setTrakingNumber(String trakingNumber) {
		this.trakingNumber = trakingNumber;
	}
	
	
}   
