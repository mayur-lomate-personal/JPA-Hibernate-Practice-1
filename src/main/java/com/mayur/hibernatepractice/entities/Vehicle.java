package com.mayur.hibernatepractice.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "hibernate_practice_vehicle")
@Table(name = "hibernate_practice_vehicle")
@SequenceGenerator(name="hibernate_practice_vehicle_pk", initialValue=1, allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Vehicle implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_practice_vehicle_pk")
	private Long vid;
	private String vehicleName;
	private String companyName;
	public Vehicle(String vehicleName, String companyName) {
		super();
		this.vehicleName = vehicleName;
		this.companyName = companyName;
	}
	
	@JsonIgnore
	@ManyToMany(mappedBy = "vehicles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Customer> customers = new ArrayList<Customer>();
	
	public Vehicle( ) {
		
	}

	public Long getVid() {
		return vid;
	}

	public void setVid(Long vid) {
		this.vid = vid;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vid == null) ? 0 : vid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (vid == null) {
			if (other.vid != null)
				return false;
		} else if (!vid.equals(other.vid))
			return false;
		return true;
	}
	
	
}
