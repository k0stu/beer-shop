package ss.bshop;

import java.util.Set;

import ss.bshop.R;
import ss.bshop.mobile.entities.ArticleMobile;
import ss.bshop.mobile.entities.OutletOrderStructureMobile;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddEditActivity extends Activity {
	private static final String TAG = "AddEditActivity";
	private Spinner goodsSpinner = null;
	private ArrayAdapter goodsAdapter = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "created");
		setContentView(R.layout.addedit);
		goodsSpinner = (Spinner) findViewById(R.id.goodsSpinner);
		fillSpinner();
		Intent incoming = this.getIntent();
		String key = incoming.getStringExtra("key");
		if (key != null) {
			OutletOrderStructureMobile oosmToEdit =
					(OutletOrderStructureMobile) Global.objectStorage.get(key);
			int articlePosition = goodsAdapter.getPosition(oosmToEdit.getArticle().getName());
			goodsSpinner.setSelection(articlePosition);
			EditText qtyEdit = (EditText) findViewById(R.id.goodsQty);
			qtyEdit.setText(oosmToEdit.getAmount());
		}
		Button ok = (Button) findViewById(R.id.goodsOk);
		ok.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Spinner goodsSpinner =
						(Spinner) findViewById(R.id.goodsSpinner);
				String articleName = (String) goodsSpinner.getSelectedItem();
				ArticleMobile article = Global.goods.get(articleName);
				EditText qtyEdit = (EditText) findViewById(R.id.goodsQty);
				int qty = Integer.parseInt(qtyEdit.getText().toString());
				OutletOrderStructureMobile orderRow =
						new OutletOrderStructureMobile();
				orderRow.setArticle(article);
				orderRow.setAmount(qty);
				orderRow.setPrice(qty * article.getPrice());
				Global.objectStorage.put("orderRow", orderRow);
				Intent result = new Intent();
				result.putExtra("key", "orderRow");
				setResult(RESULT_OK, result);
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
	private int getPositionForName(String name) {
		
		return 0;
	}
	private void fillSpinner() {
		goodsAdapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item);
		Set<String> articleNames = Global.goods.keySet();
		goodsAdapter.addAll(articleNames);
		goodsSpinner.setAdapter(goodsAdapter);
	}
}
