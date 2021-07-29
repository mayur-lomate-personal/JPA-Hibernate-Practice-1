package com.mayur.hibernatepractice.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mayur.hibernatepractice.entities.Aadhar;
import com.mayur.hibernatepractice.entities.Customer;
import com.mayur.hibernatepractice.entities.PhoneNumber;
import com.mayur.hibernatepractice.entities.Vehicle;
import com.mayur.hibernatepractice.repos.AadharRepo;
import com.mayur.hibernatepractice.repos.CustomerRepo;
import com.mayur.hibernatepractice.repos.PhoneNumberRepo;
import com.mayur.hibernatepractice.repos.VehicleRepo;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private AadharRepo aadharRepo;
	
	@Autowired
	private VehicleRepo vehicleRepo;
	
	@Autowired
	private PhoneNumberRepo phoneNumberRepo;

	public Long addCustomer(Customer customer) {
		customerRepo.save(customer);
		return customer.getCid();
	}
	
	@Transactional
	public Boolean addAadhar(Long cid, Aadhar aadhar) {
		Customer customer = customerRepo.findById(cid).orElse(null);
		if(customer == null) {
			return false;
		}
		customer.setAadhar(aadhar);
		customerRepo.save(customer);
		return true;
	}
	
	@Transactional
	public Boolean addCustomerVehicle(Long cid, Long vid) {
		Customer customer = customerRepo.findById(cid).orElse(null);
		if(customer == null) {
			return false;
		}
		Vehicle vehicle = vehicleRepo.findById(vid).orElse(null);
		if(vehicle == null) {
			return false;
		}
		customer.getVehicles().add(vehicle);
		customerRepo.save(customer);
		return true;
	}
	
	public Long addVehicle(Vehicle vehicle) {
		vehicleRepo.save(vehicle);
		return vehicle.getVid();
	}
	
	public Customer getCustomer(Long cid) {
		return customerRepo.findById(cid).orElse(null);
	}
	
	public Vehicle getVehicle(Long vid) {
		return vehicleRepo.findById(vid).orElse(null);
	}
	
	@Transactional
	public Boolean addPhoneNumber(Long cid, short countryCode, Long number) {
		Customer customer = customerRepo.findById(cid).orElse(null);
		if(customer == null) {
			return false;
		}
		PhoneNumber phoneNumber = new PhoneNumber(countryCode, number, customer);
		phoneNumberRepo.save(phoneNumber);
		return true;
	}
	
	@Transactional
	public Boolean removeCustomerVehicle(Long cid, Long vid) {
		Customer customer = customerRepo.findById(cid).orElse(null);
		Vehicle vehicle = vehicleRepo.findById(vid).orElse(null);
		if(customer == null || vehicle == null) {
			return false;
		}
		customer.getVehicles().remove(vehicle);
		customerRepo.save(customer);
		return true;
	}

	@Transactional
	public boolean removeCustomer(Long cid) {
		// TODO Auto-generated method stub
		Customer customer = customerRepo.findById(cid).orElse(null);
		System.out.println(customer);
		if(customer == null) {
			return false;
		}
		customerRepo.deleteById(cid);
		return true;
	}
	
	public List<Vehicle> getVehicles(int page) {
		Pageable sortedByPriceDescNameAsc = PageRequest.of(page, 3, Sort.by("companyName"));
		Page<Vehicle> vehicles = vehicleRepo.findAll(sortedByPriceDescNameAsc);
		return vehicles.toList();
	}
}
