package ss.bshop;

import java.util.Set;

import ss.bshop.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AddEditActivity extends Activity {
	private static final String TAG = "AddEditActivity";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "created");
		setContentView(R.layout.addedit);
		fillSpinner();
		Button ok = (Button) findViewById(R.id.goodsOk);
		ok.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Spinner goodsSpinner =
						(Spinner) findViewById(R.id.goodsSpinner);
				// TODO returning OutletOrderStructureMobile object
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
	private void fillSpinner() {
		Spinner goodsSpinner = (Spinner) findViewById(R.id.goodsSpinner);
		ArrayAdapter goodsAdapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item);
		Set<String> articleNames = Global.goods.keySet();
		goodsAdapter.add(articleNames);
		goodsSpinner.setAdapter(goodsAdapter);
	}
}
