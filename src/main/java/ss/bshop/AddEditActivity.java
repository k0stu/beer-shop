package ss.bshop;

import ss.bshop.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddEditActivity extends Activity {
	private static final String TAG = "AddEditActivity";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "created");
		setContentView(R.layout.addedit);
		Button ok = (Button) findViewById(R.id.goodsOk);
		ok.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Add returning article logics here
				AddEditActivity.this.finish();
			}
		});
		Button cancel = (Button) findViewById(R.id.goodsCancel);
		cancel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				AddEditActivity.this.finish();
			}
		});
	}
}
