package com.mayur.hibernatepractice.controllers;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mayur.hibernatepractice.entities.Aadhar;
import com.mayur.hibernatepractice.entities.Customer;
import com.mayur.hibernatepractice.entities.PhoneNumber;
import com.mayur.hibernatepractice.entities.Vehicle;
import com.mayur.hibernatepractice.services.CustomerService;
import com.mayur.hibernatepractice.services.ResponseEntityService;

@RestController
public class MainController {
	
	@Autowired
	CustomerService customerService;

	@PostMapping("/add/customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return ResponseEntityService.getResponseEntity(customer, "Customer added Succesfully", HttpStatus.OK.value());
	}
	
	@PostMapping("/add/customer/aadhar/{cid}")
	public ResponseEntity<?> addAadhar(@RequestBody Aadhar aadhar, @PathVariable Long cid) {
		if(!customerService.addAadhar(cid, aadhar)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with customer id " + cid + " not Found.");
		}
		return ResponseEntityService.getResponseEntity(aadhar, "Aadhar added Succesfully", HttpStatus.OK.value());
	}
	
	@PostMapping("/add/vehicle")
	public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle) {
		customerService.addVehicle(vehicle);
		return ResponseEntityService.getResponseEntity(vehicle, "Vehicle added Succesfully", HttpStatus.OK.value());
	}
	
	@PostMapping("/add/customer/vehicle/{cid}/{vid}")
	public ResponseEntity<?> addVehicle(@PathVariable Long cid, @PathVariable Long vid) {
		if(!customerService.addCustomerVehicle(cid, vid)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with customer id " + cid + " not Found.");
		}
		return ResponseEntityService.getResponseEntity(null, "Customer's vehicle added Succesfully", HttpStatus.OK.value());
	}
	
	@PostMapping("/add/customer/phonenumber/{cid}")
	public ResponseEntity<?> addPhoneNumber(@PathVariable Long cid, @RequestBody HashMap<String, String> values) {
		if(!customerService.addPhoneNumber(cid, Short.parseShort(values.get("country-code")), Long.parseLong(values.get("number")))) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with customer id " + cid + " not Found."); 
		}
		return ResponseEntityService.getResponseEntity(null, "Customer's Phone Number added Succesfully", HttpStatus.OK.value());
	}
	
	@GetMapping("/get/customer/{cid}")
	public ResponseEntity<?> getCustomer(@PathVariable Long cid) {
		Customer customer = customerService.getCustomer(cid);
		if(customer == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with customer id " + cid + " not Found.");
		}
		return ResponseEntityService.getResponseEntity(customer, "Customer data Returning Succesfully", HttpStatus.FOUND.value());
	}
	
	@GetMapping("/get/vehicle/{vid}")
	public ResponseEntity<?> getVehicle(@PathVariable Long vid) {
		Vehicle vehicle = customerService.getVehicle(vid);
		if(vehicle == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle with vehicle id " + vid + " not Found.");
		}
		return ResponseEntityService.getResponseEntity(vehicle, "Customer data Returning Succesfully", HttpStatus.FOUND.value());
	}
	
	@GetMapping("/get/vehicles/{page}")
	public ResponseEntity<?> getVehicle(@PathVariable int page) {
		List<Vehicle> vehicles = customerService.getVehicles(page);
		/*if(vehicle == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle with vehicle id " + vid + " not Found.");
		}*/
		return ResponseEntityService.getResponseEntity(vehicles, "Customer data Returning Succesfully", HttpStatus.FOUND.value());
	}
	
	@DeleteMapping("/delete/customer/vehicle/{cid}/{vid}")
	public ResponseEntity<?> deleteCustomerVehicle(@PathVariable Long cid, @PathVariable Long vid) {
		if(!customerService.removeCustomerVehicle(cid, vid)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with customer id " + cid + " not Found or vehicle attached with him not found");
		}
		Customer customer = customerService.getCustomer(cid);
		return ResponseEntityService.getResponseEntity(customer, "Customer's vehicle deleted Succesfully", HttpStatus.FOUND.value());
	}
	
	@DeleteMapping("/delete/customer/{cid}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long cid) {
		if(!customerService.removeCustomer(cid)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with customer id " + cid + " not Found.");
		}
		Customer customer = customerService.getCustomer(cid);
		return ResponseEntityService.getResponseEntity(customer, "Customer deleted Succesfully", HttpStatus.FOUND.value());
	}
}
