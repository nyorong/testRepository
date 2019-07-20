package com.spring.myapp.rest;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RestVO {
	
	private int number;
	private String name;
	private List<String> hobbys;
	private Map<String, String> skills;
	
}
