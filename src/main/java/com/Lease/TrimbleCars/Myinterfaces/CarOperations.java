package com.Lease.TrimbleCars.Myinterfaces;

import java.util.List;

import com.Lease.TrimbleCars.model.Cars;
import com.Lease.TrimbleCars.model.History;

public interface CarOperations {

	Cars AddingOrEnrollingCars( Cars car);
	List<Cars> carStatus(Long ownerId);

}
