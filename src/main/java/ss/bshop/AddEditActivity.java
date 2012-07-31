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
	private void fillSpinner() {
		Spinner goodsSpinner = (Spinner) findViewById(R.id.goodsSpinner);
		ArrayAdapter goodsAdapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item);
		Set<String> articleNames = Global.goods.keySet();
		goodsAdapter.addAll(articleNames);
		goodsSpinner.setAdapter(goodsAdapter);
	}
}
