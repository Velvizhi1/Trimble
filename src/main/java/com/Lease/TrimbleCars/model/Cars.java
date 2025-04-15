package com.Lease.TrimbleCars.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cars 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long carId;
	
	private Long carOwnerId;   // owner can be Either Admin or Owner 
	private String carName;
	
	private String carStatus; // status can be Ideal/On Lease/On Service
	
	  @OneToMany(mappedBy = "car")
	  @JsonManagedReference
	  private List<History> histories;

	@Override
	public String toString() {
		return "Cars [carId=" + carId + ", carOwnerId=" + carOwnerId + ", carName=" + carName + ", carStatus="
				+ carStatus + "]";
	}

	  
	 
}
