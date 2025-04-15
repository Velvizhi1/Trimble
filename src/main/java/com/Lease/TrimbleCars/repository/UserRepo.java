package com.Lease.TrimbleCars.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Lease.TrimbleCars.model.AppUsers;

@Repository
public interface UserRepo extends JpaRepository<AppUsers, Long> {

}
