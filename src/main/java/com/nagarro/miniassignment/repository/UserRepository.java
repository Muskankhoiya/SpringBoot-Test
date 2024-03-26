package com.nagarro.miniassignment.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.miniassignment.models.UserData;



@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {

	

	
	 

	

}
