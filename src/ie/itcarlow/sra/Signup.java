package ie.itcarlow.sra;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class Signup extends Activity {
	String usernameParam, passwordParam, firstnameParam, lastnameParam,
			userEmailParam;
	Boolean mstatus;
	Integer userid;
	Button signupButton;
	EditText usernameEditText, passwordEditText, passConfirmText,
			firstnameEditText, lastnameEditText, userEmailEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		signupButton = (Button) findViewById(R.id.button_signup);

		signupButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				usernameEditText = (EditText) findViewById(R.id.signup_username);
				Validate.hasText(usernameEditText);
				usernameParam = usernameEditText.getText().toString();

				passwordEditText = (EditText) findViewById(R.id.signup_password);
				passConfirmText = (EditText) findViewById(R.id.signup_passconfirm);
				Validate.hasText(passwordEditText);
				Validate.isPasswordSame(passwordEditText, passConfirmText);
				passwordParam = passwordEditText.getText().toString();

				firstnameEditText = (EditText) findViewById(R.id.signup_firstname);
				Validate.hasText(firstnameEditText);
				firstnameParam = firstnameEditText.getText().toString();

				lastnameEditText = (EditText) findViewById(R.id.signup_lastname);
				Validate.hasText(lastnameEditText);
				lastnameParam = lastnameEditText.getText().toString();

				userEmailEditText = (EditText) findViewById(R.id.signup_email);
				Validate.isEmailAddress(userEmailEditText, true);
				userEmailParam = userEmailEditText.getText().toString();

			}
		});
	}

}
