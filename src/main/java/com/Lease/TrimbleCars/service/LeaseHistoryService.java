package com.Lease.TrimbleCars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Lease.TrimbleCars.model.History;
import com.Lease.TrimbleCars.repository.HistoryRepo;

@Service
public class LeaseHistoryService {

	@Autowired
	HistoryRepo historyRepo;

	
	public String createTransaction(History obj) {
		
		if(null!=historyRepo.save(obj)) 
		{
			return "the car has been booked sucessfully ";
		}
		return null;
	}
	
	
}
