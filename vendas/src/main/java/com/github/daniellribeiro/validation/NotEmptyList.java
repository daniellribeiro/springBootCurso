package com.github.daniellribeiro.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.daniellribeiro.validation.constraintValidation.NotEmptyListValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidator.class)

public @interface NotEmptyList {
	String message() default "A lista nao pode ser vazia";
	
	Class<?>[] groups () default {};
	
	Class<? extends Payload>[] payload() default {};
	
}
