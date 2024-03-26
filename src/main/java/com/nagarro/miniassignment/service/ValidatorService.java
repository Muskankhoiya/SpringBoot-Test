package com.nagarro.miniassignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.miniassignment.validators.Validator;
import com.nagarro.miniassignment.validators.ValidatorFactory;

@Service
public class ValidatorService {

	
	@Autowired
    private ValidatorFactory validatorFactory;
	
	
	public String validateParameters(String sortType, String sortOrder, String limit, String offset) {
        validateParam("sortType", sortType);
        validateParam("sortOrder", sortOrder);
        validateParam("limit", limit);
        validateParam("offset", offset);
        
        return "true";
    }

    private void validateParam(String paramName, String paramValue) {
        Validator<String> validator = validatorFactory.getValidator(paramName);
        if (!validator.validate(paramValue)) {
            throw new IllegalArgumentException("Invalid " + paramName + " value");
        }
}
}
