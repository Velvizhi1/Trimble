package com.Lease.TrimbleCars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Lease.TrimbleCars.Myinterfaces.userOperations;
import com.Lease.TrimbleCars.model.AppUsers;
import com.Lease.TrimbleCars.repository.UserRepo;

@Service
public class UserService implements userOperations{
	
	@Autowired
	private UserRepo userRepo;	

	@Override
	public AppUsers addORupdateUser(AppUsers user) {
		if(user.getCountOfActiveLease()==null||user.getCountOfActiveLease()==0L) {
			user.setCountOfActiveLease(0L);
		}
		return userRepo.save(user);
	}
	
	@Override
	public void deleteUser(AppUsers user) {
		userRepo.delete(user);
	}

	public AppUsers getByid(Long uid) {
		// TODO Auto-generated method stub
		return userRepo.findById(uid).get();
	}



	
}
