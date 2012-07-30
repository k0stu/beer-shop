package ss.bshop;

import ss.bshop.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CreateOrderActivity extends Activity {
	private static final String TAG = "CreateOrderActivity";
	// ==== actions ====
	private static final int ADD = 0xADD;
	private static final int EDIT = 0xED17;
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
		Button addItem = (Button) findViewById(R.id.addItemButton);
		addItem.setOnClickListener(new ItemOnClickListener(ADD));
		Button editItem = (Button) findViewById(R.id.editItemButton);
		editItem.setOnClickListener(new ItemOnClickListener(EDIT));
		Button removeItem = (Button) findViewById(R.id.removeItemButton);
		removeItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO add "remove item" logics here
			}
		});
	}
	private class ItemOnClickListener implements OnClickListener {
		private int action;
		public ItemOnClickListener(int action) {
			this.action = action;
		}
		@Override
		public void onClick(View arg0) {
			Intent addnedit = new Intent(CreateOrderActivity.this,
					AddEditActivity.class);
			startActivityForResult(addnedit, action);
		}		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == ADD) {
				// TODO add here new item logics
			}
			if (requestCode == EDIT) {
				// TODO add here edit item logics
			}
		}
	}
}
