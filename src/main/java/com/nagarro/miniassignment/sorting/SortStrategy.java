package com.nagarro.miniassignment.sorting;

import java.util.List;

import com.nagarro.miniassignment.models.UserData;

public interface SortStrategy {
	 List<UserData> sort(List<UserData> users);
	
}
