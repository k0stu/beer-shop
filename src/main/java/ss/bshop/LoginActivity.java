package ss.bshop;

import ss.bshop.communication.Communicator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private static String TAG = "Login Screen";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		setContentView(R.layout.login);
		Button login = (Button) findViewById(R.id.loginButton);
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				EditText usernameEdit = (EditText)
						findViewById(R.id.usernameField);
				String username = usernameEdit.getText().toString();
				EditText passwordEdit = (EditText)
						findViewById(R.id.pwdField);
				String pwd = passwordEdit.getText().toString();
				EditText serverEdit = (EditText)
						findViewById(R.id.serverField);
				String server = serverEdit.getText().toString();
				Global.username = username;
				Global.password = pwd;
				Global.server = "http://" + server;
				if (username.equals("") || pwd.equals("")) {
					Toast.makeText(getApplicationContext(), R.string.notnull,
							Toast.LENGTH_SHORT).show();
					return;
				}
				String serverResponse = Communicator.login();
				if (serverResponse.equals("OK")) {
					Intent intent = new Intent(LoginActivity.this,
							SelectActionActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(getApplicationContext(), R.string.nologin,
							Toast.LENGTH_SHORT).show();
				}
			}
		});
    }
}
