package ss.bshop;

import java.util.ArrayList;
import java.util.List;

import ss.bshop.R;
import ss.bshop.communication.Communicator;
import ss.bshop.mobile.entities.OutletMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CreateVisitActivity extends Activity {

	private static final String TAG = "CreateVisitActivity";
	private static final int ORDER = 0;
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
				CreateVisitActivity.this.finish();
			}
		});
		Button createOrder = (Button) findViewById(
				R.id.createOrderInVisitButton);
		createOrder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent createOrder = new Intent(CreateVisitActivity.this,
						CreateOrderActivity.class);
				startActivityForResult(createOrder, ORDER);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		if (requestCode == ORDER && resultCode == RESULT_OK) {
			// TODO: get the OutletMobile from data
		}
	}
}
