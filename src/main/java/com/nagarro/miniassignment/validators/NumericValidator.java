package com.nagarro.miniassignment.validators;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("numericValidator")
@Scope("singleton")
public class NumericValidator implements Validator<String>{

	@Override
	public boolean validate(String value) {
		// TODO Auto-generated method stub
		  return value.matches("\\d+");
	}

}
