package com.Lease.TrimbleCars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Lease.TrimbleCars.Myinterfaces.LeaseHistory;
import com.Lease.TrimbleCars.model.History;
import com.Lease.TrimbleCars.repository.HistoryRepo;

@Service
public class HistoryService implements LeaseHistory {

	@Autowired
	HistoryRepo historyRepo;

	@Override
	public List<History> LeaseHistory(Long CustId) {

		return historyRepo.findByLeaserId(CustId);
	}

}
