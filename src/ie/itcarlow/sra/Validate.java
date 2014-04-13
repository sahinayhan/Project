package ie.itcarlow.sra;

import java.util.regex.Pattern;

import android.widget.EditText;

public class Validate {
	// Regular Expression
	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// Error Messages
	private static final String REQUIRED_MSG = "Field is Required";
	private static final String EMAIL_MSG = "Invalid Email Address";
	private static final String NO_MATCH ="Passwords do not match";
	//private static final String PHONE_MSG = "###-#######";

	// method will be called to check an email address
	public static boolean isEmailAddress(EditText editText, boolean required) {
		return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
	}

	// return true if the input field is valid, based on the parameter passed
	public static boolean isValid(EditText editText, String regex,
			String errMsg, boolean required) {

		String text = editText.getText().toString().trim();
		
		editText.setError(null);

		// text required and editText is blank, so return false
		if (required && !hasText(editText))
			return false;

		// pattern doesn't match so returning false
		if (required && !Pattern.matches(regex, text)) {
			editText.setError(errMsg);
			return false;
		}
		;

		return true;
	}

	// check the input field has any text or not
	// return true if it contains text otherwise false
	public static boolean hasText(EditText editText) {

		String text = editText.getText().toString().trim();
		editText.setError(null);

		// length 0 means there is no text
		if (text.length() == 0) {
			editText.setError(REQUIRED_MSG);
			return false;
		}

		return true;
	}
	//Check if the passwords matches.
	public static boolean isPasswordSame(EditText editText,EditText editText2){
		String text = editText.getText().toString().trim();
		editText.setError(null);
		String text2 = editText2.getText().toString().trim();
		editText2.setError(null);
		
		if (text.equals(text2)== false){
			editText2.setError(NO_MATCH);
			return false;
		}
		return true;
	}
}
