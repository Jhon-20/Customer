package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Customer {
	private Long id;
	private String numberId;
	private String firstName;
	private String lasName;
	private String email;
	private String phtoUrl;
	private Region region;
	private String state;
	
}
