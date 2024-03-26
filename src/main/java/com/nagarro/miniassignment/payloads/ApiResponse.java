package com.nagarro.miniassignment.payloads;

import com.nagarro.miniassignment.models.UserData;

public class ApiResponse {
	 private UserData[] results;

	   

	    public UserData[] getResults() {
	        return results;
	    }

	    public void setResults(UserData[] results) {
	        this.results = results;
	    }
}
