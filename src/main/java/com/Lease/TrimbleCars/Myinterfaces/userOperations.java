package com.Lease.TrimbleCars.Myinterfaces;

import com.Lease.TrimbleCars.model.AppUsers;

public interface userOperations {

	AppUsers addORupdateUser(AppUsers user);
	void deleteUser(AppUsers user);
}
