package ss.bshop;

import ss.bshop.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectActionActivity extends Activity {

    private static String TAG = "Zion";

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
        setContentView(R.layout.main);
        Button create = (Button) findViewById(R.id.createButton);
        create.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent createVisit = new Intent(SelectActionActivity.this,
						CreateVisitActivity.class);
				startActivity(createVisit);
			}
        });
        Button exit = (Button) findViewById(R.id.exitButton);
        exit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				SelectActionActivity.this.finish();
			}
        });
        Button synchro = (Button) findViewById(R.id.synchroButton);
        synchro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Synchronizing logics here
			}

        });
    }

}

