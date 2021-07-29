package com.mayur.hibernatepractice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayur.hibernatepractice.entities.Vehicle;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long>{

}
