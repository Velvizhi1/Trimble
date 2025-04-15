package com.Lease.TrimbleCars.Myinterfaces;

import java.util.List;

import com.Lease.TrimbleCars.model.History;

public interface LeaseHistory {

	List<History> LeaseHistory(Long carIdOrCustId);

}
