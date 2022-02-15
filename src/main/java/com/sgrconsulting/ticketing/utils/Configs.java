package com.sgrconsulting.ticketing.utils;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class Configs implements Serializable {
	
	private static final long serialVersionUID = -2737073455521531282L;

	private static Configs instance = null;
	
	private String companyLogoFilename = "logo.gif";
	
	private String companyName = "SGR Consulting";
	
	private String address = "Via XXIV Maggio, 22. 26022. Castelverde (CR)";
	
	private String phoneNumber = "+390372748270";
	
	private String phoneNumberFormatted = "+39 0372 748270";
	
	private String partitaIva = "01453650192";
	
	private String email = "info@sgrconsulting.it";
	
	public static Configs getInstance() {
		if(instance == null) {
			instance = new Configs();
		}
		
		return instance;
	}
	
}
