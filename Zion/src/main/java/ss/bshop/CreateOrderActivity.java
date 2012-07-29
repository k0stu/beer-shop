package ss.bshop;

import ss.bshop.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class CreateOrderActivity extends Activity {
	private static final String TAG = "CreateOrderActivity";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "created");
		setContentView(R.layout.createorder);
	}
}
