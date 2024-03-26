package com.nagarro.miniassignment.sorting;

import java.util.Comparator;
import java.util.List;

import com.nagarro.miniassignment.models.UserData;

public class SortByName implements SortStrategy {

	private final String sortOrder;

    public SortByName(String sortOrder) {
        this.sortOrder = sortOrder;
    }

	@Override
	public List<UserData> sort(List<UserData> users) {
		   if (sortOrder.equalsIgnoreCase("even")) {
			  
	            users.sort(Comparator.comparingInt(user -> user.getFullName().length()-1 % 2 == 0 ? 0 : 1));
	        } else if (sortOrder.equalsIgnoreCase("odd")) {
	            users.sort(Comparator.comparingInt(user -> user.getFullName().length()-1 % 2 == 0 ? 1 : 0));
	        }
	        return users;
	    
	}

	

}
