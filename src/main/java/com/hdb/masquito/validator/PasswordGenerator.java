package com.hdb.masquito.validator;

import java.util.Random;

public class PasswordGenerator {
	
	public String getRandomPassword() {
		
		
		        System.out.println("Generating password using random() : ");
		 
		        // A strong password has Cap_chars, Lower_chars,
		        // numeric value and symbols. So we are using all of
		        // them to generate our password
		        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		        String numbers = "0123456789";
		                String symbols = "!@#$%^&*_=+-/.?<>)";
		        String values = Capital_chars + Small_chars +
		                        numbers + symbols;
		 
		        // Using random method
		        Random rndm_method = new Random();
		 
		        char[] password = new char[8];
		 
		        for (int i = 0; i < 8; i++)
		        {
		            // Use of charAt() method : to get character value
		            // Use of nextInt() as it is scanning the value as int
		            password[i] =
		              values.charAt(rndm_method.nextInt(values.length()));
		 
		        }
		        return new String(password);
		    }
		
	

}
