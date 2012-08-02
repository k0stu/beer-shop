package ss.bshop.dbthings;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import ss.bshop.mobile.entities.OutletMobile;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;

public class OutletDAO extends BaseDaoImpl<OutletMobile, Integer> {

	protected OutletDAO(ConnectionSource connectionSource,
			Class<OutletMobile> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	public List<OutletMobile> getAllOutlets() throws SQLException {
		return this.queryForAll();
	}

	public void saveAll(final List<OutletMobile> outlets) throws SQLException {
		TransactionManager.callInTransaction(connectionSource,
				new Callable<Void>() {
					@Override
					public Void call() throws Exception {
						for (OutletMobile outlet : outlets) {
							OutletDAO.this.createOrUpdate(outlet);
						}
						return null;
					}
		});
	}
}
