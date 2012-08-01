package ss.bshop;

import java.util.ArrayList;

import ss.bshop.R;
import ss.bshop.extraviews.Table;
import ss.bshop.mobile.entities.ArticleMobile;
import ss.bshop.mobile.entities.OutletOrderStructureMobile;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CreateOrderActivity extends Activity {
	private static final String TAG = "CreateOrderActivity";
	// ==== actions ====
	private static final int ADD = 0xADD;
	private static final int EDIT = 0xED17;
	// ==== ======= ====
	private Table table = null;
	ArrayList<OutletOrderStructureMobile> orderList =
			new ArrayList<OutletOrderStructureMobile>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "created");
		setContentView(R.layout.createorder);
		table = (Table) findViewById(R.id.orderTable);
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
			if (action == EDIT) {
				TableRow toEdit = table.getSelectedRow();
				TextView articleNameView = (TextView) toEdit.getChildAt(0);
				String articleName = articleNameView.getText().toString();
				TextView qtyView = (TextView) toEdit.getChildAt(1);
				int qty = Integer.parseInt(qtyView.getText().toString());
				OutletOrderStructureMobile oosmToEdit = new OutletOrderStructureMobile();
				oosmToEdit.setArticle(Global.goods.get(articleName));
				oosmToEdit.setAmount(qty);
				Global.objectStorage.put("toEdit", oosmToEdit);
				addnedit.putExtra("key", "toEdit");
			}
			startActivityForResult(addnedit, action);
		}		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == ADD) {
				String key = data.getStringExtra("key");
				OutletOrderStructureMobile orderRow =
						(OutletOrderStructureMobile) Global
						.objectStorage.get(key);
				Global.objectStorage.remove(key);
				this.addArticleToTable(orderRow);
			}
			if (requestCode == EDIT) {
				// TODO add here edit item logics
			}
		}
	}
	/**
	 * Use this method to add an article to order table
	 * @param article
	 */
	private void addArticleToTable(OutletOrderStructureMobile row) {
		orderList.add(row);
		final TableRow tr = new TableRow(this);
		tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		TextView articleName = new TextView(this);
		articleName.setText(row.getArticle().getName());
		articleName.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		tr.addView(articleName);
		TextView qty = new TextView(this);
		qty.setText(row.getAmount());
		qty.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		tr.addView(qty);
		tr.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				table.setSelectedRow(tr);
			}
		});
		table.addView(tr, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
	}
}
