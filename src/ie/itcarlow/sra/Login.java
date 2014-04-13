package ie.itcarlow.sra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	EditText usernameText, passwordText;
	Button loginButton;
	TextView signupTextView ;

	SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.activity_login);
		super.onCreate(savedInstanceState);
		
		session = new SessionManager(getApplicationContext());
		
		usernameText = (EditText)findViewById(R.id.username);
		passwordText = (EditText)findViewById(R.id.password);
		
		Toast.makeText(getApplicationContext(), "User Login Status" + session.isLoggedIn(), Toast.LENGTH_LONG).show();
		
		loginButton = (Button)findViewById(R.id.sign_in) ;
		signupTextView = (TextView)findViewById(R.id.register);
		
		loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = usernameText.getText().toString();
				String password = passwordText.getText().toString();
				
				if ( username.equals("test") && password.equals("test")){
					session.createLoginSession("demo", "demo@demo.com");
					
					Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
					startActivity(mainActivity);
					finish();
				}
			}
		});
		
		signupTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent registerAct = new Intent(getApplicationContext(),Signup.class);
				startActivity(registerAct);
				finish();
			}
		});
	}
}
