package com.mayur.hibernatepractice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayur.hibernatepractice.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
