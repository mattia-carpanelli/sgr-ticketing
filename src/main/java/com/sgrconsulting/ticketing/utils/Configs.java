package com.sgrconsulting.ticketing.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
	
	private String companyAddress = "Via XXIV Maggio, 22. 26022. Castelverde (CR)";
	
	private String companyPhoneNumber = "+390372748270";
	
	private String companyPhoneNumberFormatted = "+39 0372 748270";
	
	private String companyVATNumber = "01453650192";
	
	private String companyEmail = "info@sgrconsulting.it";
	
	private Map<String, String> modelAttributes = new HashMap<>();
	
	public static Configs getInstance() {
		if(instance == null) {
			instance = new Configs();
		}
		
		return instance;
	}
	
	private Configs() {
		modelAttributes.put("companyLogoFilename", companyLogoFilename);
		modelAttributes.put("companyName", companyName);
		modelAttributes.put("companyAddress", companyAddress);
		modelAttributes.put("companyPhoneNumber", companyPhoneNumber);
		modelAttributes.put("companyPhoneNumberFormatted", companyPhoneNumberFormatted);
		modelAttributes.put("companyVATNumber", companyVATNumber);
		modelAttributes.put("companyEmail", companyEmail);
	}
	
}
