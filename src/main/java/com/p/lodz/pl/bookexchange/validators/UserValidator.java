package com.p.lodz.pl.bookexchange.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.services.UserService;

@Component
public class UserValidator implements Validator {
	private static final String MATCHING_PASSWORD = "matchingPassword";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return UserData.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		UserData user = (UserData) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, USERNAME, "NotEmpty");
		if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
			errors.rejectValue(USERNAME, "Size.userForm.username");
		}
		if (userService.findByName(user.getUsername()) != null) {
			errors.rejectValue(USERNAME, "Duplicate.userForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD, "NotEmpty");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue(PASSWORD, "Size.userForm.password");
		}

		if (!user.getMatchingPassword().equals(user.getPassword())) {
			errors.rejectValue(MATCHING_PASSWORD, "Diff.userForm.matchingPassword");
		}
	}
}