package ie.itcarlow.sra;

import java.io.IOException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson.JacksonFactory;

import ie.itcarlow.sra.userendpoint.Userendpoint;
import ie.itcarlow.sra.userendpoint.model.User;
import android.os.AsyncTask;
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
				
				new registerUserTask().execute();
			}
		});
	}
	
	private class registerUserTask extends AsyncTask<Void, Void, Void> {

	    /**
	     * Calls appropriate CloudEndpoint to indicate that user checked into a place.
	     *
	     * @param params the place where the user is checking in.
	     */
	    @Override
	    protected Void doInBackground(Void... params) {
	      User newUser = new User();
	      
	      // Set the ID of the store where the user is. 
	      // This would be replaced by the actual ID in the final version of the code. 
	      newUser.setFirstName(firstnameParam);
	      newUser.setLastName(lastnameParam);
	      newUser.setUsername(usernameParam);
	      newUser.setPassword(passwordParam);
	      newUser.setUserEmail(userEmailParam);
	      newUser.setUserStatus(true);

	      Userendpoint.Builder builder = new Userendpoint.Builder(
	          AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
	          null);
	          
	      builder = CloudEndpointUtils.updateBuilder(builder);

	      Userendpoint endpoint = builder.build();
	      

	      try {
	        endpoint.insertUser(newUser).execute();
	      } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }

	      return null;
	    }
	  }

}
