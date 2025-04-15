package com.Lease.TrimbleCars.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Lease.TrimbleCars.model.History;

@Repository
public interface HistoryRepo extends JpaRepository<History, Long> {

	@Query("SELECT h FROM History h WHERE h.LeaserId = :leaserId")
	List<History> findByLeaserId(@Param("leaserId") Long leaserId);

}
