package com.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FiledValidators {



	private static final String EMAIL_PATTERN ="[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.(com|gmail)";


	public static boolean validateEmail(final String hex) {

		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}

}
