package com.mayur.hibernatepractice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayur.hibernatepractice.entities.Aadhar;

@Repository
public interface AadharRepo extends JpaRepository<Aadhar, Long>{

}
