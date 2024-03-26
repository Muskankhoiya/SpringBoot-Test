package com.nagarro.miniassignment.validators;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("englishAlphabetsValidator")
@Scope("singleton")
public class EnglishAlphabetsValidator implements Validator<String> {

	@Override
	public boolean validate(String value) {
		// TODO Auto-generated method stub
		return  value.matches("^[a-zA-Z]*$");
	}

}
