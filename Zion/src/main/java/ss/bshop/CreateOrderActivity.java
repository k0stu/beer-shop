package ss.bshop;

import ss.bshop.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CreateOrderActivity extends Activity {
	private static final String TAG = "CreateOrderActivity";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "created");
		setContentView(R.layout.createorder);
		Spinner orderTypeSpinner = 
				(Spinner) findViewById(R.id.orderTypeSpinner);
		ArrayAdapter<CharSequence> orderTypes = ArrayAdapter
				.createFromResource(this, R.array.orderType, 
				android.R.layout.simple_spinner_item);
		orderTypes.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		orderTypeSpinner.setAdapter(orderTypes);
		Button cancel = (Button) findViewById(R.id.cancelOrderButton);
		cancel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				CreateOrderActivity.this.finish();				
			}
		});
		Button save = (Button) findViewById(R.id.saveOrderButton);
		save.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO: create and return OutletOrderMobile object logics
				CreateOrderActivity.this.finish();
			}
		});
	}
}
