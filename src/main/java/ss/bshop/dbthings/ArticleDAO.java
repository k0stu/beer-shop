package ss.bshop.dbthings;

import java.sql.SQLException;
import java.util.List;

import ss.bshop.mobile.entities.ArticleMobile;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ArticleDAO extends BaseDaoImpl<ArticleMobile, Integer> {

	protected ArticleDAO(ConnectionSource connectionSource,
			Class<ArticleMobile> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	public List<ArticleMobile> getAllArticles() throws SQLException {
		return this.queryForAll();
	}
}
