package ss.bshop;

import ss.bshop.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CreateVisitActivity extends Activity {

	private static final String TAG = "CreateVisitActivity";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "created");
		setContentView(R.layout.createvisit);
		Button close = (Button) findViewById(R.id.closeVisitButton);
		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				CreateVisitActivity.this.finish();
			}
		});
		Button save = (Button) findViewById(R.id.saveVisitButton);
		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO: here comes saving logics
			}
		});
		Button createOrder = (Button) findViewById(R.id.closeVisitButton);
		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent createOrder = new Intent(CreateVisitActivity.this,
						CreateOrderActivity.class);
			}
		});
	}
}
