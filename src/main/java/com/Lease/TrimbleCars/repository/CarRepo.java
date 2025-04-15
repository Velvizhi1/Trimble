package com.Lease.TrimbleCars.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Lease.TrimbleCars.model.Cars;

@Repository
public interface CarRepo extends JpaRepository<Cars, Long> {

	
	List<Cars> getBycarOwnerId(Long ownerId);

	List<Cars> findBycarStatus(String status);

}
