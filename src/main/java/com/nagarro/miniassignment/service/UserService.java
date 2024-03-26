package com.nagarro.miniassignment.service;




import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.miniassignment.models.UserData;

import com.nagarro.miniassignment.repository.UserRepository;


@Service
public class UserService {
	 
	@Autowired
	private  UserRepository userRepository;
	    

	    public void saveUserData(UserData user) {
	        userRepository.save(user);
	    }



		public List<UserData> saveAll(List<UserData> userList) {
			// TODO Auto-generated method stub
			return null;
		}




		public long getTotalUsersCount() {
			// TODO Auto-generated method stub
			return userRepository.count();
		}



		public List<UserData> getUsersWithLimitAndOffset(int limit, int offset) {
			// TODO Auto-generated method stub
			
			Long userId = (long) (offset + 1);

		    List<UserData> users = new ArrayList<>();
		    while (limit > 0) {
		        // Query and retrieve the records from the database based on the starting index
		        Optional<UserData> userData = userRepository.findById(userId);
		        
		        if (userData.isPresent()) {
		            users.add(userData.get());
		        } else {
		            break;
		        }

		       
		        userId++;
		        limit--;
		    }

		    return users;
		
		}
		
		 
}
