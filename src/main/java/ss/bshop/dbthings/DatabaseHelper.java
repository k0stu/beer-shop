package ss.bshop.dbthings;

import java.sql.SQLException;

import ss.bshop.mobile.entities.ArticleMobile;
import ss.bshop.mobile.entities.OutletMobile;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String TAG = DatabaseHelper.class.getSimpleName();

	private static final String DATABASE_NAME ="beershop.db";
	
	private static final int DATABASE_VERSION = 1;
	
	//ссылки на DAO соответсвующие сущностям, хранимым в БД
	private ArticleDAO articleDao = null;
	private OutletDAO outletDao = null;
	
	public DatabaseHelper(Context context) {
		super(context,DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db,
			ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, ArticleMobile.class);
			TableUtils.createTable(connectionSource, OutletMobile.class);
		} catch (SQLException e) {
			Log.e(TAG, "error creating DB " + DATABASE_NAME);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource connectionSource, int oldVer, int newVer) {
	}

	public ArticleDAO getArticleDAO() throws SQLException {
		if(articleDao == null) {
			articleDao = new ArticleDAO(getConnectionSource(),
					ArticleMobile.class);
		}
		return articleDao;
	}

	public OutletDAO getOutletDAO() throws SQLException {
		if(outletDao == null) {
			outletDao = new OutletDAO(getConnectionSource(),
					OutletMobile.class);
		}
		return outletDao;
	}
	

	@Override
	public void close() {
		super.close();
		articleDao = null;
		outletDao = null;
	}
}
