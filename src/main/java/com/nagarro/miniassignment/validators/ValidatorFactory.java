package com.nagarro.miniassignment.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ValidatorFactory {

	@Autowired
    @Qualifier("numericValidator")
    private Validator<String> numericValidator;

    @Autowired
    @Qualifier("englishAlphabetsValidator")
    private Validator<String> englishAlphabetsValidator;

    public Validator<String> getValidator(String parameterType) {
        switch (parameterType) {
            case "limit":
            case "offset":
                return numericValidator;
            case "sortType":
            case "sortOrder":
                return englishAlphabetsValidator;
            default:
                throw new IllegalArgumentException("Invalid parameter type");
        }
    }
}
