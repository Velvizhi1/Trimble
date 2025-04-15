package com.Lease.TrimbleCars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Lease.TrimbleCars.Myinterfaces.CarOperations;
import com.Lease.TrimbleCars.Myinterfaces.LeaseHistory;
import com.Lease.TrimbleCars.model.AppUsers;
import com.Lease.TrimbleCars.model.Cars;
import com.Lease.TrimbleCars.model.History;
import com.Lease.TrimbleCars.repository.CarRepo;

@Service
public class CarService implements CarOperations, LeaseHistory {

	@Autowired
	CarRepo carRepo;
	@Autowired
	LeaseHistoryService leaseHistoryService;
	@Autowired
	UserService userService;

	@Override
	public Cars AddingOrEnrollingCars(Cars car) {
		if (car.getCarStatus() == null) {
			car.setCarStatus("Ideal");
		}
		return carRepo.save(car);
	}

	@Override
	public List<Cars> carStatus(Long ownerId) {
		return carRepo.getBycarOwnerId(ownerId);
	}

	@Override
	public List<History> LeaseHistory(Long carId) {
		
		return carRepo.findById(carId).get().getHistories();
	}

	public String BookByID(Long carId, History leaseHistory) {  
		// this method used to book car for lease 
		
		
		Long userid=leaseHistory.getLeaserId();
		AppUsers user =userService.getByid(userid);
		Long activeLCount=user.getCountOfActiveLease();
		
		if (activeLCount<2) 
		{
		leaseHistory.setLeaserName(user.getName());
		leaseHistory.setLeaserrole(user.getRole());
		
		
		Cars car=carRepo.findById(carId).get();
		car.setCarStatus("On Lease");
		carRepo.save(car);  // updating the car 
		leaseHistory.setCar(car);
		
		activeLCount++;		
		user.setCountOfActiveLease(activeLCount);
		userService.addORupdateUser(user);
		
		
		return car.toString() + leaseHistoryService.createTransaction(leaseHistory);
		}
		else 
		{
			return "maximum booking limit is exceed";
		}
		
	}

	
	
	public List<CarDTO> getAvailableCarsForLease() {
		return carRepo.findBycarStatus("Ideal").stream().map(CarDTO::fromEntity).toList();
	}

	// DTO for share only car details instead of care lease history
	// here use dto

	public record CarDTO(Long carId, Long carOwnerId, String carName, String carStatus) {
		public static CarDTO fromEntity(Cars car) {
			return new CarDTO(car.getCarId(), car.getCarOwnerId(), car.getCarName(), car.getCarStatus());
		}
	}

}
