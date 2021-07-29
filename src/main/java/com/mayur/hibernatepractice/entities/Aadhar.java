package com.mayur.hibernatepractice.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "hibernate_practice_aadhar")
@Table(name = "hibernate_practice_aadhar")
public class Aadhar implements Serializable {

	@Id
	private Long aid;
	private String fullName;
	
	@JsonIgnore
	@OneToOne(mappedBy = "aadhar")
	private Customer customer;

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Aadhar [aid=" + aid + ", fullName=" + fullName + ", customer=" + customer + "]";
	}
	
}
