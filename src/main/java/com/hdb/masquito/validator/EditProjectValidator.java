package com.hdb.masquito.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hdb.masquito.model.ProjectDTO;
@Component
public class EditProjectValidator implements Validator{
	
	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return ProjectDTO.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors err) {
		// TODO Auto-generated method stub
		
	
		ValidationUtils.rejectIfEmpty(err, "projectName", "user.name.empty");
		ValidationUtils.rejectIfEmpty(err, "projectType", "user.name.empty");
		
		ProjectDTO projectDTO=(ProjectDTO) arg0;
		
		
		
		
		
		
	}

	

}
