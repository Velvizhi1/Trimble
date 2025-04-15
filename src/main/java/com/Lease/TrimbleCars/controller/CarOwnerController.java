package com.Lease.TrimbleCars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Lease.TrimbleCars.model.Cars;
import com.Lease.TrimbleCars.service.CarService;
//  Car Owner -> requirment as per document
@RestController
public class CarOwnerController {

	@Autowired
	CarService carService;
	
	//  1. Must be able to register and enroll the car
	@PostMapping("/enroll/car")
	public Cars CarEnrollmentByOwner(@RequestBody Cars car) {
		return carService.AddingOrEnrollingCars(car);
	}	
	
	
	// 2. Must be able to see the car current status & details (Ideal/On Lease/On Service)
	@GetMapping("/car/status")
	public List<Cars> getCarByOwner(@RequestParam Long OwnerId) {
		return carService.carStatus(OwnerId);
	}
	
	
	// 3. Must be able to see the lease history
	@GetMapping("/car/LeaseHistory")
	public List<Cars> getCarLeaseHistory(@RequestParam Long carid) {
		return carService.carStatus(carid);
	}
	
	
	
}
