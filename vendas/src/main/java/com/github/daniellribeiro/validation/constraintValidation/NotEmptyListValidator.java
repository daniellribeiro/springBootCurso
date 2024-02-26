package com.github.daniellribeiro.validation.constraintValidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.daniellribeiro.validation.NotEmptyList;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List>{

	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value != null && !(value.isEmpty());
	}
	
}
