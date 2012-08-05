package ss.bshop;


import java.util.Date;

import ss.bshop.R;
import ss.bshop.communication.Communicator;
import ss.bshop.communication.TestingCommunicator;
import ss.bshop.mobile.entities.OutletMobile;
import ss.bshop.mobile.entities.OutletOrderMobile;
import ss.bshop.mobile.entities.VisitMobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateVisitActivity extends Activity {

	private static final String TAG = "CreateVisitActivity";
	private static final int ORDER = 0;
	private OutletOrderMobile order = null;
	private Spinner outletsSpinner = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "created");
		setContentView(R.layout.createvisit);
		fillSpinner();
		Button close = (Button) findViewById(R.id.closeVisitButton);
		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				CreateVisitActivity.this.finish();
			}
		});
		Button save = (Button) findViewById(R.id.saveVisitButton);
		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					VisitMobile visit = new VisitMobile();
					visit.setOutletOrder(order);
					visit.setTime(new Date());
					String outletName = (String) outletsSpinner.getSelectedItem();
					OutletMobile outlet = Global.outlets.get(outletName);
					visit.setOutlet(outlet);
					try {
						Location location = getLocation();
						visit.setLat(location.getLatitude());
						visit.setLng(location.getLongitude());
					} catch (NullPointerException npe) {
						visit.setLat(0);
						visit.setLng(0);
					}
					Communicator.addVisit(visit);
				} catch (NullPointerException e) {
					Toast.makeText(CreateVisitActivity.this, e.getMessage(), 8);
					Log.e(TAG, "Error: " + e.getMessage());
				}
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

	private void fillSpinner() {
		outletsSpinner = (Spinner) findViewById(R.id.outlets);
		ArrayAdapter outletsAdapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item);
		outletsAdapter.addAll(Global.outlets.keySet());
		outletsSpinner.setAdapter(outletsAdapter);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		if (requestCode == ORDER && resultCode == RESULT_OK) {
			String key = data.getStringExtra("key");
			order = (OutletOrderMobile) 
					Global.objectStorage.get(key);
			Global.objectStorage.remove(key);
		}
	}
	private Location getLocation() {
		LocationManager locationManager = null;
		String context = Context.LOCATION_SERVICE;
		locationManager = (LocationManager) getSystemService(context);
		String provider = LocationManager.GPS_PROVIDER;
		return locationManager.getLastKnownLocation(provider);
	}
}
