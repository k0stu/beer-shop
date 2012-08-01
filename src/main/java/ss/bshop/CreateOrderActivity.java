package ss.bshop;

import java.util.ArrayList;

import ss.bshop.R;
import ss.bshop.extraviews.Table;
import ss.bshop.mobile.entities.OutletOrderMobile;
import ss.bshop.mobile.entities.OutletOrderStructureMobile;
import android.app.Activity;
import android.widget.TableRow.LayoutParams;
import android.content.Intent;
import android.graphics.Color;
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
				Global.objectStorage.put("orderList", orderList);
				OutletOrderMobile order = new OutletOrderMobile();
				Spinner orderTypeSpinner = 
						(Spinner) findViewById(R.id.orderTypeSpinner);
				String orderType = (String) orderTypeSpinner.getSelectedItem();
				order.setType(orderType);
				double payment = 0;
				for (OutletOrderStructureMobile orderRow : orderList) {
					double price = orderRow.getPrice();
					int amount = orderRow.getAmount();
					double perRowPayment = price * amount;
					payment += perRowPayment;
				}
				order.setPayment(payment);
				order.setDiscount((byte) 0);
				order.setStructure(orderList);
				Global.objectStorage.put("order", order);
				Intent result = CreateOrderActivity.this.getIntent();
				result.putExtra("key", "order");
				setResult(RESULT_OK, result);
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
				TableRow toRemove = table.getSelectedRow();
				table.removeView(toRemove);
				OutletOrderStructureMobile orderRow =
						CreateOrderActivity.this
						.getOrderRowForTableRow(toRemove);
				orderList.remove(toRemove);
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
				OutletOrderStructureMobile oosmToEdit =
						CreateOrderActivity.this.getOrderRowForTableRow(toEdit);
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
			String key = data.getStringExtra("key");
			OutletOrderStructureMobile orderRow =
					(OutletOrderStructureMobile) Global
					.objectStorage.get(key);
			Global.objectStorage.remove(key);
			if (requestCode == ADD) {
				this.addArticleToTable(orderRow);
			}
			if (requestCode == EDIT) {
				this.editArticleInTable(orderRow);
			}
		}
	}
	private OutletOrderStructureMobile getOrderRowForTableRow(TableRow row) {
		OutletOrderStructureMobile toReturn = null;
		TextView articleNameView = (TextView) row.getChildAt(0);
		String articleName = articleNameView.getText().toString();
		TextView qtyView = (TextView) row.getChildAt(1);
		int qty = Integer.parseInt(qtyView.getText().toString());
		for (OutletOrderStructureMobile orderRow : orderList) {
			if (orderRow.getArticle().getName().equals(articleName) &&
					orderRow.getAmount() == qty) {
				toReturn = orderRow;
				break;
			}
		}
		return toReturn;
	}
	/**
	 * Use this method to add an article to order table
	 * @param article
	 */
	private void addArticleToTable(OutletOrderStructureMobile row) {
		orderList.add(row);
		final TableRow tr = new TableRow(this);
		LayoutParams params = new LayoutParams();
		params.setMargins(0, 0, 0, 1);
		tr.setLayoutParams(params);
		TextView articleName = new TextView(this);
		articleName.setText(row.getArticle().getName());
		articleName.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		tr.addView(articleName);
		TextView qty = new TextView(this);
		qty.setText(String.valueOf(row.getAmount()));
		qty.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		tr.addView(qty);
		tr.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				TableRow previous = table.getSelectedRow();
				if (previous != null) {
					previous.setBackgroundColor(Color.BLACK);
				}
				table.setSelectedRow(tr);
				tr.setBackgroundColor(Color.GRAY);
			}
		});
		table.addView(tr, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
	}
	private void editArticleInTable(OutletOrderStructureMobile row) {
		TableRow selected = table.getSelectedRow();
		TextView articleNameView = (TextView) selected.getChildAt(0);
		articleNameView.setText(row.getArticle().getName());
		TextView qtyView = (TextView) selected.getChildAt(1);
		qtyView.setText(String.valueOf(row.getAmount()));
	}
}
