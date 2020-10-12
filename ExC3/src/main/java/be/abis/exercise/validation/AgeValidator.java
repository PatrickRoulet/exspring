package be.abis.exercise.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<ValidAge, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Integer age;
		try {
			age = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}
		
		if (age < 18) {
			return false;
		}
		return true;	
	}

}
