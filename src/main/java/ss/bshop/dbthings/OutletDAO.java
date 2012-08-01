package ss.bshop.dbthings;

import java.sql.SQLException;
import java.util.List;

import ss.bshop.mobile.entities.OutletMobile;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class OutletDAO extends BaseDaoImpl<OutletMobile, Integer> {

	protected OutletDAO(ConnectionSource connectionSource,
			Class<OutletMobile> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	public List<OutletMobile> getAllOutlets() throws SQLException {
		return this.queryForAll();
	}

	public void saveAllOutlets(List<OutletMobile> outlets) {
		// TODO fill stub
	}
}
