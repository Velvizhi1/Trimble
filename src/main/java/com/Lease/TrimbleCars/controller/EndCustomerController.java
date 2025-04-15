package com.Lease.TrimbleCars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Lease.TrimbleCars.model.History;
import com.Lease.TrimbleCars.service.CarService;
import com.Lease.TrimbleCars.service.CarService.CarDTO;
import com.Lease.TrimbleCars.service.HistoryService;
import com.Lease.TrimbleCars.service.UserService;

@RestController
public class EndCustomerController {

	//End Customer module
	
	@Autowired
	CarService carService;

	@Autowired
	UserService userService;
	
	@Autowired
	HistoryService historyService;
	
	// 1. Must be able to view the cars for lease | 4. See the list of Cars, Status
	@GetMapping("/customer/avail/cars")
	public List<CarDTO> getAvailaleCarsForLease() {
		return carService.getAvailableCarsForLease();
	}
	
	// 2. Start the lease with Car (max 2 lease at a time) | 
	@GetMapping("/customer/ActiveLease/count")
	public String countOfActiveLease(@RequestParam Long userId) {
		return "Total count of Active Lease is :   "+userService.getByid(userId).getCountOfActiveLease();
	}
	
	// 3.book car 
	@PostMapping("/customer/book/car")
	public String bookById(@RequestParam Long carId , @RequestBody History obj) {  
		// book the car by car ID | while filling form LeaseDetails should provide
		//  the cust id and name and role in hidden fields 
		
		return carService.BookByID(carId,obj);
	}
	
	// 5. Must be able to see their own history
	@GetMapping("/customer/my_history")
	public List<History> own_history(@RequestParam Long userId) {
		return historyService.LeaseHistory(userId);
	}
	
	
}
