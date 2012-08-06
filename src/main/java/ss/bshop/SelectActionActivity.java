package ss.bshop;

import java.sql.SQLException;
import java.util.List;

import ss.bshop.R;
import ss.bshop.communication.Communicator;
import ss.bshop.communication.TestingCommunicator;
import ss.bshop.dbthings.ArticleDAO;
import ss.bshop.dbthings.HelperFactory;
import ss.bshop.dbthings.OutletDAO;
import ss.bshop.mobile.entities.ArticleMobile;
import ss.bshop.mobile.entities.OutletMobile;
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
		HelperFactory.setHelper(getApplicationContext());
		try {
			ArticleDAO adao = HelperFactory.getHelper().getArticleDAO();
			OutletDAO odao = HelperFactory.getHelper().getOutletDAO();
			List<OutletMobile> outlets = odao.getAllOutlets();
			List<ArticleMobile> articles = adao.getAllArticles();
			for (OutletMobile outlet : outlets) {
				Global.outlets.put(outlet.getName(), outlet);
			}
			for (ArticleMobile article : articles) {
				Global.goods.put(article.getName(), article);
			}
		} catch (SQLException e) {
			Log.e(TAG, "Failed to get data from storage");
		}
		
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
        Button synchro = (Button) findViewById(R.id.synchroButton);
        synchro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Global.goods.clear();
				List<ArticleMobile> goods = Communicator.getArticles();
				List<OutletMobile> outlets = Communicator.
						getOutletsForToday(Global.username);
				for (ArticleMobile article : goods) {
					Global.goods.put(article.getName(), article);	
				}
				try {
					ArticleDAO adao = HelperFactory.getHelper().getArticleDAO();
					adao.saveAll(goods);
				} catch (SQLException e) {
					Log.e(TAG, "Failed to save new articles: " + 
							e.getMessage());
				}
				for (OutletMobile outlet : outlets) {
					Global.outlets.put(outlet.getName(), outlet);
				}
				try {
					OutletDAO odao = HelperFactory.getHelper().getOutletDAO();
					odao.saveAll(outlets);
				} catch (SQLException e) {
					Log.e(TAG, "Failed to save new outlets: " + 
							e.getMessage());
				}
			}
        });
    }

}

