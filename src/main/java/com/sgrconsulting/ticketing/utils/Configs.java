package com.sgrconsulting.ticketing.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class Configs {
	
	private static Configs instance = null;
	
	private String companyName = "SGR Consulting";
	
	private String address = "Via XXIV Maggio, 22. 26022. Castelverde (CR)";
	
	private String phoneNumber = "+390372748270";
	
	private String phoneNumberFormatted = "+39 0372 74 82 70";
	
	private String partitaIva = "01453650192";
	
	private String email = "info@sgrconsulting.it";
	
	public static Configs getInstance() {
		if(instance == null) {
			instance = new Configs();
		}
		
		return instance;
	}
	
}
