package com.mayur.hibernatepractice.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "h_p_phone_number")
@Table(name = "h_p_phone_number")
public class PhoneNumber implements Serializable {

	@EmbeddedId
	private HPNumber number;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Customer customer;
	
	public PhoneNumber( ) {
		
	}
	
	public PhoneNumber(short countryCode, Long number, Customer customer) {
		this.number = new HPNumber(countryCode, number);
		this.customer = customer;
	}

	public HPNumber getNumber() {
		return number;
	}

	public void setNumber(HPNumber number) {
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	
	
	
}
