package com.nagarro.miniassignment.sorting;


import org.springframework.stereotype.Service;

@Service
public class SortStrategyFactory {
	public SortStrategy getStrategy(String sortType, String sortOrder) {
        if (sortType.equalsIgnoreCase("name")) {
            return new SortByName(sortOrder);
        } else if (sortType.equalsIgnoreCase("age")) {
            return new SortByAge(sortOrder);
        } else {
            throw new IllegalArgumentException("Invalid sort type");
        }
    }
}
