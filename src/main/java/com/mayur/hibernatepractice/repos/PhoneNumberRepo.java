package com.mayur.hibernatepractice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayur.hibernatepractice.entities.HPNumber;
import com.mayur.hibernatepractice.entities.PhoneNumber;

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumber, HPNumber> {

}
