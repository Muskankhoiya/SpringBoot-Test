package com.nagarro.miniassignment.controllers;


import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.miniassignment.apiconsumer.ApiConsumer;


import com.nagarro.miniassignment.models.UserData;
//import com.nagarro.miniassignment.payloads.UserRequest;
import com.nagarro.miniassignment.service.UserService;
import com.nagarro.miniassignment.service.ValidatorService;
import com.nagarro.miniassignment.sorting.SortStrategy;
import com.nagarro.miniassignment.sorting.SortStrategyFactory;
import com.nagarro.miniassignment.payloads.UsersPageInfo;
import com.nagarro.miniassignment.payloads.PageInfo;

@RestController
public class ApiController {
	private final ApiConsumer apiService;
	private final UserService userService;
	private final ValidatorService validatorService;
	private final SortStrategyFactory sortService;
	
    @Autowired
    public ApiController(ApiConsumer apiService,UserService userService,ValidatorService validatorService,SortStrategyFactory sortService) {
        this.apiService = apiService;
		this.userService =  userService;
		this.validatorService = validatorService;
		this.sortService = sortService;
    }
   
    
    @PostMapping("/users")
    public List<UserData> createUser(@RequestBody UserRequest request) {
        // Validate size to be within the specified range (max 5 users)
       int size = request.getSize();
    	size = Math.min(size, 5);

        List<UserData> users = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            UserData user = apiService.callApisAndMapToObject().block();
            userService.saveUserData(user);
            users.add(user);
        }
        return users;
    }
    
    
    
    
    @GetMapping("/users")
    public UsersPageInfo getUsers(
            @RequestParam(name = "sortType") String sortType,
            @RequestParam(name = "sortOrder") String sortOrder,
            @RequestParam(name = "limit") String limit,
            @RequestParam(name = "offset") String offset
    ) {
    String any = validatorService.validateParameters(sortType, sortOrder, limit, offset);
        System.out.print(any);

        // Get total count of users in DB
        long totalUsersCount = userService.getTotalUsersCount();

        // Get the list of users based on limit and offset using your service method
        
        
        List<UserData> users = userService.getUsersWithLimitAndOffset(Integer.parseInt(limit), Integer.parseInt(offset));
        
        SortStrategy sortStrategy = sortService.getStrategy(sortType, sortOrder);
        users = sortStrategy.sort(users);
      
  
        
        int temp =Integer.parseInt(limit)+Integer.parseInt(offset);
          
          

        // Determine page info
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(totalUsersCount);
        pageInfo.setHasNextPage(temp < totalUsersCount);
        pageInfo.setHasPreviousPage(Integer.parseInt(offset) > 0);
      

        UsersPageInfo usersPageInfo = new UsersPageInfo();
        usersPageInfo.setUserList(users);
        usersPageInfo.setPageInfo(pageInfo);

        return  usersPageInfo;
//        return userService.getUsers(sortType, sortOrder, Integer.parseInt(limit), Integer.parseInt(offset));
    }


 

}
